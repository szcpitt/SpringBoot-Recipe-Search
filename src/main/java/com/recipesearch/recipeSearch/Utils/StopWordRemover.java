package com.recipesearch.recipeSearch.Utils;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class StopWordRemover {

    private BufferedReader bufferedReader;
    private Set<String> stopWord = new HashSet<>();

    public StopWordRemover( ) {
        //File file = new File("src/main/resources/stopword.txt");
        InputStream file = this.getClass().getResourceAsStream("/stopword.txt");

        try {
            //this.bufferedReader = new BufferedReader(new FileReader(file));
            this.bufferedReader = new BufferedReader(new InputStreamReader(file, "UTF-8"));
            String temp = null;
            while((temp = bufferedReader.readLine())!= null){
                stopWord.add(temp);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isStopword( char[] word ) {
        // Return true if the input word is a stopword, or false if not.
        if(stopWord.contains(new String(word))){
            return true;
        }
        return false;
    }
}