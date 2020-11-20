package com.eliavco.trafficsigns;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;

public class TestAdapterRecords extends ArrayAdapter<Test> {
    private Context ctx;
    private ArrayList<Test> arr;
    private int resourceId;

    public TestAdapterRecords(@NonNull Context context, int resource, @NonNull ArrayList<Test> objects) {
        super(context, resource, objects);
        this.ctx = context;
        this.resourceId = resource;
        this.arr = new ArrayList<Test>();
        if (objects.size() > 3) {
            this.arr = new ArrayList<Test>(objects.subList(3, objects.size()));
        }
    }

    @Override
    public int getCount() {
        return this.arr.size();
    }

    public void setPropertyText(View v, int tid, String value) {
        TextView propertyText = v.findViewById(tid);
        propertyText.setText(value);
    }

    public void setPropertyText(View v, int tid, SpannableString value) {
        TextView propertyText = v.findViewById(tid);
        propertyText.setText(value);
    }

    public SpannableString calculateGradeColor(int grade) {
        SpannableString str = new SpannableString(grade + "");
        String colorSuccess = "#95d5b2", colorOk = "#ffe66d", colorFailure = "#e63946";
        int chosenColor = 0;
        if (grade >= 85) {
            chosenColor = Color.parseColor(colorSuccess);
        } else if (grade < 55){
            chosenColor = Color.parseColor(colorFailure);
            str.setSpan(new ForegroundColorSpan(Color.parseColor("#f0f0f0")), 0, str.length(), 0);
        } else {
            chosenColor = Color.parseColor(colorOk);
        }

        str.setSpan(new BackgroundColorSpan(chosenColor), 0, str.length(), 0);
        return str;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater li = (LayoutInflater)(this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        View v = li.inflate(this.resourceId, null);
        Test t = this.arr.get(position);

        this.setPropertyText(v, R.id.idTestRecords, position + 4 + "");
        this.setPropertyText(v, R.id.userTestRecords, t.getUser());
        this.setPropertyText(v, R.id.timeTestRecords, this.formatTime(t.getTime()));
        this.setPropertyText(v, R.id.dateTestRecords, this.formatDate(t.getDate()));
        this.setPropertyText(v, R.id.gradeTestRecords, this.calculateGradeColor(t.getGrade()));

        return v;
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

    private String formatDate(Date dt) {
        DateTime dto = new DateTime(dt);
        return dto.getDayOfMonth() + "/" + dto.getMonthOfYear() + "/" + dto.getYear() + " " + dto.getHourOfDay() + ":" + dto.getMinuteOfHour();
    }
}
