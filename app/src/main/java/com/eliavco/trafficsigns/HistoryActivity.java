package com.eliavco.trafficsigns;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.GridView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class HistoryActivity extends AppCompatActivity {
    ArrayList<Test> tests;
    WebView chart;
    Dal dal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // INITIALIZE BACK BUTTON
        this.forceRTLIfSupported();
        getSupportActionBar().setTitle(getString(R.string.app_name_history)); // INITIALIZE NEW TITLE

        this.dal = new Dal(this);
        this.tests = this.dal.getAllTests();
        Collections.reverse(this.tests);
        this.setTests();

        this.initializeChart();
    }

    protected void setTests() {
        ListView testsView = findViewById(R.id.listTestsHistory);
        TestAdapterHistory adap = new TestAdapterHistory(this, R.layout.test_history_layout, this.tests);
        testsView.setAdapter(adap);
        testsView.setEmptyView(findViewById(R.id.emptyElementTestsHistory));
    }

    private String getAssetFile(String path) {
        StringBuilder sb = new StringBuilder();
        String html = "";
        try {
            InputStream is = getAssets().open(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8 ));
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            br.close();
            html = sb.toString();
        } catch(Exception e) {}
        return html;
    }

    private String injectData(String rawHtml) {
        String data = new Gson().toJson(this.tests);
        String newHtml = rawHtml.replace("<<<PLACEHOLDER>>>", data);
        return newHtml;
    }

    protected void initializeChart() {
        this.chart = findViewById(R.id.chartTests);
        if (this.tests.size() < 1) { this.chart.setVisibility(View.GONE); }
        String html = this.getAssetFile("html/chart.html");
        html = injectData(html);
        html = html.replaceAll("#", "%23");
        this.chart.getSettings().setJavaScriptEnabled(true);
        this.chart.getSettings().setDomStorageEnabled(true);
        this.chart.setWebContentsDebuggingEnabled(true);
        this.chart.loadData(html, "text/html", "utf-8");
    }

    protected void deleteTestView(long tid) {
        this.dal.deleteTest(tid);
        int indexTestView = this.findTestIndexById(tid);
        this.tests.remove(indexTestView);
        if (this.tests.size() < 1) { this.chart.setVisibility(View.GONE); }
        this.setTests();
    }

    private int findTestIndexById(long id) {
        for (int i = 0; i < this.tests.size(); i++) {
            if (this.tests.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
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