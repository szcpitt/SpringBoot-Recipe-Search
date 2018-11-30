package com.recipesearch.recipeSearch.Controller;

import com.recipesearch.recipeSearch.Model.Recipe;
import com.recipesearch.recipeSearch.Service.RecipeService;
import com.recipesearch.recipeSearch.Utils.Cons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(Cons.ROOT_PATH+"/recipe")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @GetMapping("/getRecipe")
    public List<Recipe> getRecipe(@RequestParam(value="name") String name){
        return recipeService.getOne(name);
    }

    @GetMapping("/getRecipeList")
    public List<Recipe> getRecipeList(@RequestParam(value="name") String name){
        String[] queries=name.split(" ");
        List<Recipe> recipes;
        if(queries.length==1){
            recipes=recipeService.getOneWord(name);
        }else{
            recipes=recipeService.getWords(queries);
        }
        return recipes;
    }


}
