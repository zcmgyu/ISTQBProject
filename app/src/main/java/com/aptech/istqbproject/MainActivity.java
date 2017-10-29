package com.aptech.istqbproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String json = loadJson();
//        int no;
//        String question;
//        JSONObject answers;
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.activity_main);
        TextView tvQuestion = new TextView(this);
        tvQuestion.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_size_question));
        tvQuestion.setText("Question here here here here here here here here here here!");

        mainLayout.addView(tvQuestion);

        RadioGroup rgAns = new RadioGroup(this);

        for (int i = 0; i < 6; i++) {
            LinearLayout loAns = new LinearLayout(this);
            RadioButton rb = new RadioButton(this);
            rb.setText("Answer here here " + i);
            rgAns.addView(rb);
        }

        mainLayout.addView(rgAns);


        // Create text view for Question No.
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

    public String loadJson() {
        String json;
        try {
            InputStream is = getResources()
                    .openRawResource(R.raw.file);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
