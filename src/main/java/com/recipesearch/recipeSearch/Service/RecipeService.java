package com.recipesearch.recipeSearch.Service;

import com.recipesearch.recipeSearch.Model.Recipe;
import com.recipesearch.recipeSearch.Repository.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    @Autowired
    RecipeRepo recipeRepo;

    public Recipe getOne(String name){
        return recipeRepo.findByName(name);
    }
}
