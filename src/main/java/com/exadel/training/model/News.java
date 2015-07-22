package com.exadel.training.model;

import javax.persistence.*;

/**
 * Created by HP on 23.07.2015.
 */
@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

   // @ManyToOne(cascade = CascadeType.ALL)
  //  private Training training;

    private String action;

    public News(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
