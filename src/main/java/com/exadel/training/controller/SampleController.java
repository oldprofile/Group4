package com.exadel.training.controller;

import com.exadel.training.service.RoleService;
import com.exadel.training.service.UserService;
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

    @Autowired
    RoleService roleService;

    @RequestMapping(value="/test", method = RequestMethod.GET)
    @ResponseBody
    public String Get() {
        String s = String.valueOf(userService.getUserByID(1L).getId());
        String s1 = String.valueOf(roleService.getRoleByID(1L).getId());
        return s + s1;
    }
    @RequestMapping("/")
    @ResponseBody
    public String index() {
       // System.out.println(service.getUserByID(1).getId());
        return "Proudly handcrafted by " +
                "<a href='http://netgloo.com/en'>netgloo</a> zsdfsdf:)";
    }
}
