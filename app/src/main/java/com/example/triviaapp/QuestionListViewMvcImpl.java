package com.example.triviaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class QuestionListViewMvcImpl implements QuestionListAdapter.OnQuestionClickListener, QuestionListViewMvc {

    private QuestionListAdapter mListAdapter;
    private RecyclerView mListRecyclerView;

    private List<Listener> mListeners = new ArrayList<>(1);
    private View mRootView;
    public QuestionListViewMvcImpl(LayoutInflater inflater, ViewGroup parent) {
        mRootView = inflater.inflate(R.layout.layout_question_list, parent, false);
        mListRecyclerView = findViewById(R.id.QuestionListRecyclerView);
        mListRecyclerView.setHasFixedSize(true);
        mListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void registerListener(Listener listener) {
        mListeners.add(listener);
    }

    @Override
    public void unregisterListener(Listener listener) {
        mListeners.remove(listener);
    }

    public Context getContext() {
        return getRootView().getContext();
    }
    private <T extends View> T findViewById(int id) {
        return getRootView().findViewById(id);
    }

    @Override
    public View getRootView() {
        return mRootView;
    }
    @Override
    public void bindQuestions(List<Question> questionList) {
        mListAdapter = new QuestionListAdapter(questionList, this);
        mListRecyclerView.setAdapter(mListAdapter);
    }
    @Override
    public void onQuestionClicked(Question question) {
        for (Listener listener : mListeners) {
            listener.onQuestionClicked(question);
        }
    }
}

