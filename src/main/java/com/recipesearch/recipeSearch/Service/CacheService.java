package com.recipesearch.recipeSearch.Service;

import com.recipesearch.recipeSearch.Model.Recipe;
import com.recipesearch.recipeSearch.Repository.RecipeRepo;
import com.recipesearch.recipeSearch.Utils.WordNormializer;
import com.recipesearch.recipeSearch.Utils.WordTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.recipesearch.recipeSearch.Utils.StopWordRemover;

import java.util.*;

@Service
public class CacheService {

    @Autowired
    RecipeRepo recipeRepo;

    private Map<String,Map<Integer,Integer>> tokenDocIDDocFreMap = new HashMap<>();
    private int colLength;
    private Map<Integer,Integer> docIDDocLengthMap = new HashMap<>();

    public void setCacheService() {
        List<Recipe> list = recipeRepo.findAll();

        WordNormializer wordNormializer = new WordNormializer();

        StopWordRemover stopWordRemover = new StopWordRemover();
        int count = 0;
        for(Recipe recipe : list){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 10; i++){
                sb.append(recipe.getName());            // weight name as ten times than other attributes
            }
            sb.append(recipe.getCourse());
            sb.append(recipe.getCuisine());
            for(String s : recipe.getIngredients()){
                sb.append(s);
            }
            String content = sb.toString();

            WordTokenizer wordTokenizer = new WordTokenizer(content.toCharArray());

            char word[] = null;
            int temp = 0;
            while((word = wordTokenizer.nextWord()) != null){
                temp++;
                word = wordNormializer.lowercase(word);

                if(!stopWordRemover.isStopword(word)){
                    String token = wordNormializer.stem(word);
                    Map<Integer, Integer> docIDDocFreMap = new HashMap<>();
                    if(tokenDocIDDocFreMap.containsKey(token)){
                        docIDDocFreMap = tokenDocIDDocFreMap.get(token);
                    }
                    int docID = recipe.getId();
                    if(docIDDocFreMap.containsKey(docID)){
                        docIDDocFreMap.put(docID, docIDDocFreMap.get(docID)+1);
                    }else {
                        docIDDocFreMap.put(docID,1);
                    }
                    tokenDocIDDocFreMap.put(token,docIDDocFreMap);
                }
            }
            docIDDocLengthMap.put(recipe.getId(),temp);
            colLength += temp;
            count++;
//            if(count % 1000 == 0){
//                System.out.println("finish "+ count + " docs." );
//            }
        }
//        System.out.println("totally document count: "+count);
    }

    public Map<String, Map<Integer, Integer>> getTokenDocIDDocFreMap() {
        return tokenDocIDDocFreMap;
    }

    public int getColLength() {

        return colLength;
    }

    public int getColFrequency(String token){
        int res = 0;
        if(tokenDocIDDocFreMap.get(token) == null) return res;
        for(Map.Entry entry : tokenDocIDDocFreMap.get(token).entrySet()){
            res += (int)entry.getValue();
        }
        return res;
    }

    public int getDocLength(int docID){
        if(!docIDDocLengthMap.containsKey(docID)){
            return 0;
        }else{
            return (int)docIDDocLengthMap.get(docID);
        }
    }
}
