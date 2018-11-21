package com.recipesearch.recipeSearch.Repository;

import com.recipesearch.recipeSearch.Model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecipeRepo extends MongoRepository<Recipe,Integer> {
    Recipe findByName(String name);
}
