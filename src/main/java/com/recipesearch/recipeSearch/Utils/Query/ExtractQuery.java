package com.recipesearch.recipeSearch.Utils.Query;

import com.recipesearch.recipeSearch.Utils.StopWordRemover;
import com.recipesearch.recipeSearch.Utils.WordNormializer;
import com.recipesearch.recipeSearch.Utils.WordTokenizer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ExtractQuery {
    private String content;
    public ExtractQuery(String query) {
        //you should extract the 4 queries from the Path.TopicDir
        //NT: the query content of each topic should be 1) tokenized, 2) to lowercase, 3) remove stop words, 4) stemming
        //NT: you can simply pick up title only for query, or you can also use title + description + narrative for the query content.

        // save stop words
        StopWordRemover stopWordRemover = new StopWordRemover();
        WordNormializer wordNormializer = new WordNormializer();
        WordTokenizer wordTokenizer = new WordTokenizer(query.toCharArray());

//        String words[] = query.replaceAll( "\\p{Punct}", "" ).split(" ");	// 1. tokenized

        StringBuilder sb = new StringBuilder();
        char word[] = null;
        while((word = wordTokenizer.nextWord()) != null){
            word = wordNormializer.lowercase(word);
            if(!stopWordRemover.isStopword(word)){		// 3. remove stop words
                sb.append(wordNormializer.stem(word)+" ");
            }
        }
        sb.setLength(sb.length()-1);
        content = sb.toString();
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {

        return content;
    }
}