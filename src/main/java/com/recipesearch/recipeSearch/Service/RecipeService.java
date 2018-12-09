package com.recipesearch.recipeSearch.Service;

import com.recipesearch.recipeSearch.Model.Recipe;
import com.recipesearch.recipeSearch.Repository.RecipeRepo;
import com.recipesearch.recipeSearch.Utils.Query.ExtractQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class RecipeService {

    @Autowired
    RecipeRepo recipeRepo;

    @Autowired
    QueryRetrievalService model;
    public List<Recipe> getOne(String name){
        return recipeRepo.findByNameIgnoreCase(name);
    }


    public List<Recipe> getOneWord(String name) {
        return recipeRepo.findRecipesByNameContainingIgnoreCase(name);
    }

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

    public List<Recipe> getCal(double total_cal){
        if(total_cal < 800)
            total_cal = 800;
        else if (total_cal > 3000)
            total_cal = 3000;
        double dinner= total_cal * 0.3;
        double lunch = total_cal * 0.4;
        double salad = total_cal * 0.2;
        double gap = total_cal * 0.005;
        List<Recipe> results = new ArrayList<>();
        Set<Recipe> dinnerset = recipeRepo.findByTotalCalBetween(dinner - gap, dinner);
        Set<Recipe> lunchset = recipeRepo.findByTotalCalBetween(lunch - gap, lunch);
        Set<Recipe> saladset = recipeRepo.findByTotalCalBetween(salad - 10 * gap, salad);
        List<Recipe> dinners = new ArrayList<>();
        List<Recipe> lunchs = new ArrayList<>();
        List<Recipe> salads = new ArrayList<>();
        Iterator it = dinnerset.iterator();
        while(it.hasNext()){
            Recipe recipe = (Recipe)it.next();
            if(recipe.getCourse().contains("Main Dishes"))
                dinners.add(recipe);
        }

        Iterator it2 = lunchset.iterator();
        while(it2.hasNext()){
            Recipe recipe = (Recipe)it2.next();
            if(recipe.getCourse().contains("Main Dishes") || recipe.getCourse().contains("Lunch and Snacks"))
                lunchs.add(recipe);
        }

        Iterator it3 = saladset.iterator();
        while(it3.hasNext()){
            Recipe recipe = (Recipe)it3.next();
            if(recipe.getCourse().contains("Salads"))
                salads.add(recipe);
        }

        Collections.sort(dinners, new Comparator<Recipe>() {
            @Override
            public int compare(Recipe o1, Recipe o2) {
                return Double.compare(o2.getTotalCal(),o1.getTotalCal());
            }
        });

        Collections.sort(lunchs, new Comparator<Recipe>() {
            @Override
            public int compare(Recipe o1, Recipe o2) {
                return Double.compare(o2.getTotalCal(),o1.getTotalCal());
            }
        });


        if(lunchs.size() > 30)
            lunchs = lunchs.subList(0,30);
        if(dinners.size() > 30)
            dinners = dinners.subList(0,30);

        double l = Math.floor(Math.random() * lunchs.size());
        double d = Math.floor(Math.random() * lunchs.size());
        double s = Math.floor(Math.random() * salads.size());

        results.add(salads.get((int)s));
        results.add(lunchs.get((int)l));
        results.add(dinners.get((int)d));

        return results;
    }

    public boolean isNumeric(String str){
        int flag = 1;
        for(int i = 0;i<str.length();i++){
            if(str.charAt(i) == '.' && flag == 1)
                flag = 0;
            else if(str.charAt(i) < '0' || str.charAt(i) > '9')
                return false;
        }
        return true;
    }
}
