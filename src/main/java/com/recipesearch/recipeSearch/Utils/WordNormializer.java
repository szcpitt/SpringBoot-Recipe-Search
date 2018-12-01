package com.recipesearch.recipeSearch.Utils;

import com.recipesearch.recipeSearch.Utils.Stemmer;

public class WordNormializer {
    public char[] lowercase(char[] chars) {
        // transform words into lowercase.
        return (new String(chars).toLowerCase().toCharArray());
    }

    // YOU MUST IMPLEMENT THIS METHOD.
    public String stem(char[] chars) {
        // return the stemmed word with Stemmer in Classes package.
        Stemmer s = new Stemmer();
        s.add(chars, chars.length);
        s.stem();
        String str = s.toString();
        return str;
    }
}
