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
    private boolean isOmission;
    private String reason;

    public JournalOmissionModel() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.date = sdf.format(date);
    }

    public boolean getIsOmission() {
        return isOmission;
    }

    public void setIsOmission(boolean isOmission) {
        this.isOmission = isOmission;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public static JournalOmissionModel parseJournalOmissionUserByTraining(Omission omission) {
        JournalOmissionModel journalOmissionModel = new JournalOmissionModel();
        journalOmissionModel.setIsOmission(omission.isOmission());
        journalOmissionModel.setDate(omission.getTraining().getDateTime());
        journalOmissionModel.setReason(omission.getReason());

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
