package com.recipesearch.reciepesearch.Controller;

import com.recipesearch.reciepesearch.Model.Recipe;
import com.recipesearch.reciepesearch.Service.RecipeService;
import com.recipesearch.reciepesearch.Utils.Cons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
