package com.exadel.training.controller.model.Omission;

/**
 * Created by Yoga 3 Pro on 04.08.2015.
 */
public class PathToStatistics {
    String path;
    String dropboxLink;

    public PathToStatistics() {

    }

    public PathToStatistics(String path, String dropboxLink) {
        this.path = path;
        this.dropboxLink = dropboxLink;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDropboxLink() {
        return dropboxLink;
    }

    public void setDropboxLink(String dropboxLink) {
        this.dropboxLink = dropboxLink;
    }
}
