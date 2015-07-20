package com.exadel.training.controller.model.Omission;

import com.exadel.training.model.Omission;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by HP on 17.07.2015.
 */
public class JournalOmissionUserByTraining {

    private String date;
    private Boolean isOmission;

    public JournalOmissionUserByTraining() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        this.date = sdf.format(date);
    }

    public Boolean getIsOmission() {
        return isOmission;
    }

    public void setIsOmission(Boolean isOmission) {
        this.isOmission = isOmission;
    }

    public static JournalOmissionUserByTraining parseJournalOmissionUserByTraining(Omission omission) {
        JournalOmissionUserByTraining journalOmissionUserByTraining = new JournalOmissionUserByTraining();
        journalOmissionUserByTraining.setIsOmission(omission.isOmission());
        journalOmissionUserByTraining.setDate(omission.getTraining().getDateTime());

        return journalOmissionUserByTraining;
    }
}
