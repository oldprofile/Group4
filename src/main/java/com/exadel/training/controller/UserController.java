package com.exadel.training.controller;

import com.exadel.training.common.RoleType;
import com.exadel.training.model.User;
import com.exadel.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by HP on 13.07.2015.
 */
@Controller
@RequestMapping("/user_controller")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public @ResponseBody List<User> get() throws NoSuchFieldException {
        return userService.findUserByRole(RoleType.Admin);
    }
}
