package com.exadel.training.controller;

import com.exadel.training.controller.model.Authentication;
import com.exadel.training.model.User;
import com.exadel.training.service.RoleService;
import com.exadel.training.service.UserService;
import com.twilio.sdk.TwilioRestException;
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

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/log_password", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody Authentication save(@RequestBody Authentication project) {
        User user = userService.findUserByLoginAndPassword(project.getLogin(), project.getPassword());
        return Authentication.parseAuthentication(user);
    }
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public @ResponseBody Authentication get() throws TwilioRestException {
        // Role role = roleService.getRoleByID(1);
        User user = userService.findUserByLoginAndPassword("1", 1l);
        return Authentication.parseAuthentication(user);
    }
}
