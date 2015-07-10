package com.exadel.training.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by asd on 09.07.2015.
 */

@Entity
@Table(name = "tfeedbacks")
public class TrainingFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String clear;

    private String interesting;

    private String newMaterial;

    private int effective;

    private String trainer;

    private String recommendation;

    private String other;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "tfeedbacks_users",
            joinColumns = @JoinColumn(name = "feedbacker_id"))
    private User feedbacker;

    @NotNull
    private Date date;


    public long getId() {
        return id;
    }

    public String getClear() {
        return clear;
    }

    public void setClear(String clear) {
        this.clear = clear;
    }

    public String getInteresting() {
        return interesting;
    }

    public void setInteresting(String interesting) {
        this.interesting = interesting;
    }

    public String getNewMaterial() {
        return newMaterial;
    }

    public void setNewMaterial(String newMaterial) {
        this.newMaterial = newMaterial;
    }

    public int getEffective() {
        return effective;
    }

    public void setEffective(int effective) {
        this.effective = effective;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
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

}
