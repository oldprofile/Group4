package com.exadel.training.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by HP on 07.07.2015.
 */
@Controller
public class SampleController {

    @RequestMapping(value="/test", method = RequestMethod.GET)
    @ResponseBody
    public String Get() {
        return "Hello World!";
    }
    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "Proudly handcrafted by " +
                "<a href='http://netgloo.com/en'>netgloo</a> :)";
    }
}
