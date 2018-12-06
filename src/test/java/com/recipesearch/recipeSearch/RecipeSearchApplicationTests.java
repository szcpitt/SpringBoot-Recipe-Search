package com.recipesearch.recipeSearch;

import com.recipesearch.reciepesearch.Controller.DAO.impl.InsertDataDAOImpl;
import com.recipesearch.recipeSearch.Model.Recipe;
import com.recipesearch.recipeSearch.Repository.RecipeRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeSearchApplicationTests {

    @Autowired
    RecipeRepo recipeRepo;

    @Autowired
    MongoTemplate mongoTemplate;
    @Test
    public void contextLoads() {
    }

    @Test
    public void testData(){
        List<Recipe> recipe=recipeRepo.findByNameIgnoreCase("Mushroom and Roasted Garlic Risotto");
        assertNotNull(recipe);

    }

    @Test
    public void testNullValue(){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(11));
        List<Recipe> list = mongoTemplate.find(query, Recipe.class);

    }
    @Test
    public void testDatabase(){
        InsertDataDAOImpl i = new InsertDataDAOImpl();
        i.openConnection();
    }
}
