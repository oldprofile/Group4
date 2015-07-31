package com.exadel.training.controller.model.Training;

import com.exadel.training.model.Category;
import com.exadel.training.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Клим on 14.07.2015.
 */
public class CategoryShort {

    private long id;
    private String name;
    private int trainingsNumber;
    private String pictureLink;

    public CategoryShort() {
    }

    public CategoryShort(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.pictureLink = category.getPictureLink();
    }

    public static List<CategoryShort> parseListCategoryShort(List<Category> categories) {
        List<CategoryShort> categoriesShorts = new ArrayList<>();
        for (Category category : categories) {
            CategoryShort categoryShort = new CategoryShort(category);
            //categoryShort.trainingsNumber = trainingService.getValidTrainingsNumberByCategory(category);
            categoriesShorts.add(categoryShort);
        }
        return categoriesShorts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTrainingsNumber() {
        return trainingsNumber;
    }

    public void setTrainingsNumber(int trainingsNumber) {
        this.trainingsNumber = trainingsNumber;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }
}
