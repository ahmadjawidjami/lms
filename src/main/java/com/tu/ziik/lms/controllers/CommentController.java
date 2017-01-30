package com.tu.ziik.lms.controllers;

import com.tu.ziik.lms.model.lecturer.Comment;
import com.tu.ziik.lms.service.lecturer.course.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ahmadjawid on 1/30/17.
 */

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/lecturer/course/{courseId}/{postId}/comment", method = RequestMethod.POST)
    public String saveComment(@PathVariable Long courseId, @PathVariable Long postId, @ModelAttribute("comment") Comment comment ){

        commentService.setCourseContentPost(comment, postId);
        commentService.saveComment(comment);

        //commentService.saveComment()

        return "redirect:/lecturer/course/" + courseId + "/list";

    }
}
