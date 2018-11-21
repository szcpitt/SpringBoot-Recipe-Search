package com.recipesearch.reciepesearch;

import com.recipesearch.reciepesearch.Controller.DAO.InsertDataDao;
import com.recipesearch.reciepesearch.Controller.DAO.impl.InsertDataDaoImpl;
import com.recipesearch.reciepesearch.Model.Recipe;
import com.recipesearch.reciepesearch.Repository.RecipeRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReciepesearchApplicationTests {

    @Autowired
    RecipeRepo recipeRepo;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testData(){
        Recipe recipe=recipeRepo.findByName("Mushroom and Roasted Garlic Risotto");
        assertNotNull(recipe);

    }

    @Test
    public void testDatabase(){
        InsertDataDaoImpl i = new InsertDataDaoImpl();
        i.openConnection();
    }
}
