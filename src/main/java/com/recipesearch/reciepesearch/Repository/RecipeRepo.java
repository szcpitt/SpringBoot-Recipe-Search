package com.recipesearch.reciepesearch.Repository;

import com.recipesearch.reciepesearch.Model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecipeRepo extends MongoRepository<Recipe,Long> {
}
