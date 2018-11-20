package com.recipesearch.reciepesearch.Repository;

import com.recipesearch.reciepesearch.Model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RecipeRepo extends MongoRepository<Recipe,Long> {

    List<Recipe> findByName(String name);
}
