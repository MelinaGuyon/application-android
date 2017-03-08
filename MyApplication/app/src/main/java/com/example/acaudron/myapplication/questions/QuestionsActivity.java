package com.example.acaudron.myapplication.questions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.acaudron.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class QuestionsActivity extends AppCompatActivity {

    ArrayList<Question> questions = new ArrayList<Question>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();


        //Get Data
        int numberOfquestion = extras.getInt("NUM_OF_QUESTIONS");

        Log.d("num_of_questions", String.valueOf(numberOfquestion));

        this.fetchQuestions(numberOfquestion);


        //Base UI
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) this.findViewById(R.id.questions_list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new QuestionsAdapter(questions));
    }


    protected ArrayList<Question> parseResultsRequestQuestion(JSONArray results) {

        ArrayList<Question> questions = new ArrayList<Question>();

        for (int i = 0; i < results.length(); i++) {
            String title = null;
            try {
                title = (String) results.getJSONObject(i).get("question");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String difficulty = null;
            try {
                difficulty = (String) results.getJSONObject(i).get("difficulty");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String correct_answer = null;
            try {
                correct_answer = (String) results.getJSONObject(i).get("correct_answer");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String category = null;
            try {
                category = (String) results.getJSONObject(i).get("category");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            if(title != null && difficulty != null && correct_answer != null) {
                Question question = new Question(convertCharacter(title), convertCharacter(difficulty), convertCharacter(correct_answer), convertCharacter(category));
                questions.add(question);
            }
        }

        return questions;
    }

    protected String convertCharacter(String string) {
        String result;
        result = string.replace("&quot;", "\"");
        return result.replace("&#039;", "'");
    }


    void fetchQuestions(int numberOfquestion) {
        final String url = "https://opentdb.com/api.php?amount=" + numberOfquestion;
        final RequestQueue queue = Volley.newRequestQueue(this);
        final JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response json", String.valueOf(response));
                try {

                    JSONArray results = response.getJSONArray("results");

                    Log.d("response json results", String.valueOf(results));

                    questions = parseResultsRequestQuestion(results);

                    //Update ui
                    recyclerView.setAdapter(new QuestionsAdapter(questions));

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("response json error", String.valueOf(e));

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(jsonRequest);
    }
}
