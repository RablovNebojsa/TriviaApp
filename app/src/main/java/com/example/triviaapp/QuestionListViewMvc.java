package com.example.triviaapp;

import android.view.View;

import java.util.List;

public interface QuestionListViewMvc {
    public interface Listener {
        void onQuestionClicked(Question question);
    }
    void registerListener(Listener listener);

    void unregisterListener(Listener listener);

    View getRootView();

    void bindQuestions(List<Question> questionList);
}
