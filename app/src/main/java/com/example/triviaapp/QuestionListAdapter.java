package com.example.triviaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.zip.Inflater;

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.ViewHolder> {

    private List<Question> mQuestionList;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mQuestTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mQuestTextView = itemView.findViewById(R.id.QuestionText);
        }

        public TextView getQuestionTextView() {
            return mQuestTextView;
        }
    }

    public QuestionListAdapter(List<Question> questionList) {
        mQuestionList = questionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_question_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getQuestionTextView().setText(mQuestionList.get(position).getQuestion());
    }

    @Override
    public int getItemCount() {
        return mQuestionList.size();
    }
}
