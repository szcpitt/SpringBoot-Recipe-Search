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
    List<Recipe> findByName(String name);

    List<Recipe> findRecipesByNameContaining(String name);

    List<Recipe> findRecipeByIngredientsContains(String ingredients);

    List<Recipe> findAll();
//    Optional<Recipe> findById(Integer id);

    Recipe findById(int id);

}
