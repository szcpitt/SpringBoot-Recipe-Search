package com.recipesearch.recipeSearch.Service;


import com.recipesearch.recipeSearch.Model.Recipe;
import com.recipesearch.recipeSearch.Repository.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CacheService {
    @Autowired
    RecipeService recipeService;
    public void buildCache(){
        List<Recipe> documentList = recipeService.getAll();
    }
}
