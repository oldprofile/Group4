package com.exadel.training.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @JsonManagedReference
    private Training training;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private User user;

    private boolean isOmission;
}
