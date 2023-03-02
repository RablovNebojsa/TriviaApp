package com.example.triviaapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionListResponseSchema {

    @SerializedName("response_code")
    @Expose
    private Integer responseCode;
    @SerializedName("results")
    @Expose
    private List<QuestionSchema> results;

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public List<QuestionSchema> getResults() {
        return results;
    }

    public void setResults(List<QuestionSchema> results) {
        this.results = results;
    }

}