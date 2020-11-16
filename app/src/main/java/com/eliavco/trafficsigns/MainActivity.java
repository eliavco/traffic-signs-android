package com.eliavco.trafficsigns;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Dal dal = new Dal(this);

        ArrayList<Test> tests = dal.getAllTests();
        for (int i = 0; i < tests.size(); i++) {
            Test t = tests.get(i);
            Log.i("important bla", t + "");
        }
        dal.deleteTest(9);
        Log.i("important bla", "DELETING ****");
        tests = dal.getAllTests();
        for (int i = 0; i < tests.size(); i++) {
            Test t = tests.get(i);
            Log.i("important bla", t + "");
        }

    }
}