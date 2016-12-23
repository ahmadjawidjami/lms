package com.tu.ziik.lms.controllers;

import com.tu.ziik.lms.model.admin.course.Course;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ahmadjawid on 12/23/16.
 */

@Controller
public class AdminCourseController {


    @RequestMapping(value = "/admin/course/list", method = RequestMethod.GET)
    public String listCourses(){
        return "admin/course/course-list";
    }


    @RequestMapping(value = "/admin/course/add", method = RequestMethod.GET)
    public String addCourse(Model model){

        model.addAttribute("course", new Course());
        return "admin/course/add";
    }
}
