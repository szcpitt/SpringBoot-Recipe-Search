package com.recipesearch.recipeSearch.Service;

import com.recipesearch.recipeSearch.Model.Recipe;
import com.recipesearch.recipeSearch.Repository.RecipeRepo;
import com.recipesearch.recipeSearch.Utils.Query.ExtractQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {

    @Autowired
    RecipeRepo recipeRepo;

    @Autowired
    QueryRetrievalService model;
    public List<Recipe> getOne(String name){
        return recipeRepo.findByNameIgnoreCase(name);
    }

    // TODO: Not Implemented!
    public List<Recipe> getOneWord(String name) {
        return recipeRepo.findRecipesByNameContainingIgnoreCase(name);
    }

    // TODO: Need score rank!
    public List<Recipe> getWords(String query){
        ExtractQuery extractQuery = new ExtractQuery(query);
        String queryAfterStem = extractQuery.getContent();

        List<Recipe> result = new ArrayList<>();
        try {
             result =  model.retrieveQuery(queryAfterStem,20);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    // find all documents
    public Recipe getByID(int id){
        return recipeRepo.findRecipeById(id);
    }
    public List<Recipe> retrieveQuery(){
        return null;
    }
}
