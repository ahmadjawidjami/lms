package com.tu.ziik.lms.controllers;

import com.tu.ziik.lms.model.User;
import com.tu.ziik.lms.model.admin.course.Course;
import com.tu.ziik.lms.model.lecturer.CourseContentPost;
import com.tu.ziik.lms.service.lecturer.course.CourseContentPostService;
import com.tu.ziik.lms.storage.StorageFileNotFoundException;
import com.tu.ziik.lms.storage.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.stream.Collectors;

@Controller
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @Autowired
    private CourseContentPostService postService;


    @RequestMapping(value = "/lecturer/course/{courseId}/list", method = RequestMethod.GET)
    public String listCourseContent(@PathVariable Long courseId, Model model) {
        String courseTitle = postService.findCourseById(courseId).getTitle();
        model.addAttribute("posts", postService.findAllPostsById(courseId));
        model.addAttribute("courseId", courseId);
        model.addAttribute("courseTitle", courseTitle);

        model.addAttribute("post", new CourseContentPost());

//        model.addAttribute("files", storageService
//                .loadAll(courseId)
//                .map(path ->
//                        MvcUriComponentsBuilder
//                                .fromMethodName(FileUploadController.class, "serveFile", courseId, path.getFileName().toString())
//                                .build().toString())
//                .collect(Collectors.toList()));

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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Long userId = user.getId();

//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof UserDetails) {
//            String username = ((UserDetails)principal).getUsername();
//        } else {
//            String username = principal.toString();
//        }

        //Course course = postService.findCourseById(courseId);
        //courseContentPost.setCourse(course);
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
