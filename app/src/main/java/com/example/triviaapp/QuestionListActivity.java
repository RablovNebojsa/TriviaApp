package com.example.triviaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
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
    private QuestionListAdapter mListAdapter;
    private RecyclerView mListRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_question_list);

        mTriviaApi = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TriviaApi.class);
        mListRecyclerView = findViewById(R.id.QuestionListRecyclerView);
        mListRecyclerView.setHasFixedSize(true);
        mListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
        List<Question> questionList = new ArrayList<Question>();
        for (QuestionSchema questionSchema : results){
            Question question = new Question(
                    questionSchema.getCategory(),
                    questionSchema.getType(),
                    questionSchema.getDifficulty(),
                    questionSchema.getQuestion(),
                    questionSchema.getCorrectAnswer(),
                    questionSchema.getIncorrectAnswers());
            questionList.add(question);
        }
        mListAdapter = new QuestionListAdapter(questionList);
        mListRecyclerView.setAdapter(mListAdapter);
    }
}