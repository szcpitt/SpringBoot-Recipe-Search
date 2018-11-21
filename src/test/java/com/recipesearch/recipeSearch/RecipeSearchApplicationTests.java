package com.recipesearch.recipeSearch;

<<<<<<< HEAD:src/test/java/com/recipesearch/reciepesearch/ReciepesearchApplicationTests.java
import com.recipesearch.reciepesearch.Controller.DAO.impl.InsertDataDAOImpl;
import com.recipesearch.reciepesearch.Model.Recipe;
import com.recipesearch.reciepesearch.Repository.RecipeRepo;
=======
import com.recipesearch.recipeSearch.Model.Recipe;
import com.recipesearch.recipeSearch.Repository.RecipeRepo;
>>>>>>> 9bb771d1bf09ea030913913fbf6190161350a751:src/test/java/com/recipesearch/recipeSearch/RecipeSearchApplicationTests.java
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeSearchApplicationTests {

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
        InsertDataDAOImpl i = new InsertDataDAOImpl();
        i.openConnection();
    }
}
