package com.exadel.training.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by asd on 09.07.2015.
 */

@Entity
@Table(name = "tfeedbacks")
public class TrainingFeedback{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private boolean clear;

    private boolean interesting;

    private boolean newMaterial;

    private int effective;

    private boolean recommendation;

    private String other;

    @ManyToOne(cascade = CascadeType.ALL)
    private User feedbacker;

    @NotNull
    private Date date;

    @ManyToOne(cascade = CascadeType.ALL)
    private Training training;

    public TrainingFeedback() {
        this.date = new Date();
    }

    public TrainingFeedback(boolean clear, boolean interesting, boolean newMaterial, int effective, boolean recommendation,
                            String other, User feedbacker, Training training) {
        this.clear = clear;
        this.interesting = interesting;
        this.newMaterial = newMaterial;
        this.effective = effective;
        this.recommendation = recommendation;
        this.other = other;
        this.feedbacker = feedbacker;
        this.training = training;
        this.date = new Date();
    }

    public long getId() {
        return id;
    }

    public boolean getClear() {
        return clear;
    }

    public void setClear(boolean clear) {
        this.clear = clear;
    }

    public boolean getInteresting() {
        return interesting;
    }

    public void setInteresting(boolean interesting) {
        this.interesting = interesting;
    }

    public boolean getNewMaterial() {
        return newMaterial;
    }

    public void setNewMaterial(boolean newMaterial) {
        this.newMaterial = newMaterial;
    }

    public int getEffective() {
        return effective;
    }

    public void setEffective(int effective) {
        this.effective = effective;
    }

    public boolean getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(boolean recommendation) {
        this.recommendation = recommendation;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public User getFeedbacker() {
        return feedbacker;
    }

    public void setFeedbacker(User feedbacker) {
        this.feedbacker = feedbacker;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }
}
