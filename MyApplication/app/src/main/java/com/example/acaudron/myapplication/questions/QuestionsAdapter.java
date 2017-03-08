package com.example.acaudron.myapplication.questions;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acaudron.myapplication.R;

import java.util.ArrayList;

/**
 *
 */
public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder> {

    private ArrayList<Question> questions;

    public QuestionsAdapter(ArrayList<Question> questions) {
        this.questions = questions;
    }

    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final Context ctx = parent.getContext();
        final LayoutInflater inflater = LayoutInflater.from(ctx);
        final View questionItemView = inflater.inflate(R.layout.question_item, parent, false);
        return new QuestionViewHolder(questionItemView);
    }

    @Override
    public void onBindViewHolder(QuestionViewHolder holder, final int position) {

        Log.d("position", String.valueOf(position));
        final Question question = questions.get(position);
        holder.questionText.setText(question.text);
        holder.categoryText.setText(question.category);

        switch (question.difficulty) {
            case "easy":
                holder.questionText.setBackgroundResource(R.color.easy);
                break;
            case "medium":
                holder.questionText.setBackgroundResource(R.color.medium);
                break;
            case "hard":
                holder.questionText.setBackgroundResource(R.color.hard);
                break;
        }

        switch (question.category) {
            case "Science & Nature":
                holder.categoryImage.setImageResource(R.drawable.sciences);
                break;
            case "Entertainment: Video Games":
                holder.categoryImage.setImageResource(R.drawable.games);
                break;
            case "Science: Computers":
                holder.categoryImage.setImageResource(R.drawable.computer);
                break;
            case "Geography":
                holder.categoryImage.setImageResource(R.drawable.geography);
                break;
            case "History":
                holder.categoryImage.setImageResource(R.drawable.history);
                break;
            case "Science: Mathematics":
                holder.categoryImage.setImageResource(R.drawable.mathematics);
                break;
            case "Entertainment: Books":
                holder.categoryImage.setImageResource(R.drawable.book);
                break;
            case "Entertainment: Music":
                holder.categoryImage.setImageResource(R.drawable.music);
                break;
            case "Animals":
                holder.categoryImage.setImageResource(R.drawable.animals);
                break;
            case "Politics":
                holder.categoryImage.setImageResource(R.drawable.politics);
                break;
            case "Entertainment: Film":
                holder.categoryImage.setImageResource(R.drawable.film);
                break;
            case "Entertainment: Television":
                holder.categoryImage.setImageResource(R.drawable.film);
                break;
            case "Entertainment: Cartoon & Animations":
                holder.categoryImage.setImageResource(R.drawable.cartoon);
                break;
            case "Entertainment: Comics":
                holder.categoryImage.setImageResource(R.drawable.comics);
                break;
            case "Sports":
                holder.categoryImage.setImageResource(R.drawable.sport);
                break;
            case "Celebrities":
                holder.categoryImage.setImageResource(R.drawable.famous);
                break;
            default:
                holder.categoryImage.setImageResource(R.drawable.history);
                break;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), question.correct_answer + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView questionText;
        TextView categoryText;
        ImageView categoryImage;
        public QuestionViewHolder(View itemView) {
            super(itemView);
            this.questionText = (TextView) itemView.findViewById(R.id.question_text);
            this.categoryText = (TextView) itemView.findViewById(R.id.category_text);
            this.categoryImage = (ImageView) itemView.findViewById(R.id.category_image);
        }
    }

}
