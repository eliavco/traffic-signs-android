package com.eliavco.trafficsigns;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class TestActivity extends AppCompatActivity {
    TestSession test;
    long time = 0;
    Timer timer;
    TimerTask timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // INITIALIZE BACK BUTTON
        this.forceRTLIfSupported();
        getSupportActionBar().setTitle(getString(R.string.app_name_test)); // INITIALIZE NEW TITLE

        this.initializeTest();
        this.initializeUser();
        this.initializeClock();
        this.initializeQuestions();
    }

    private void initializeQuestions() {
        ListView questionsContainer = findViewById(R.id.testQuestionsView);
        QuestionTestAdapter adap = new QuestionTestAdapter(this, R.layout.question_layout, this.test.getQuestions());
        questionsContainer.setAdapter(adap);
    }

    private void initializeClock() {
        class UpdateClock extends TimerTask {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time++;
                        updateClock(time);
                    }
                });
            }
        }

        this.timer = new Timer();
        this.timerTask = new UpdateClock();
        this.startTimer();
    }

    private void startTimer() {
        this.timer.schedule(this.timerTask, 0, 1000);
    }

    private void pauseTimer() {
        this.timer.cancel();
    }

    private void updateClock(long time) {
        String updated = this.formatTime(time);
        TextView clock = findViewById(R.id.testClock);
        clock.setText(updated);
    }

    private String formatTime(long time) {
        long hours = time / 3600;
        long seconds = time % 60;
        long minutes = time / 60;
        if (hours >= 1) {
            return hours + ":" + (minutes % 60) + ":" + seconds;
        } else if (minutes >= 1){
            return minutes + ":" + seconds;
        }
        return "00:" + time;
    }

    public void initializeUser() {
        final EditText user = findViewById(R.id.testUserNameEdit);
        user.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setUser(user.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    public void initializeTest() {
        this.test = new TestSession(this);
        findViewById(R.id.submitButtonTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeTest();
            }
        });
    }

    public void setUser(String user) {
        if (!user.isEmpty()) { this.test.setUser(user); } else { this.test.setUser("אני"); }
    }

    public void chooseAnswer(int index, int answer) {
        this.test.chooseAnswer(index, answer);
    }

    public void closeTest() {
        this.pauseTimer();
        Test newTest = this.test.closeTest();
        Dal dal = new Dal(this);
        dal.addTest(newTest);
        String message = "";
        if (newTest.getGrade() >= 90) { message = "עברת את המבחן בהצלחה"; } else { message = "נכשלת במבחן"; }
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.show();

        AlertDialog.Builder finishConfirmation = new AlertDialog.Builder(this);
        finishConfirmation.setMessage(message + " עם ציון: " + newTest.getGrade());

        finishConfirmation.setNegativeButton(this.getString(R.string.approve_message), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });

        AlertDialog deleteConfirmationDialog = finishConfirmation.create();
        deleteConfirmationDialog.show();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void forceRTLIfSupported()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }

    @Override
    protected void onDestroy() {
        this.pauseTimer();
        super.onDestroy();
    }
}