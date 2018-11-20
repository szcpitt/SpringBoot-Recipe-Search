package com.recipesearch.reciepesearch.Service;

import com.recipesearch.reciepesearch.Model.Recipe;
import com.recipesearch.reciepesearch.Repository.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    RecipeRepo recipeRepo;

    public List<Recipe> getOne(String name){
        return recipeRepo.findByName(name);
    }
}
