package com.vsks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;

@Controller
public class WishController {

    private Calendar calendar;

    @Autowired
    public WishController(Calendar calendar) {
        this.calendar = calendar;
    }

    @RequestMapping(value = "/wish.htm")
    public String getWish(Model model, @RequestParam("name") String name) {
        String wish = null;
        int hours = calendar.getTime().getHours();
        if (hours > 0 && hours < 12)
            wish = "Good morning";
        else if (hours > 12 && hours < 16)
            wish = "Good afternoon";
        else if (hours > 16 && hours < 20)
            wish = "Good evening";
        else
            wish = "Good night";
        model.addAttribute("msg", wish + " " + name);
        return "wish";
    }

}