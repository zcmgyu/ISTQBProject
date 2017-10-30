package com.aptech.istqbproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.aptech.istqbproject.utils.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

public class QuizActivity extends AppCompatActivity {
    int questionNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Bundle data = getIntent().getExtras();
        int quiznNum = data.getInt("quiz_num");
        // Display list quiz
        try {
            displayQuiz(quiznNum);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void displayQuiz(int questionNo) throws IOException, JSONException {
        this.questionNo = questionNo;
        JSONArray questionList = JsonUtil.loadJsonArrayFile(this, R.raw.file);
        Button btnPrevious = (Button) findViewById(R.id.btn_previous);
        Button btnNext = (Button) findViewById(R.id.btn_next);
        if (questionNo == 1) {
            btnPrevious.setEnabled(false);
        } else {
            btnPrevious.setEnabled(true);
            btnPrevious.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    previous(view);
                }
            });
        }
        if (questionNo == questionList.length()) {
            btnNext.setText(R.string.btn_submit);
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    submitQuiz();
                }
            });
        } else {
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    next(view);
                }
            });
            btnNext.setText(getString(R.string.btn_next));
        }

        // Set question number
        TextView tvQuestionNum = (TextView) findViewById(R.id.question_number);
        tvQuestionNum.setText(String.format(getString(R.string.question_num), this.questionNo, questionList.length()));

        // Get question obj by index
        JSONObject questionObj = questionList.getJSONObject(questionNo - 1);

        // Set question description
        TextView tvQuestion = (TextView) findViewById(R.id.tv_question);
        String question = questionObj.getString("question");
        tvQuestion.setText(question);

        // Get Radio Group
        RadioGroup rgAnswer = (RadioGroup) findViewById(R.id.rg_answer);

        // Set answer radio
        JSONObject answerObj = questionObj.getJSONObject("answers");
        Iterator<String> answerIter = answerObj.keys();
        while (answerIter.hasNext()) {
            String answer = answerObj.getString(answerIter.next());
            RadioButton rbAnswer = new RadioButton(this);
            rbAnswer.setText(answer);
            rgAnswer.addView(rbAnswer);
        }
    }

    private void previous(View view) {
    }

    private void next(View view) {
    }

    private void submitQuiz() {
    }
}
