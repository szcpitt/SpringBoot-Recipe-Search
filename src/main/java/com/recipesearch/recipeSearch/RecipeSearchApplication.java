package com.recipesearch.recipeSearch;

import com.recipesearch.recipeSearch.Service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;

@SpringBootApplication
public class RecipeSearchApplication {
    @Autowired
    CacheService cacheService;

    public static void main(String[] args) {
        SpringApplication.run(RecipeSearchApplication.class, args);
    }

    @PostConstruct
    public void startBuildCache(){
            cacheService.setCacheService();
    }
}
