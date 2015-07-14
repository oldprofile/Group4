package com.exadel.training.model;

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

    @Column(unique = true)
    @NotNull
    private String name;

    private Date date;

    private String pictureLink;

    private String description;

    private String place;

    private int amount;

    private String language;

    private boolean isInternal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private User coach;

    @ManyToMany(mappedBy = "trainings")
    @JsonBackReference
    private List<User> users;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    @ManyToOne(cascade = CascadeType.ALL)
    private State state;

    @OneToMany(cascade = CascadeType.ALL)
    private List<User> spareUsers;

    @OneToMany(mappedBy = "training")
    @JsonBackReference
    private List<Omission> omissions;

    private long parent;

    public Training() {
    }

    public Training(long id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Training training = (Training) o;

        if (id != training.id) return false;
        if (amount != training.amount) return false;
        if (isInternal != training.isInternal) return false;
        if (parent != training.parent) return false;
        if (name != null ? !name.equals(training.name) : training.name != null) return false;
        if (date != null ? !date.equals(training.date) : training.date != null) return false;
        if (pictureLink != null ? !pictureLink.equals(training.pictureLink) : training.pictureLink != null)
            return false;
        if (description != null ? !description.equals(training.description) : training.description != null)
            return false;
        if (place != null ? !place.equals(training.place) : training.place != null) return false;
        if (language != null ? !language.equals(training.language) : training.language != null) return false;
        if (coach != null ? !coach.equals(training.coach) : training.coach != null) return false;
        if (users != null ? !users.equals(training.users) : training.users != null) return false;
        if (category != null ? !category.equals(training.category) : training.category != null) return false;
        if (state != null ? !state.equals(training.state) : training.state != null) return false;
        if (spareUsers != null ? !spareUsers.equals(training.spareUsers) : training.spareUsers != null) return false;
        return !(omissions != null ? !omissions.equals(training.omissions) : training.omissions != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (pictureLink != null ? pictureLink.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + amount;
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (isInternal ? 1 : 0);
        result = 31 * result + (coach != null ? coach.hashCode() : 0);
        result = 31 * result + (users != null ? users.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (spareUsers != null ? spareUsers.hashCode() : 0);
        result = 31 * result + (omissions != null ? omissions.hashCode() : 0);
        result = 31 * result + (int) (parent ^ (parent >>> 32));
        return result;
    }
}