package com.recipesearch.recipeSearch.Utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class StopWordRemover {
    private FileInputStream wordStream;
    private BufferedReader wordReader;
    private HashSet<String> stopword;

    public StopWordRemover( ) throws IOException{

        wordStream = new FileInputStream("src/main/resources/stopword.txt");
        wordReader = new BufferedReader(new InputStreamReader(wordStream));
        stopword=new HashSet<String>();
        String line;
        while ((line= wordReader.readLine()) != null){
            stopword.add(line);//add stopword
        }
        wordReader.close();
        wordStream.close();
    }

    public boolean isStopword(String word ) {
        if(stopword.contains(word))
            return true;
        return false;
    }
}
