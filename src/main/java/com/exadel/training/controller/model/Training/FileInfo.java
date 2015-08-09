package com.exadel.training.controller.model.Training;

import com.exadel.training.model.File;

/**
 * Created by Клим on 09.08.2015.
 */
public class FileInfo {
    private String name;
    private String link;

    public FileInfo() {
    }

    public FileInfo(File file) {
        name = file.getName();
        link = file.getLink();
    }

    public FileInfo(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
