package com.tu.ziik.lms.controllers;

import com.tu.ziik.lms.model.Event;
import com.tu.ziik.lms.service.admin.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ahmadjawid on 1/29/17.
 */

@Controller
public class EventController {

    @Autowired
    private EventService eventService;


    @RequestMapping(value = "/admin/event/list", method = RequestMethod.GET)
    public String listEvents(Model model){


        model.addAttribute("events", eventService.findAllEvents());

        return "admin/event/event-list";
    }


    @RequestMapping(value = "/admin/event/add", method = RequestMethod.GET)
    public String addEvent(Model model){

        model.addAttribute("event", new Event());

        return "admin/event/event-form";
    }

    @RequestMapping(value = "/admin/event/add", method = RequestMethod.POST)
    public String addEvent(@ModelAttribute("event") Event event){

        eventService.saveEvent(event);

        return "redirect:/admin/event/list";
    }
}
