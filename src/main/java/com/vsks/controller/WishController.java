package com.vsks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;

@Controller
public class WishController {

    @Autowired
    private Calendar calendar;

    @GetMapping(value = "/wish")
    public ModelAndView getWish(@RequestParam(name = "name", required = false) String name) {
        System.out.println("WishController.getWish :: Start");
        ModelAndView mav = new ModelAndView("wish");
        mav.addObject("name",  "Saikiran");
        mav.addObject("rollNo", 1234);
        mav.addObject("region", "X");
        mav.addObject("sayHi", Boolean.FALSE);
        mav.addObject("loginUrl", "https://www.cricbuzz.com");
        System.out.println("WishController.getWish :: End");
        return mav;
    }

}