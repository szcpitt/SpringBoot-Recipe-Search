package com.recipesearch.recipeSearch.Model;

public class Document {

    protected int docid;
//    protected String docno;
    protected double score;

    public Document( int docid, double score ) {
        this.docid = docid;
//        this.docno = docno;
        this.score = score;
    }

    public int getDocid() {
        return docid;
    }

    public double getScore() {
        return score;
    }

    public void setDocid( int docid ) {
        this.docid = docid;
    }

    public void setScore( double score ) {
        this.score = score;
    }

}