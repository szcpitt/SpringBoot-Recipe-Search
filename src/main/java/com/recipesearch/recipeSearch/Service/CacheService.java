package com.recipesearch.recipeSearch.Service;

import com.recipesearch.recipeSearch.Model.Recipe;
import com.recipesearch.recipeSearch.Repository.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.recipesearch.recipeSearch.Utils.Stemmer;
import com.recipesearch.recipeSearch.Utils.StopWordRemover;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Service
public class CacheService {

    @Autowired
    RecipeRepo recipeRepo;

    private Map<String,Map<Integer,Integer>> documentIndex;
    private Set<String> stopwords;

    private FileInputStream wordStream;
    private BufferedReader wordReader;


    public void setCacheService() throws IOException{
        List<Recipe> recipes = recipeRepo.findAll();
        String str = "";
        Map<Integer,String> content = new HashMap<>();

        wordStream = new FileInputStream("src/main/resources/stopword.txt");
        wordReader = new BufferedReader(new InputStreamReader(wordStream));
        stopwords = new HashSet<>();
        String line;
        while ((line= wordReader.readLine()) != null){
            stopwords.add(line);//add stopword
        }
        wordReader.close();
        wordStream.close();
    }

    public void setDocumentIndex(Map<String, Map<Integer, Integer>> documentIndex) {
        this.documentIndex = documentIndex;
    }

    public Map<String, Map<Integer, Integer>> getDocumentIndex() {
        return documentIndex;
    }

    public void setStopwrods(Set<String> stopwords){
        this.stopwords= stopwords;
    }

    public Set<String> getStopwrods() {
        return stopwords;
    }

    public boolean isStopword(String word ) {
        if(stopwords.contains(word))
            return true;
        return false;
    }
}
