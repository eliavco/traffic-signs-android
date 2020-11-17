package com.eliavco.trafficsigns;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.graphics.text.LineBreaker;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
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
        this.setImage();
        this.setDetails(R.id.signId, this.s.getId().trim());
        this.setDetails(R.id.signCategory, this.s.getCategory().trim());
        this.setDetails(R.id.signGroup, this.s.getGroup());
        this.setDetails(R.id.signMeaning, this.s.getMeaning());
        this.setDetails(R.id.signPurpose, this.s.getPurpose().trim());
        this.setDetails(R.id.signPower, this.s.getPower());
    }

    protected void setDetails(int dId, String value) {
        TextView signDetailsText = findViewById(dId);
        signDetailsText.setText(value);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            signDetailsText.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
        }
    }

    protected void setImage() {
        ImageView signImage = findViewById(R.id.signImage);
        try {
            Drawable d = this.s.getImage(getAssets());
            signImage.setImageDrawable(d);
        } catch(Exception e) {}
    }
}