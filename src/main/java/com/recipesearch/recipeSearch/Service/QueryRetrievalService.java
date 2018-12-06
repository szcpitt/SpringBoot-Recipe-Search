package com.recipesearch.recipeSearch.Service;

import com.recipesearch.recipeSearch.Model.Document;
import com.recipesearch.recipeSearch.Model.Recipe;
import com.recipesearch.recipeSearch.Service.CacheService;
import com.recipesearch.recipeSearch.Service.RecipeService;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class QueryRetrievalService {
    @Autowired
    CacheService cacheService;
    @Autowired
    RecipeService recipeService;
    private int colLen;

    /**
     * Search for the topic information.
     * The returned results (retrieved documents) should be ranked by the score (from the most relevant to the least).
     * TopN specifies the maximum number of results to be returned.
     *
     * @param query The query to be searched for.
     * @param TopN The maximum number of returned document
     * @return
     */

    public List<Recipe> retrieveQuery(String query, int TopN ) throws IOException {
        // NT: you will find our IndexingLucene.Myindexreader provides method: docLength()
        // implement your retrieval model here, and for each input query, return the topN retrieved documents
        // sort the documents based on their relevance score, from high to low

        this.colLen = cacheService.getColLength();
        HashMap<Integer, HashMap<String,Integer>> idTokenDocFrequencyMap = new HashMap<>();	//docid - (token - document frequency) map
        HashMap<String, Integer> tokenColFrequencyMap = new HashMap<>();	// token - collection frequency map
        String[] tokens = query.split(" ");

        for(String token : tokens){
            tokenColFrequencyMap.put(token, cacheService.getColFrequency(token)); 	// update the second map
            Map<Integer,Integer> postingMap = cacheService.getTokenDocIDDocFreMap().get(token);
            if(postingMap == null) continue;
            for(Map.Entry entry : postingMap.entrySet()){
                int docid = (int)entry.getKey();
                int freq = (int) entry.getValue();
                if(idTokenDocFrequencyMap.containsKey(docid)){		// update the first map
                    idTokenDocFrequencyMap.get(docid).put(token,freq);
                }else {
                    HashMap<String,Integer> tokenFrequencyMap = new HashMap<>();
                    tokenFrequencyMap.put(token,freq);
                    idTokenDocFrequencyMap.put(docid,tokenFrequencyMap);
                }
            }
        }
        double U = 2000.0;
        List<Document> documentList = new ArrayList<>();
        // if use a docidList, it will have some duplicates.
        for(Map.Entry<Integer, HashMap<String,Integer>> entry : idTokenDocFrequencyMap.entrySet()){
            double score = 1.0;			// each document have one score
            int docid = entry.getKey();
            int docLen = cacheService.getDocLength(docid);	// get document length
            HashMap<String,Integer> tokenDocFrequencyMap = idTokenDocFrequencyMap.get(docid);
            for(String token : tokens){		// for each token, not the token just exist in the document
                long colFreq = tokenColFrequencyMap.getOrDefault(token,0);	// get collection frequency
                if(colFreq != 0){
                    int docFreq = 0;	// if this document don't have token, just set document frequency as 0
                    if(tokenDocFrequencyMap.containsKey(token)){
                        docFreq = tokenDocFrequencyMap.get(token);
                    }
                    score = score * (docFreq + U * ((double)colFreq / colLen)) / (docLen + U);	//equation
                }
            }
            Document document = new Document(docid,score);
            documentList.add(document);
        }

        Collections.sort(documentList, new Comparator<Document>() {
            @Override
            public int compare(Document o1, Document o2) {
                return Double.compare(o2.getScore(),o1.getScore());
            }
        });
        List<Recipe> res = new ArrayList<>();
        for(int i = 0 ; i < TopN; i++){
            int id = documentList.get(i).getDocid();
            res.add(recipeService.getByID(id));
        }
        return res;
    }

}