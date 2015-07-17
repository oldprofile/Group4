package com.exadel.training.controller.model.Omission;

import com.exadel.training.model.Omission;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by HP on 17.07.2015.
 */
public class JournalOmissionByTraining {

    private String login;
    private Boolean isOmission;
    private String date;

    public JournalOmissionByTraining() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Boolean getIsOmission() {
        return isOmission;
    }

    public void setIsOmission(Boolean isOmission) {
        this.isOmission = isOmission;
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        this.date = sdf.format(date);
    }

    public static JournalOmissionByTraining parseJournalOmissionByTraining(Omission omission) {
        JournalOmissionByTraining journalOmissionByTraining = new JournalOmissionByTraining();
        journalOmissionByTraining.setLogin(omission.getUser().getLogin());
        journalOmissionByTraining.setDate(omission.getTraining().getDateTime());
        journalOmissionByTraining.setIsOmission(omission.isOmission());

        return journalOmissionByTraining;
    }
}
