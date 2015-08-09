package com.exadel.training.controller.model.Training;

import org.apache.commons.lang3.SystemUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by Клим on 14.07.2015.
 */
public class TrainingForCreation {

    private String name;
    private String coachLogin;
    private String description;
    private int idCategory;
    private int participantsNumber;
    private String pictureData;
    private String pictureLink;
    private String audience;
    private String language;
    private boolean isInternal;
    private List<String> places;
    private List<String> filesLinks;
    private List<Date> dateTimes;
    private String additional;
    private boolean isRepeating;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public TrainingForCreation() {
    }

    public static String createFile(String fileData, String filePath ,String fileName) throws IOException {
        int fileBegin;
        int i;
        for ( i = 0; fileData.charAt(i) != ','; ++i){

        }
        fileBegin = i + 1;
        String pictureString = fileData.substring(fileBegin);
        byte[] data = Base64.decodeBase64(pictureString);
        fileName = fileName.replace(" ", "-");
        String fileLink = filePath + fileName;
        String destination = System.getProperty("user.dir") + "\\src\\main\\webapp" + fileLink;
        if(!SystemUtils.IS_OS_WINDOWS)
            destination = destination.replace("\\", "/");
        FileOutputStream imageOutFile = new FileOutputStream(destination);
        fileLink = fileLink.replace("\\", "/");
        imageOutFile.write(data);
        imageOutFile.close();
        return fileLink;
    }

    public TrainingForCreation(JSONObject json) throws NoSuchFieldException, IOException, ParseException {
        dateTimes = new ArrayList<>();
        isRepeating = (Boolean)json.get("isRepeating");
        //isRepeating = true;
        if (isRepeating){
            DateParser dateParser = new DateParser(json);
            dateParser.parseDateTimes();
            dateTimes = dateParser.getDateTimes();
        } else {
            JSONArray jsonDates = (JSONArray) json.get("dateTime");
            for (Object jsonDate : jsonDates) {
                String date = (String) jsonDate;
                if (!date.equals(""))
                    dateTimes.add(sdf.parse(date));
            }
        }

        JSONArray jsonPlaces = (JSONArray) json.get("places");
        places = new ArrayList<>();
        for (Object jsonPlace : jsonPlaces) {
            places.add((String) jsonPlace);
        }

        JSONArray jsonFiles = (JSONArray) json.get("files");
        filesLinks = new ArrayList<>();
        for (Object object : jsonFiles) {
            JSONObject jsonFile = (JSONObject)object;
            String fileName = (String)jsonFile.get("name");
            String fileData = (String)jsonFile.get("data");
            String fileLink = (String)jsonFile.get("link");
            if(fileData == null)
                filesLinks.add(fileLink);
            else {
                fileLink = createFile(fileData,"\\files_storage\\", fileName);
                filesLinks.add(fileLink);
            }
        }

        coachLogin = (String)json.get("coach");
        isInternal = (Boolean)json.get("isInternal");
        audience = (String)json.get("audience");
        additional = (String)json.get("additional");
        participantsNumber = Integer.parseInt(String.valueOf(json.get("participantsNumber")));
        name = (String)json.get("name");
        description = (String)json.get("description");
        language = (String)json.get("language");
        idCategory = Integer.parseInt(String.valueOf(json.get("idCategory")));
        pictureData = (String)json.get("pictureData");
        if (pictureData == null)
            pictureLink = (String)json.get("pictureLink");
        else
            pictureLink = createFile(pictureData, "\\image_storage\\", name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoachLogin() {
        return coachLogin;
    }

    public void setCoachLogin(String coachLogin) {
        this.coachLogin = coachLogin;
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

    public List<Date> getDateTimes() {
        return dateTimes;
    }

    public void setDateTimes(List<Date> dateTimes) {
        this.dateTimes = dateTimes;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public List<String> getPlaces() {
        return places;
    }

    public void setPlaces(List<String> places) {
        this.places = places;
    }

    public String getPictureData() {
        return pictureData;
    }

    public void setPictureData(String pictureData) {
        this.pictureData = pictureData;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public boolean getIsRepeating() {
        return isRepeating;
    }

    public void setIsRepeating(boolean isRepeating) {
        this.isRepeating = isRepeating;
    }

    public List<String> getFilesLinks() {
        return filesLinks;
    }

    public void setFilesLinks(List<String> filesLinks) {
        this.filesLinks = filesLinks;
    }
}
