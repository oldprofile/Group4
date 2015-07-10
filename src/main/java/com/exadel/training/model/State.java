package com.exadel.training.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Клим on 10.07.2015.
 */
@Entity
@Table(name="states")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @OneToMany(mappedBy = "state")
    private List<Training> trainings;

    public State() {
    }

    public State(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }
}
