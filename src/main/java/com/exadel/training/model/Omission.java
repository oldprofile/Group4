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
}
