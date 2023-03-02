package com.example.triviaapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TriviaApi {
    @GET("/api.php/")
    Call<QuestionListResponseSchema> fetchTriviaQuestions(@Query("amount") Integer count);
}
