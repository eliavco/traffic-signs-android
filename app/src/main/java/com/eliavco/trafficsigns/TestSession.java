package com.eliavco.trafficsigns;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;

public class TestSession extends Test {
    private ArrayList<Question> questions = new ArrayList<Question>();

    public TestSession(Context ctx) {
        super();
        this.user = "אני";
        this.setDate(new Date());
        for (int a = 0; a < 10; a++) {
            this.questions.add(new Question(ctx, this.questions));
        }
    }

    public Test closeTest() {
        return new Test(0, this.date, this.user, ((new Date()).getTime() - this.getDate().getTime()) / 1000, this.calculateGrade());
    }

    private int calculateGrade() {
        int counter = 0;
        for (int k = 0; k < this.questions.size(); k++) {
            if (this.questions.get(k).isTrue()) counter++;
        }
        return counter * 10;
    }

    public void chooseAnswer(int index, int answer) {
        this.questions.get(index).setChosenAnswer(answer);
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
}
