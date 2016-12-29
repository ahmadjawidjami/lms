package com.tu.ziik.lms.controllers;

import com.tu.ziik.lms.model.lecturer.CourseContentPost;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/**
 * Created by ahmadjawid on 12/28/16.
 */

@Controller
public class PostController {


    //@RequestMapping(value = "/lecturer/course/post", method = RequestMethod.GET)
    public String addPost(Model model){
        model.addAttribute("post", new CourseContentPost());
        return "lecturer/post";
    }
}