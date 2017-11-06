package com.aptech.istqbproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, 0);
        preferences.edit().clear().commit();



//        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.activity_main);
//        TextView tvQuestion = new TextView(this);
//        tvQuestion.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_size_question));
//        tvQuestion.setText("Question here here here here here here here here here here!");
//
//        mainLayout.addView(tvQuestion);
//
//        RadioGroup rgAns = new RadioGroup(this);
//
//        for (int i = 0; i < 6; i++) {
//            RadioButton rb = new RadioButton(this);
//            rb.setText("Answer here here " + i);
//        }
//
//        mainLayout.addView(rgAns);

//        String json = loadJson();
//        int no;
//        String question;
//        JSONObject answers;

        // Create text view for Question No.            rgAns.addView(rb);

//        TextView quesNo = new TextView(this);
//        linearLayout.addView(quesNo);
//        try {
//            JSONArray jsonArray = new JSONArray(json);
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject row = jsonArray.getJSONObject(i);
//                no = row.getInt("no");
//                quesNo.setText(Integer.toString(no));
//                question = row.getString("question");
//                answers = row.getJSONObject("answers");
//                Iterator<String> iter = answers.keys();
//                System.out.println("No: " + no);
//                System.out.println("Question: " + question);
//                while (iter.hasNext()) {
//                    String key = iter.next();
//
//                    try {
//                        Object value = answers.get(key);
//                    } catch (JSONException e) {
//                        // Something went wrong!
//                    }
//                }
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

    public void makeQuiz(View view) {
        // Make data
        Bundle data = new Bundle();

        data.putInt("quiz_num", 1);

        // Make the intent
        Intent intent = new Intent(this, QuizActivity.class);

        // Add data
        intent.putExtras(data);
        startActivity(intent);
    }


}
