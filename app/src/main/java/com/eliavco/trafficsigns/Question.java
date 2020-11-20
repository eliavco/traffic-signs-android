package com.eliavco.trafficsigns;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Question {
    private ArrayList<String> answers = new ArrayList<String>();
    private int rightAnswer;
    private int chosenAnswer = -1;
    private Drawable img;

    public Question(Context ctx) {
        this.setAnswers(ctx, new ArrayList<Question>());
    }

    public Question(Context ctx, ArrayList<Question> olderQuestions) {
        this.setAnswers(ctx, olderQuestions);
    }

    public boolean existsInOlderQuestions(ArrayList<Question> olderQuestions) {
        for (int l = 0; l < olderQuestions.size(); l++) {
            Question olderQuestion = olderQuestions.get(l);
            String rightAnswerOlderQuestion = olderQuestion.getAnswers().get(olderQuestion.rightAnswer),
            rightAnswerCurrentQuestion = this.answers.get(this.rightAnswer);
            if (rightAnswerOlderQuestion == rightAnswerCurrentQuestion) return true;
        }
        return false;
    }

    private void setAnswers(Context ctx, ArrayList<Question> olderQuestions) {
        Dal dal = new Dal(ctx);
        ArrayList<Sign> signs = dal.getAllSigns();
        while(true) {
            int[] indexes = this.getRands(signs.size());
            ArrayList<Sign> chosenSigns = new ArrayList<Sign>();
            for (int m = 0; m < indexes.length; m++) {
                chosenSigns.add(signs.get(indexes[m]));
                this.answers.add(signs.get(indexes[m]).getMeaning());
            }
            this.rightAnswer = this.getRand(4);
            try {
                this.img = chosenSigns.get(this.rightAnswer).getImage(ctx.getAssets());
            } catch (Exception e) { this.img = ctx.getDrawable(R.drawable.brown_circle); }
            if (!existsInOlderQuestions(olderQuestions)) break;
        }
    }

    private int[] getRands(int max) {
        int[] indexes = new int[4];
        int a;
        for (int i = 0; i < indexes.length; i++) {
            a = getRand(max);
            while (this.contains(indexes, a)) {
                a = getRand(max);
            }
            indexes[i] = a;
        }
        return indexes;
    }

    public static boolean contains(final int[] arr, final int key) {
        return Arrays.asList(arr).contains(key) ;
    }

    private int getRand(int max) {
        Random rd = new Random();
        int rand = rd.nextInt(max);
        return rand;
    }

    public ArrayList<String> getAnswers() {
        return this.answers;
    }

    public Drawable getImg() {
        return this.img;
    }

    public void setChosenAnswer(int chosenAnswer) {
        this.chosenAnswer = chosenAnswer;
    }

    public boolean isTrue() {
        return this.chosenAnswer == this.rightAnswer;
    }

}
