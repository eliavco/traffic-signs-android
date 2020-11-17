package com.eliavco.trafficsigns;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.text.LineBreaker;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SignDetailsActivity extends AppCompatActivity {
    Sign s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // INITIALIZE BACK BUTTON
        this.forceRTLIfSupported();

        String sid = getIntent().getStringExtra("SIGN_ID");
        Dal dal = new Dal(this);
        this.s = dal.getSign(sid);
        this.setImage();
        this.setDetailsToView(this.s.getId(), this.s.getCategory(), this.s.getGroup(), this.s.getMeaning(), this.s.getPurpose(), this.s.getPower());
    }

    protected void setDetails(int dId, String value) {
        TextView signDetailsText = findViewById(dId);
        signDetailsText.setText(value);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            signDetailsText.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
        }
    }

    protected void setDetailsToView(String id, String category, String group, String meaning, String purpose, String power) {
        this.setDetails(R.id.signId, id); this.setDetails(R.id.signCategory, category); this.setDetails(R.id.signGroup, group); this.setDetails(R.id.signMeaning, meaning); this.setDetails(R.id.signPurpose, purpose); this.setDetails(R.id.signPower, power);
    }

    protected void setImage() {
        ImageView signImage = findViewById(R.id.signImage);
        try {
            Drawable d = this.s.getImage(getAssets());
            signImage.setImageDrawable(d);
        } catch(Exception e) {}
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