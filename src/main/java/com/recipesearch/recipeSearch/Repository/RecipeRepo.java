package com.recipesearch.recipeSearch.Repository;

import com.recipesearch.recipeSearch.Model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepo extends MongoRepository<Recipe,Integer> {
    List<Recipe> findByNameIgnoreCase(String name);

    List<Recipe> findRecipesByNameContainingIgnoreCase(String name);

    List<Recipe> findRecipeByIngredientsContainsIgnoreCase(String ingredients);

    List<Recipe> findAll();
//    Optional<Recipe> findById(Integer id);

    Recipe findRecipeById(int id);

}
