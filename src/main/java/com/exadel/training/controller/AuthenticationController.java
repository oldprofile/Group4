package com.exadel.training.controller;

import com.exadel.training.TokenAuthentification.CryptService;
import com.exadel.training.TokenAuthentification.impl.DESCryptServiceImpl;
import com.exadel.training.TokenAuthentification.impl.DecoratorDESCryptServiceImpl;
import com.exadel.training.controller.model.Authentication;
import com.exadel.training.model.User;
import com.exadel.training.service.RoleService;
import com.exadel.training.service.UserService;
import com.twilio.sdk.TwilioRestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by HP on 10.07.2015.
 */
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    private static final Object EMPTY = null;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    private CryptService cryptService;

    public AuthenticationController() {
        try {
            cryptService = new DecoratorDESCryptServiceImpl(new DESCryptServiceImpl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/log_password", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody Authentication save(@RequestBody Authentication project, HttpServletResponse httpServletResponse) {
        String login = project.getLogin();
        Long password = project.getPassword();

         User user = userService.findUserByLoginAndPassword(login, password);
         httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
            try {
                httpServletResponse.setHeader("token", cryptService.encrypt(login));
            } catch (UnsupportedEncodingException | BadPaddingException | IllegalBlockSizeException e) {
                e.printStackTrace();
            }
        return Authentication.parseAuthentication(user);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST,  consumes = "application/json")
    public void logout(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException {
        String header = httpServletRequest.getHeader("authorization");
        String login = cryptService.decrypt(header);

        if(userService.checkUserByLogin(login)) {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public @ResponseBody Authentication get() throws TwilioRestException {
        // Role role = roleService.getRoleByID(1);
        User user = userService.findUserByLoginAndPassword("1", 1l);
        return Authentication.parseAuthentication(user);
    }
}
