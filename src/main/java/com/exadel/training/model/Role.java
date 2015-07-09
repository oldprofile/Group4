package com.exadel.training.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by HP on 09.07.2015.
 */
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private boolean admin;

    @NotNull
    private boolean employee;

    @NotNull
    private boolean exCoach;

    public Role() {}

    public Role(long id){
        this.id = id;
    }

    public long getId () {
        return id;
    }

    public boolean getAdminTypeID () {
        return admin;
    }

    public boolean getEmlpoyeeTypeID () {
        return employee;
    }

    public boolean getExCoachTypeID () {
        return exCoach;
    }
}
