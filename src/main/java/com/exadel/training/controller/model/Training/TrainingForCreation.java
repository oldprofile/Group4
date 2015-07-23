package com.exadel.training.controller.model.Training;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by Клим on 14.07.2015.
 */
public class TrainingForCreation {

    private String name;
    private String userLogin;
    private String description;
    private int idCategory;
    private int participantsNumber;
    private String pictureLink;
    private String additional;
    private String audience;
    private String language;
    private boolean isInternal;
    private List<String> dateTimes;

    public TrainingForCreation() {
    }

    public static String createFile(String fileData, String filePath ,String fileName) throws IOException {
        int typeBegin = 11;
        int typeEnd = 15;
        int fileBegin;
        int i;
        for ( i = 0; fileData.charAt(i) != ','; ++i){
            if (fileData.charAt(i) == '/')
                typeBegin = i + 1;
            else
            if (fileData.charAt(i) == ';')
                typeEnd = i;
        }
        fileBegin = i + 1;
        String fileType = fileData.substring(typeBegin, typeEnd);
        String pictureString = fileData.substring(fileBegin);
        byte[] data = Base64.decodeBase64(pictureString);
        String fileLink = System.getProperty("user.dir") + filePath + fileName +  "." + fileType;
        FileOutputStream imageOutFile = new FileOutputStream(fileLink);
        imageOutFile.write(data);
        imageOutFile.close();
        return fileLink;
    }

    public TrainingForCreation(JSONObject json) throws NoSuchFieldException, IOException {
        JSONArray jsonDates = (JSONArray) json.get("dateTime");
        dateTimes = new ArrayList<>();
        for (Object jsonDate : jsonDates) {
            dateTimes.add((String) jsonDate);
        }
        isInternal = (Boolean)json.get("isInternal");
        audience = (String)json.get("audience");
        participantsNumber = Integer.parseInt(String.valueOf(json.get("participantsNumber")));
        additional = (String)json.get("additional");
        name = (String)json.get("name");
        description = (String)json.get("description");
        language = (String)json.get("language");
        idCategory = Integer.parseInt(String.valueOf(json.get("idCategory")));
        String pictureData = (String)json.get("pictureLink");
        pictureLink = createFile(pictureData, "\\src\\main\\resources\\imageStorage\\", name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public int getParticipantsNumber() {
        return participantsNumber;
    }

    public void setParticipantsNumber(int participantsNumber) {
        this.participantsNumber = participantsNumber;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isInternal() {
        return isInternal;
    }

    public void setIsInternal(boolean isInternal) {
        this.isInternal = isInternal;
    }

    public List<String> getDateTimes() {
        return dateTimes;
    }

    public void setDateTimes(List<String> dateTimes) {
        this.dateTimes = dateTimes;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }
}
