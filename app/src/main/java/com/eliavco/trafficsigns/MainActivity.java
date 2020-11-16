package com.eliavco.trafficsigns;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    }

    public void openCatalog(View view) {
        Intent intent = new Intent(this, SignsCatalogActivity.class);
//        intent.putExtra(EXTRA_MESSAGE, message); // PASS DATA
        startActivity(intent);
    }
}