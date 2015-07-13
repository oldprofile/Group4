package com.exadel.training.controller;

import com.exadel.training.model.User;
import com.exadel.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by HP on 13.07.2015.
 */
@Controller
@RequestMapping("/user_controller")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/role", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody User get() {
        return new User();
    }
}
