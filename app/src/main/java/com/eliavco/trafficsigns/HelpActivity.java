package com.eliavco.trafficsigns;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.graphics.text.LineBreaker;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

public class HelpActivity extends AppCompatActivity {
    private static final String VIDEO_SAMPLE = "car";
    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // INITIALIZE BACK BUTTON
        getSupportActionBar().setTitle(getString(R.string.app_name_help)); // INITIALIZE NEW TITLE
        this.forceRTLIfSupported();
        this.justifyHelpParagraphs();
        this.mVideoView = findViewById(R.id.videoview);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializePlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releasePlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            this.mVideoView.pause();
        }
    }

    private Uri getMedia(String mediaName) {
        return Uri.parse("android.resource://" + getPackageName() +
                "/raw/" + mediaName);
    }

    private void initializePlayer() {
        Uri videoUri = getMedia(VIDEO_SAMPLE);
        this.mVideoView.setVideoURI(videoUri);
        MediaController controller = new MediaController(this);
        controller.setMediaPlayer(mVideoView);
        this.mVideoView.setMediaController(controller);
        ((ScrollView) findViewById(R.id.helpScrollContainer)).smoothScrollTo(0,0); // GO Back to top
    }

    private void releasePlayer() {
        this.mVideoView.stopPlayback();
    }

    protected void justifyHelpParagraphs() {
        int[] paragraphs = new int[]{ R.id.goalTextHelp, R.id.catalogTextHelp, R.id.functionsTextHelp, R.id.goodluckTextHelp, R.id.helpTextHelp, R.id.testTextHelp, R.id.recordsTextHelp, R.id.historyTextHelp, R.id.homeTextHelp };
        for (int i = 0; i < paragraphs.length; i++) {
            this.justifyParagraphs(paragraphs[i]);
        }
    }

    protected void justifyParagraphs(int tid) {
        TextView t = findViewById(tid);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            t.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
        }
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