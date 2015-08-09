package com.exadel.training.model;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.SystemUtils;

import javax.persistence.*;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Клим on 06.08.2015.
 */
@Entity
@Table(name="files")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String link;

    @ManyToOne(cascade = CascadeType.ALL)
    private Training training;

    public File() {
    }

    public File(String link, Training training) {
        this.link = link;
        this.training = training;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }
}
