package com.aptech.istqbproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, 0);
        preferences.edit().clear().commit();
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
