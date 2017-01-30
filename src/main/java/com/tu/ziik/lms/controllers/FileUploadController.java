package com.tu.ziik.lms.controllers;

import com.tu.ziik.lms.model.lecturer.Comment;
import com.tu.ziik.lms.model.lecturer.CourseContentPost;
import com.tu.ziik.lms.service.SecurityService;
import com.tu.ziik.lms.service.lecturer.course.CourseContentPostService;
import com.tu.ziik.lms.storage.StorageFileNotFoundException;
import com.tu.ziik.lms.storage.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @Autowired
    private CourseContentPostService postService;

    @Autowired
    private SecurityService securityService;


    @RequestMapping(value = "/lecturer/course/{courseId}/list", method = RequestMethod.GET)
    public String listCourseContent(@PathVariable Long courseId, Model model) {
        String courseTitle = postService.findCourseById(courseId).getTitle();
        String username = securityService.findAuthenticatedUsername();

        model.addAttribute("posts", postService.findAllPostsByCourseIdAndUsername(courseId, username));
        model.addAttribute("courseId", courseId);
        model.addAttribute("courseTitle", courseTitle);

        model.addAttribute("post", new CourseContentPost());
        model.addAttribute("comment", new Comment());


        return "/lecturer/course-content-list";
    }

    @RequestMapping(value = "/lecturer/course/{courseId}/add", method = RequestMethod.GET)
    public String addCourseContent(@PathVariable Long courseId, Model model) {

        model.addAttribute("courseId", courseId);
        model.addAttribute("post", new CourseContentPost());

        return "lecturer/course/add-content";

    }

    @RequestMapping(value = "/lecturer/course/{courseId}/add", method = RequestMethod.POST)
    public String addCourseContent(@ModelAttribute("post") CourseContentPost courseContentPost,
                                   @PathVariable Long courseId,
                                   @RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        if (!file.isEmpty()){
            courseContentPost.setFileName(file.getOriginalFilename());
            courseContentPost.setFilePath(storageService.createFilePath(file, courseId));
            storageService.store(file, courseContentPost.getFilePath());
        }

        String username = securityService.findAuthenticatedUsername();

        postService.setUser(courseContentPost, username);

        postService.setCourse(courseContentPost, courseId);

        if (courseContentPost.getCourse() != null) {
            postService.savePost(courseContentPost);
            redirectAttributes.addFlashAttribute("success", "Post added successfully.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid course id.");
        }

        redirectAttributes.addAttribute("courseId", courseId);
        return "redirect:/lecturer/course/{courseId}/list";

    }

    @GetMapping("/course/{courseId}/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename, @PathVariable Long courseId) {

        Resource file = storageService.loadAsResource("course/" + courseId + "/" + filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }


//    @PostMapping("/lecturer/course/courseContentPost")
//    public String handleFileUpload(@RequestParam("file") MultipartFile file,
//                                   @ModelAttribute("post") CourseContentPost courseContentPost,
//                                   RedirectAttributes redirectAttributes) {
//
//        // courseContentPost.setFilePath("course/" + courseContentPost.getType() + "/" + courseContentPost.getTitle());
//
//        courseContentPost.setFilePath(storageService.createFilePath(file, new Long(2)));
//
//        storageService.store(file, courseContentPost.getFilePath());
//        redirectAttributes.addFlashAttribute("message",
//                "You successfully uploaded " + file.getOriginalFilename() + "!");
//
//        return "redirect:/lecturer/course/courseContentPost-list";
//    }

//    @GetMapping("/lecturer/course/post-list")
//    public String listUploadedFiles() throws IOException {
//
//        //model.addAttribute("post", new CourseContentPost());
//
////        model.addAttribute("files", storageService
////                .loadAll()
////                .map(path ->
////                        MvcUriComponentsBuilder
////                                .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
////                                .build().toString())
////                .collect(Collectors.toList()));
//
//        return "uploadForm";
//    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
