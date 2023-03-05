package com.example.triviaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.zip.Inflater;

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.ViewHolder> {
    public interface OnQuestionClickListener {
        void onQuestionClicked(Question question);
    }
    private List<Question> mQuestionList;
    private final OnQuestionClickListener mListener;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mQuestTextView;
        private LinearLayout mQuestionLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mQuestionLayout = itemView.findViewById(R.id.QuestionItemLayout);
            mQuestTextView = itemView.findViewById(R.id.QuestionText);
        }

        private TextView getQuestionTextView() {
            return mQuestTextView;
        }

        private LinearLayout getQuestionLayout() {
            return mQuestionLayout;
        }
    }

    public QuestionListAdapter(List<Question> questionList, OnQuestionClickListener listener) {
        mQuestionList = questionList;
        mListener = listener;
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
        holder.getQuestionLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onQuestionClicked(mQuestionList.get(holder.getBindingAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mQuestionList.size();
    }
}
