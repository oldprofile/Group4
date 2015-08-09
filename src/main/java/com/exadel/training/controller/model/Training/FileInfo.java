package com.exadel.training.controller.model.Training;

import com.exadel.training.model.EntityFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Клим on 09.08.2015.
 */
public class FileInfo {
    private String name;
    private String dropboxLink;
    private String link;
    private String trainingName;
    private String data;

    public FileInfo() {
    }

    public FileInfo(EntityFile file) {
        name = file.getName();
        link = file.getDropboxLink();
    }

    public FileInfo(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public static List<FileInfo> parseToList(List<EntityFile> files) {
        List<FileInfo> fileInfos = new ArrayList<>();
        for(EntityFile file: files)
            fileInfos.add(new FileInfo(file));
        return fileInfos;
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

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
