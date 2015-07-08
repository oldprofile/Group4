package com.exadel.training.controller;

import com.exadel.training.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by HP on 07.07.2015.
 */
@Controller
public class SampleController {

    @Autowired
    UserService userService;

    @RequestMapping(value="/test", method = RequestMethod.GET)
    @ResponseBody
    public String Get() {
        String s = String.valueOf(userService.getUserByID(1L).getId());
        return s;
    }
    @RequestMapping("/")
    @ResponseBody
    public String index() {
       // System.out.println(service.getUserByID(1).getId());
        return "Proudly handcrafted by " +
                "<a href='http://netgloo.com/en'>netgloo</a> :)";
    }
}
