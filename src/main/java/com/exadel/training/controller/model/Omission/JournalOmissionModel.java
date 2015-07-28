package com.exadel.training.controller.model.Omission;

import com.exadel.training.model.Omission;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by HP on 17.07.2015.
 */
public class JournalOmissionModel {

    private String date;
    private Boolean isOmission;

    public JournalOmissionModel() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.date = sdf.format(date);
    }

    public Boolean getIsOmission() {
        return isOmission;
    }

    public void setIsOmission(Boolean isOmission) {
        this.isOmission = isOmission;
    }

    public static JournalOmissionModel parseJournalOmissionUserByTraining(Omission omission) {
        JournalOmissionModel journalOmissionModel = new JournalOmissionModel();
        journalOmissionModel.setIsOmission(omission.isOmission());
        journalOmissionModel.setDate(omission.getTraining().getDateTime());

        return journalOmissionModel;
    }

    public static List<JournalOmissionModel> parseListOfOmissions (List <Omission> omissions) {
        List<JournalOmissionModel> journalOmissionModels = new ArrayList<JournalOmissionModel>();
        for(Omission omission: omissions) {
            journalOmissionModels.add(JournalOmissionModel.parseJournalOmissionUserByTraining(omission));
        }
        return journalOmissionModels;
    }
}
