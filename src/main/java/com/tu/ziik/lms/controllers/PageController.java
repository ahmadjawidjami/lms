package com.tu.ziik.lms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ahmadjawid on 1/4/17.
 */

@Controller
public class PageController {

    @RequestMapping("/temp")
    public String loadMain(){
        return "/fragments/admin-main";
    }
}
