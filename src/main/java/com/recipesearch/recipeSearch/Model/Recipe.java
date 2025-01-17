package com.recipesearch.recipeSearch.Model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document( collection = "recipe")
public class Recipe {

    @Field("ID")
    private int id;
    @Field("NAME")
    private String name;
    @Field("photo")
    private String photo;
    @Field("TotalTime")
    private String totalTime;
    @Field("Ingredients")
    private String[] ingredients;
    @Field("TotalkCal")
    private double totalCal;
    @Field("FAT_KCAL")
    private double fat_kcal;
    @Field("ENERC_FAT")
    private double enerc_fat;
    @Field("Source_url")
    private String source_url;
    @Field("Course")
    private String course;
    @Field("Cuisine")
    private String cuisine;

    public Recipe() {
    }

    public Recipe(int id, String name, String totalTime, String[] ingredients, double totalCal, double fat_kcal, double enerc_fat, String source_url, String course, String cuisine, String photo) {
        this.id = id;
        this.name = name;
        this.totalTime = totalTime;
        this.ingredients = ingredients;
        this.totalCal = totalCal;
        this.fat_kcal = fat_kcal;
        this.enerc_fat = enerc_fat;
        this.source_url = source_url;
        this.course = course;
        this.cuisine = cuisine;
        this.photo=photo;
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

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public double getTotalCal() {
        return totalCal;
    }

    public void setTotalCal(double totalCal) {
        this.totalCal = totalCal;
    }

    public double getFat_kcal() {
        return fat_kcal;
    }

    public void setFat_kcal(double fat_kcal) {
        this.fat_kcal = fat_kcal;
    }

    public double getEnerc_fat() {
        return enerc_fat;
    }

    public void setEnerc_fat(double enerc_fat) {
        this.enerc_fat = enerc_fat;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
