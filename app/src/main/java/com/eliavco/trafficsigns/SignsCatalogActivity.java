package com.eliavco.trafficsigns;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class SignsCatalogActivity extends AppCompatActivity {
    ArrayList<Sign> signs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signs_catalog);
        Dal dal = new Dal(this);
        this.signs = dal.getAllSigns();
        ListView signsView = findViewById(R.id.listSigns);
        SignsAdapterHome adap = new SignsAdapterHome(this, R.layout.sign_layout, this.signs);
        signsView.setAdapter(adap);
    }
}