package com.eliavco.trafficsigns;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class RecordsActivity extends AppCompatActivity {
    ArrayList<Test> bestTests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // INITIALIZE BACK BUTTON
        this.forceRTLIfSupported();
        getSupportActionBar().setTitle(getString(R.string.app_name_records)); // INITIALIZE NEW TITLE

        Dal dal = new Dal(this);
        ArrayList<Test> tests = dal.getAllTests();

        this.bestTests = this.getBestTests(tests);

        ListView testsView = findViewById(R.id.listTestsRecords);
        TestAdapterRecords adap = new TestAdapterRecords(this, R.layout.test_records_layout, this.bestTests);
        testsView.setAdapter(adap);

        GridView firstTestsView = findViewById(R.id.listFirstTestsRecords);
        FirstTestAdapterRecords adap2 = new FirstTestAdapterRecords(this, R.layout.first_test_records_layout, this.bestTests);
        firstTestsView.setAdapter(adap2);
        firstTestsView.setEmptyView(findViewById(R.id.emptyElementTestsRecords));
    }

    private ArrayList<Test> getBestTests(ArrayList<Test> tests) {
        ArrayList<Test> sortedTests = this.sortByBest(tests);
        if (sortedTests.size() > 10) {
            sortedTests = new ArrayList<Test>(sortedTests.subList(0, 10));
        }
        return sortedTests;
    }

    private ArrayList<Test> sortByBest(ArrayList<Test> tests) {
        ArrayList<Test> newTests = (ArrayList<Test>) tests.clone();
        Collections.sort(newTests);
        return newTests;
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
}