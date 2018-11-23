package com.recipesearch.recipeSearch.Service;

import com.recipesearch.recipeSearch.Model.Recipe;
import com.recipesearch.recipeSearch.Repository.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {

    @Autowired
    RecipeRepo recipeRepo;

    public List<Recipe> getOne(String name){
        return recipeRepo.findByName(name);
    }

    // TODO: Not Implemented!
    public List<Recipe> getOneWord(String name) {
        return recipeRepo.findRecipesByNameContaining(name);
    }

    // TODO: Need score rank!
    public List<Recipe> getWords(String[] words){
        return new ArrayList<>();
    }

    public List<Recipe> retrieveQuery(){
        return null;
    }
}
