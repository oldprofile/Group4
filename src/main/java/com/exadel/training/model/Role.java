package com.exadel.training.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by HP on 09.07.2015.
 */
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    private Set<User> listUser;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<User> getListUser() {
        return listUser;
    }

    public void setListUser(Set<User> listUser) {
        this.listUser = listUser;
    }

    public void addUser(User user) {
        this.listUser.add(user);
    }
}
