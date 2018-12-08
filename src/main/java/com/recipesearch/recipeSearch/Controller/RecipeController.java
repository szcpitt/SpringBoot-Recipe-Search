package com.recipesearch.recipeSearch.Controller;

import com.recipesearch.recipeSearch.Model.Recipe;
import com.recipesearch.recipeSearch.Service.CacheService;
import com.recipesearch.recipeSearch.Service.RecipeService;
import com.recipesearch.recipeSearch.Utils.Cons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(Cons.ROOT_PATH+"/recipe")
public class RecipeController {

    @Autowired
    RecipeService recipeService;
    @Autowired
    CacheService cacheService;

    @GetMapping("/getRecipe")
    public Map<String,List<Recipe>> getRecipe(@RequestParam(value="name") String name){
        HashMap<String, List<Recipe>> map = new HashMap<>();
        map.put("Search",recipeService.getOne(name));
        return map;
    }

    @GetMapping("/getRecipeList")
    public Map<String,List<Recipe>> getRecipeList(@RequestParam(value="name") String name) throws Exception{
        String[] queries=name.split(" ");
        List<Recipe> recipes;
        if(name.charAt(0) == ':' || name.charAt(0) == '：'){
            name = name.substring(1,name.length());
            double total_cal = Double.parseDouble(name);

            recipes = recipeService.getCal(total_cal);
        }
        else{
            if(queries.length==1){
                recipes=recipeService.getOneWord(name);
            }else{
                recipes=recipeService.getWords(name+" ");   // in case of OutOfIndex, problem of WordTokenizer;
            }
        }
        HashMap<String,List<Recipe>> map = new HashMap<>();
        map.put("Search",recipes);
        return map;
    }

}
