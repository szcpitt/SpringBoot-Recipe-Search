package com.recipesearch.recipeSearch.Repository;

import com.recipesearch.recipeSearch.Model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RecipeRepo extends MongoRepository<Recipe,Integer> {
    Recipe findByName(String name);

    List<Recipe> findByNameContaining(String name);
}
