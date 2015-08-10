package com.exadel.training.controller.model.Training;

import java.util.List;

/**
 * Created by Клим on 10.08.2015.
 */
public class FilesInfoArray {
    private List<FileInfo> files;
    private String trainingName;

    public FilesInfoArray() {
    }

    public List<FileInfo> getFiles() {
        return files;
    }

    public void setFiles(List<FileInfo> files) {
        this.files = files;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }
}
