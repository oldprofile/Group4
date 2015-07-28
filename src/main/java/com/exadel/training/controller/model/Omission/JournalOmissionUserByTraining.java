package com.exadel.training.controller.model.Omission;

import com.exadel.training.model.Omission;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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

    public static List<JournalOmissionUserByTraining> parseListOfOmissions (List <Omission> omissions) {
        List<JournalOmissionUserByTraining> journalOmissionUserByTrainings = new ArrayList<JournalOmissionUserByTraining>();
        for(Omission omission: omissions) {
            journalOmissionUserByTrainings.add(JournalOmissionUserByTraining.parseJournalOmissionUserByTraining(omission));
        }
        return journalOmissionUserByTrainings;
    }
}
