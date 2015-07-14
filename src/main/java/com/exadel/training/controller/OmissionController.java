package com.exadel.training.controller;

import com.exadel.training.model.Omission;
import com.exadel.training.model.Training;
import com.exadel.training.service.OmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Клим on 13.07.2015.
 */
@Controller
public class OmissionController {
    @Autowired
    OmissionService omissionService;

    @RequestMapping(value = "/englishOmission", method = RequestMethod.GET)
    @ResponseBody
    List<Omission> trainingList() {
        List<Omission> list = omissionService.getOmissionsByTrainingName("english");
        return list;
    }
}
