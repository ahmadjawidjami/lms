package com.tu.ziik.lms.controllers;

import com.tu.ziik.lms.model.lecturer.Post;
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

    @GetMapping("/lecturer/course/post-list")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("post", new Post());

        model.addAttribute("files", storageService
                .loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/lecturer/course/post-list/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }

    @PostMapping("/lecturer/course/post")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   @ModelAttribute("post") Post post,
                                   RedirectAttributes redirectAttributes) {

       // post.setFilePath("course/" + post.getType() + "/" + post.getTitle());

        post.setFilePath(storageService.createFilePath(file, post));

        storageService.store(file, post.getFilePath());
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/lecturer/course/post-list";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
