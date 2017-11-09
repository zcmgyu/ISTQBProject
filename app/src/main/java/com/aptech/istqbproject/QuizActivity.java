package com.aptech.istqbproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aptech.istqbproject.utils.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

public class QuizActivity extends AppCompatActivity {
    private int questionNo;
    public static final String PREFS_NAME = "data";

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
        LinearLayout direction_quiz = (LinearLayout) findViewById(R.id.ll_direction_quiz);

        // Get Radio Group
        final RadioGroup rgAnswer = (RadioGroup) findViewById(R.id.rg_answer);
        rgAnswer.removeAllViews();
        rgAnswer.clearCheck();

        if (questionNo == 1) {
            direction_quiz.setWeightSum(1);
            btnPrevious.setVisibility(View.GONE);
        } else {
            direction_quiz.setWeightSum(2);
            btnPrevious.setVisibility(View.VISIBLE);
            btnPrevious.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        previous(view);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            btnPrevious.setText(getString(R.string.btn_previous));
        }
        if (questionNo == questionList.length()) {
            btnNext.setText(R.string.btn_submit);
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (rgAnswer.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(getApplicationContext(), "Give the answer before submitting.", Toast.LENGTH_SHORT).show();
                    } else {
                        submitQuiz();
                    }
                }
            });
        } else {
            btnNext.setText(getString(R.string.btn_next));
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        if (rgAnswer.getCheckedRadioButtonId() == -1) {
                            Toast.makeText(getApplicationContext(), "Give the answer before going to the next question.", Toast.LENGTH_SHORT).show();
                        } else {
                            next(view);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
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

        // Set sub question description
        TextView tvSubQuestion = (TextView) findViewById(R.id.tv_sub_question);
        String subQuestion = questionObj.getString("sub_question");
        if (subQuestion.isEmpty() || subQuestion == null) {
            tvSubQuestion.setVisibility(View.GONE);
        } else {
            tvSubQuestion.setVisibility(View.VISIBLE);
            tvSubQuestion.setText(subQuestion);
        }
        // Set answer radio
        JSONObject answerObj = questionObj.getJSONObject("answers");
        Iterator<String> answerIter = answerObj.keys();
        while (answerIter.hasNext()) {
            String key = answerIter.next();
            String answer = answerObj.getString(key);
            RadioButton rbAnswer = new RadioButton(this);
            rbAnswer.setId(key.charAt(0));
            rbAnswer.setText(answer);
            rgAnswer.addView(rbAnswer);
        }

        // check either saved option or don't know
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, 0);
        int selectedRadio = preferences.getInt(String.format("ques_no_%d", questionNo), -1);
        if (selectedRadio != -1) {
            RadioButton r = rgAnswer.findViewById(selectedRadio);
            r.setChecked(true);
        }

    }

    public void previous(View view) throws IOException, JSONException {
        saveAnswer(questionNo);
        displayQuiz(questionNo - 1);
    }

    public void next(View view) throws IOException, JSONException {
        saveAnswer(questionNo);
        displayQuiz(questionNo + 1);
    }

    private void submitQuiz() {
        saveAnswer(questionNo);

        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(getString(R.string.submitConfirmTitle))
                .setMessage(getString(R.string.submitConfirmMessage))
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent results = new Intent(QuizActivity.this, ResultActivity.class);
                        startActivity(results);
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }

    private void saveAnswer(int questionNo) {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        RadioGroup rg = (RadioGroup) findViewById(R.id.rg_answer);
        int checkedRadio = rg.getCheckedRadioButtonId();
        editor.putInt(String.format("ques_no_%d", questionNo), checkedRadio);
        editor.apply();
    }
}
