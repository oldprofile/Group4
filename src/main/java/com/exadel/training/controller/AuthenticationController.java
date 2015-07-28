package com.exadel.training.controller;

import com.exadel.training.controller.model.Authentication;
import com.exadel.training.model.User;
import com.exadel.training.service.RoleService;
import com.exadel.training.service.UserService;
import com.exadel.training.tokenAuthentification.CryptService;
import com.exadel.training.tokenAuthentification.SessionToken;
import com.twilio.sdk.TwilioRestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Autowired(required = true)
    @Qualifier(value = "decoratorDESCryptServiceImpl")
    private CryptService cryptService;
    @Autowired
    private SessionToken sessionToken;


    @RequestMapping(value = "/log_password", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody Authentication save(@RequestBody Authentication project, HttpServletResponse httpServletResponse) {
        String login = project.getLogin();
        Long password = project.getPassword();
        User user = new User();
         try {
             user = userService.findUserByLoginAndPassword(login, password);
         }catch (NullPointerException e){
             httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
         }
         httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
            try {
                String token = cryptService.encrypt(login);
                httpServletResponse.setHeader("token", token);
                sessionToken.addToken(login,token);
            } catch (UnsupportedEncodingException | BadPaddingException | IllegalBlockSizeException e) {
                e.printStackTrace();
            }
        return Authentication.parseAuthentication(user);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST,  consumes = "application/json")
    public void logout(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws BadPaddingException, IOException, IllegalBlockSizeException {
        String header = httpServletRequest.getHeader("authorization");
        String login = httpServletRequest.getHeader("login");

        if(sessionToken.containsToken(header)) {
            sessionToken.deleteToken(login, header);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public @ResponseBody Authentication get(HttpServletRequest httpServletRequest) throws TwilioRestException {
        // Role role = roleService.getRoleByID(1);
        User user = userService.findUserByLoginAndPassword("1", 1l);
        String header = httpServletRequest.getHeader("authorization");
        String login = httpServletRequest.getHeader("login");
        sessionToken.deleteToken(login, header);
        return Authentication.parseAuthentication(user);
    }
}
