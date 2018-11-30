package com.recipesearch.recipeSearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class RecipeSearchApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(RecipeSearchApplication.class, args);
    }

    @PostConstruct
    public static void startBuildCache(){

    }
}
