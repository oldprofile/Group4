package com.exadel.training.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @JsonManagedReference
    private Set<Role> roles;

    @NotNull
    private String name;

    @NotNull
    @Column(unique = true)
    private String login;

    private long password;

    @OneToMany(mappedBy = "user")
    private List<UserFeedback> userFeedbacks;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Training> trainings;


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

    public List<UserFeedback> getUserFeedbacks() {
        return userFeedbacks;
    }

    public void setUserFeedbacks(List<UserFeedback> userFeedbacks) {
        this.userFeedbacks = userFeedbacks;
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
        if (!userFeedbacks.equals(user.userFeedbacks)) return false;

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
        result = 31 * result + userFeedbacks.hashCode();
        return result;
    }
}