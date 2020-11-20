package com.eliavco.trafficsigns;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.text.LineBreaker;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionTestAdapter extends ArrayAdapter<Question> {
    private Context context;
    private int resourceId;
    private ArrayList<Question> questions;

    public QuestionTestAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Question> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resourceId = resource;
        this.questions = objects;
    }

    @Override
    public int getCount() {
        return this.questions.size();
    }

    private void chooseAnswer(int index, int answer) {
        if (this.context instanceof TestActivity) {
            TestActivity testActivity = (TestActivity) this.context;
            testActivity.chooseAnswer(index, answer);
        }
    }

    private void setListenersForAnswers(int[] answersIds, View v, int position, ArrayList<String> answers) {
        for (int o = 0; o < answersIds.length; o++) {
            this.setListenerForAnswer(v, answersIds[o], position, o, answers.get(o));
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater li = (LayoutInflater)(this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        View v = li.inflate(this.resourceId, null);
        Question q = this.questions.get(position);

        int[] answersIds = new int[]{R.id.answerTestChoiceOne, R.id.answerTestChoiceTwo, R.id.answerTestChoiceThree, R.id.answerTestChoiceFour};
        this.setListenersForAnswers(answersIds, v, position, q.getAnswers());

        ImageView img = v.findViewById(R.id.testQuestionImageSign);
        img.setImageDrawable(q.getImg());

        TextView title = v.findViewById(R.id.testQuestionTitle);
        title.setText((position + 1) + this.context.getString(R.string.test_question_question_description));

        int[] bColors = new int[]{
                Color.argb(77, 69, 123, 157),
                Color.argb(77, 64, 145, 108)
        };
        v.setBackgroundColor(bColors[position % 2]);

        return v;
    }

    public void setListenerForAnswer(View v, int aid, final int position, final int answerNumber, String text) {
        RadioButton rd = v.findViewById(aid);
        rd.setText(text);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            rd.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
        }
        rd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseAnswer(position, answerNumber);
            }
        });
    }
}
