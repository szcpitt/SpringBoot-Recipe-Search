package com.recipesearch.recipeSearch.Service;

import com.recipesearch.recipeSearch.Model.Recipe;
import com.recipesearch.recipeSearch.Repository.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    RecipeRepo recipeRepo;

    public Recipe getOne(String name){
        return recipeRepo.findByName(name);
    }

    public List<Recipe> getRecipeList(String name){
        return recipeRepo.findByNameContaining(name);
    }
}
