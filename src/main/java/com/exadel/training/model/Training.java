package com.exadel.training.model;

import com.exadel.training.controller.model.Training.TrainingForCreation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
/**
 * Created by Клим on 10.07.2015.
 */

@Entity
@Table(name= "trainings")
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    private Date dateTime;

    private String pictureLink;

    private String description;

    private String place;

    private int amount;

    private int language;

    private boolean isInternal;

    private long parent;

    @ManyToOne(cascade = CascadeType.ALL)
    private User coach;

    @ManyToMany(mappedBy = "trainings")
    private List<User> listeners;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    //@ManyToOne(cascade = CascadeType.ALL)
    private int state;

    @OneToMany(cascade = CascadeType.ALL)
    private List<User> spareUsers;

    @OneToMany(mappedBy = "training")
    private List<Omission> omissions;

    public Training() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public boolean isInternal() {
        return isInternal;
    }

    public void setIsInternal(boolean isInternal) {
        this.isInternal = isInternal;
    }

    public User getCoach() {
        return coach;
    }

    public void setCoach(User coach) {
        this.coach = coach;
    }

    public List<User> getListeners() {
        return listeners;
    }

    public void setListeners(List<User> listeners) {
        this.listeners = listeners;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getParent() {
        return parent;
    }

    public void setParent(long parent) {
        this.parent = parent;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public List<User> getSpareUsers() {
        return spareUsers;
    }

    public void setSpareUsers(List<User> spareUsers) {
        this.spareUsers = spareUsers;
    }

    public List<Omission> getOmissions() {
        return omissions;
    }

    public void setOmissions(List<Omission> omissions) {
        this.omissions = omissions;
    }

}