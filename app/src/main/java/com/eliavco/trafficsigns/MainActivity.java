package com.eliavco.trafficsigns;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.forceRTLIfSupported();
    }

    public void openView(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    public void openCatalog(View view) {
        this.openView(SignsCatalogActivity.class);
    }

    public void openHelp(View view) {
        this.openView(HelpActivity.class);
    }

    public void openHistory(View view) {
        this.openView(HistoryActivity.class);
    }

    public void openTest(View view) {
        this.openView(TestActivity.class);
    }

    public void openRecords(View view) {
        this.openView(RecordsActivity.class);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void forceRTLIfSupported()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }
}