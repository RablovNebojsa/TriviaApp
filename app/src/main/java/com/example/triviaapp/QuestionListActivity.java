package com.example.triviaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuestionListActivity extends AppCompatActivity {

    private static final String LOG_TAG = QuestionListActivity.class.getName();
    public static final String BASE_URL = "https://opentdb.com/";
    public static final Integer AMOUNT = 20;

    private TriviaApi mTriviaApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_question_list);

        mTriviaApi = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TriviaApi.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        fetchQuestions();
    }

    private void fetchQuestions() {
        mTriviaApi.fetchTriviaQuestions(AMOUNT)
                .enqueue(new Callback<QuestionListResponseSchema>() {
                    @Override
                    public void onResponse(Call<QuestionListResponseSchema> call, Response<QuestionListResponseSchema> response) {
                        if(response.isSuccessful()){
                            bindQuestions(response.body().getResults());
                        } else {
                            networkCallFailed();
                        }
                    }

                    @Override
                    public void onFailure(Call<QuestionListResponseSchema> call, Throwable t) {

                    }
                });
    }

    private void networkCallFailed() {
        Toast.makeText(this,"Fetching questions faild", Toast.LENGTH_LONG)
                .show();
    }

    private void bindQuestions(List<QuestionSchema> results) {
        for (QuestionSchema questionSchema : results){
            Log.d(LOG_TAG,"Response: " + questionSchema.getCategory() + " " +
                    questionSchema.getCorrectAnswer() + " " +
                    questionSchema.getQuestion());
        }
    }
}