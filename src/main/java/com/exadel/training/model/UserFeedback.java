package com.exadel.training.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

/**
 * Created by asd on 09.07.2015.
 */
@Entity
@Table(name = "ufeedbacks")
public class UserFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String attendance;

    private String attitude;

    private String commSkills;

    private String questions;

    private String motivation;

    private String focusOnResult;

    private String other;

    @NotNull
    private Date date;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "ufeedbacks_users",
            joinColumns = @JoinColumn(name = "feedbacker_id"))
    private User feedbacker;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "ufeedbacks_users",
            joinColumns = @JoinColumn(name = "user_userFeedback"))
    private User user;

    // for english
    //private assessment;
    //private level;


    public long getId() {
        return id;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getCommSkills() {
        return commSkills;
    }

    public void setCommSkills(String commSkills) {
        this.commSkills = commSkills;
    }

    public String getAttitude() {
        return attitude;
    }

    public void setAttitude(String attitude) {
        this.attitude = attitude;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getFocusOnResult() {
        return focusOnResult;
    }

    public void setFocusOnResult(String focusOnResult) {
        this.focusOnResult = focusOnResult;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getFeedbacker() {
        return feedbacker;
    }

    public void setFeedbacker(User feedbacker) {
        this.feedbacker = feedbacker;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
