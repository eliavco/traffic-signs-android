package com.eliavco.trafficsigns;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SignDetailsActivity extends AppCompatActivity {
    Sign s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_details);
        String sid = getIntent().getStringExtra("SIGN_ID");
        Dal dal = new Dal(this);
        this.s = dal.getSign(sid);
        TextView signTitle = findViewById(R.id.signTitle);
        signTitle.setText(s.getId() + " " + s.getGroup());
    }
}