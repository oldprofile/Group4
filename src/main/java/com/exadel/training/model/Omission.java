package com.exadel.training.model;

import javax.persistence.*;

/**
 * Created by Клим on 13.07.2015.
 */
@Entity
@Table(name= "omissions")
public class Omission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Training training;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    private boolean isOmission;

    public Omission() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isOmission() {
        return isOmission;
    }

    public void setOmission(boolean isOmission) {
        this.isOmission = isOmission;
    }
}
