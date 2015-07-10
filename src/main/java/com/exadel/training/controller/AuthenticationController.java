package com.exadel.training.controller;

import com.exadel.training.common.Authentication;
import com.exadel.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by HP on 10.07.2015.
 */
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/log_password", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Authentication save(@RequestBody Authentication project)  {
        System.out.println(project.getParole());
        return project;
    }
}
