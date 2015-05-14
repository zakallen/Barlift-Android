package com.barliftapp.barlift.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.barliftapp.barlift.R;
import com.parse.ConfigCallback;
import com.parse.ParseConfig;
import com.parse.ParseException;
import com.parse.ParseUser;

public class OnBoard3Activity extends ActionBarActivity {

    VideoView videoHolder;
    ParseUser currentUser;
    String[] mAffinities;
    RadioGroup radiogroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board3);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        videoHolder = (VideoView) findViewById(R.id.iv_onboard);
        videoHolder.setVideoURI(Uri.parse("android.resource://com.barliftapp.barlift/" + R.raw.wine));
        videoHolder.requestFocus();
        videoHolder.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        videoHolder.start();

//        Intent intent = getIntent();
//        boolean isStudent = intent.getBooleanExtra("isStudent", true);
//
//        TextView questionView = (TextView) findViewById(R.id.tv_onboard_question);
//        String question = (isStudent) ? "Where do you go to school?" : "Where did you go to school?";
//        questionView.setText(question);

        currentUser = ParseUser.getCurrentUser();

        populateRadioGroup();

    }

    private void populateRadioGroup(){
        // get reference to radio group in layout
        radiogroup = (RadioGroup) findViewById(R.id.radiogroupAff);
        // layout params to use when adding each radio button
        final LinearLayout.LayoutParams layoutParams = new RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.MATCH_PARENT,
                RadioGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(10, 10, 10, 10);
        layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());

        ParseConfig.getInBackground(new ConfigCallback() {
            @Override
            public void done(ParseConfig config, ParseException e) {
                if (e == null) {
                    Log.d("TAG", "Yay! Config was fetched from the server.");
                } else {
                    Log.e("TAG", "Failed to fetch. Using Cached Config.");
                    config = ParseConfig.getCurrentConfig();
                }
                if (currentUser.getString("gender").equals("female")){
                    mAffinities = config.getList("sororities").toArray(new String[config.getList("sororities").size()]);
                }else{
                    mAffinities = config.getList("fraternities").toArray(new String[config.getList("fraternities").size()]);
                }

                for (int i = 0; i < mAffinities.length; i++) {
                    RadioButton newRadioButton = new RadioButton(OnBoard3Activity.this);
                    String label = mAffinities[i];
                    newRadioButton.setBackground(getResources().getDrawable(R.drawable.white_btn));
                    newRadioButton.setButtonDrawable(R.color.trans);
                    newRadioButton.setText(label);
                    newRadioButton.setGravity(Gravity.CENTER);
                    newRadioButton.setId(i);
                    radiogroup.addView(newRadioButton, layoutParams);
                }
            }
        });
    }

    public void onNextClick(View v) {
        if (radiogroup.getCheckedRadioButtonId() != -1) {
            currentUser.put("affiliation", mAffinities[radiogroup.getCheckedRadioButtonId()]);
            currentUser.saveInBackground();
            Intent main = new Intent(this, MainActivity.class);
            main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            main.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(main);
            finish();
        } else {
            Toast.makeText(this, "You must select a group", Toast.LENGTH_SHORT).show();
        }
//        currentUser.put("is_student", true);
//        currentUser.saveInBackground();
    }

    @Override
    public void onResume(){
        super.onResume();
        videoHolder.resume();
    }

    @Override
    public void onPause(){
        super.onPause();
        videoHolder.pause();
    }
}
