package com.example.acaudron.myapplication.questions;

/**
 * Created by acaudron on 07/03/2017.
 */

public class Question {
    public String text;
    public String difficulty;
    public String correct_answer;
    public String category;

    public Question(String text, String difficulty, String correct_answer, String category) {
        this.text = text;
        this.difficulty = difficulty;
        this.correct_answer = correct_answer;
        this.category = category;
    }
}
