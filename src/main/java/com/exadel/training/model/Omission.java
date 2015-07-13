package com.exadel.training.model;

import javax.persistence.*;

/**
 * Created by Клим on 13.07.2015.
 */
@Entity
@Table(name= "omissions")
public class Omission {

    @ManyToOne(cascade = CascadeType.ALL)
    private Training training;

    @OneToOne(mappedBy = "omissions")
    private User user;

    private boolean isOmission;
}
