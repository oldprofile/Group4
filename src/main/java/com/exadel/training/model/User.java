package com.exadel.training.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * Created by HP on 08.07.2015.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(unique = true)
    private String email;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Role> roles;

    @NotNull
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    private String name;

    @NotNull
    @Column(unique = true)
    private String login;

    @NotNull
    private long password;

    private String numberPhone;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<UserFeedback> feedbacksOnUser;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Training> trainings;

    @OneToMany(mappedBy = "user")
    private  List<Omission> omissions;

    @OneToMany(mappedBy = "coach")
    private  List<Training> ownTrainings;

    @OneToMany(mappedBy = "feedbacker")
    private List<UserFeedback> userFeedback;

    @OneToMany(mappedBy = "feedbacker")
    private List<TrainingFeedback> trainingFeedback;

    @ManyToMany(mappedBy = "spareUsers")
    private  List<Training> spareTrainings;

    public User() {
    }

    public User(long id) {
        this.id = id;
    }

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getPassword() {
        return password;
    }

    public void setPassword(long password) {
        this.password = password;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    public List<UserFeedback> getFeedbacksOnUser() {
        return feedbacksOnUser;
    }

    public void setFeedbacksOnUser(List<UserFeedback> feedbacksOnUser) {
        this.feedbacksOnUser = feedbacksOnUser;
    }

    public List<Omission> getOmissions() {
        return omissions;
    }

    public void setOmissions(List<Omission> omissions) {
        this.omissions = omissions;
    }

    public List<Training> getOwnTrainings() {
        return ownTrainings;
    }

    public void setOwnTrainings(List<Training> ownTrainings) {
        this.ownTrainings = ownTrainings;
    }

    public List<UserFeedback> getUserFeedback() {
        return userFeedback;
    }

    public void setUserFeedback(List<UserFeedback> userFeedback) {
        this.userFeedback = userFeedback;
    }

    public List<TrainingFeedback> getTrainingFeedback() {
        return trainingFeedback;
    }

    public void setTrainingFeedback(List<TrainingFeedback> trainingFeedback) {
        this.trainingFeedback = trainingFeedback;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public List<Training> getSpareTrainings() {
        return spareTrainings;
    }

    public void setSpareTrainings(List<Training> spareTrainings) {
        this.spareTrainings = spareTrainings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (password != user.password) return false;
        if (!email.equals(user.email)) return false;
        if (!login.equals(user.login)) return false;
        if (!name.equals(user.name)) return false;
        if (!roles.equals(user.roles)) return false;
        if (!feedbacksOnUser.equals(user.feedbacksOnUser)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + email.hashCode();
        result = 31 * result + roles.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + (int) (password ^ (password >>> 32));
        result = 31 * result + feedbacksOnUser.hashCode();
        return result;
    }
}