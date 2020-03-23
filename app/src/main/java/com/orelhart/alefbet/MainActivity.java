package com.orelhart.alefbet;


import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    public static final int MODE_EASY = 1;
    public static final int MODE_HARD = 2;
    public static final String MODE = "mode";
    private static final byte STATE_MAIN_MENU = 0;
    private static final byte STATE_PREFERENCE = 1;
    private byte state;
    private byte musicButtonState;
    final private static byte MUSIC_STOP = 0;
    final private static byte MUSIC_PLAY = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        state = STATE_MAIN_MENU;

        BackgroundMusic.get(this).start();
        musicButtonState = MUSIC_PLAY;


        TextView lettersOrder = findViewById(R.id.game_2);
        TextView identifyTheLetter = findViewById(R.id.game_1);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch(v.getId()){

                    case R.id.game_2:
                        Intent intent = new Intent(MainActivity.this, LettersOrderActivity.class);
                        MainActivity.this.startActivity(intent);
                        break;

                    case R.id.game_1:

                        modePreference();


                        break;
                }
            }
        };
        lettersOrder.setOnClickListener(onClickListener);
        identifyTheLetter.setOnClickListener(onClickListener);


        ImageView volumeButton = findViewById(R.id.sound_button);

        View.OnClickListener sundButtonOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               switch(musicButtonState){

                   case MUSIC_PLAY:

                       BackgroundMusic.get(MainActivity.this).pause();
                       musicButtonState = MUSIC_STOP;
                       setSoundButtonOff();
                       break;


                   case 0:
                       BackgroundMusic.get(MainActivity.this).resume();
                       musicButtonState = MUSIC_PLAY;
                       setSoundButtonOn();
                       break;


                   default:
                       throw new IllegalStateException("Unexpected value: " + musicButtonState);

               }

            }
        };

       volumeButton.setOnClickListener(sundButtonOnClickListener);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }



    private void modePreference(){

        state = STATE_PREFERENCE;

        animateModePreference();

        final TextView easyModeView = findViewById(R.id.mode_easy);
        TextView hardModeView = findViewById(R.id.mode_hard);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent;

                switch (v.getId()) {


                    case R.id.mode_hard:

                        intent = new Intent(MainActivity.this,IdentifyLetterActivity.class);
                        intent.putExtra(MainActivity.MODE, MODE_HARD);
                        MainActivity.this.startActivity(intent);
                        break;

                        default:
                            intent = new Intent(MainActivity.this,IdentifyLetterActivity.class);
                            intent.putExtra(MainActivity.MODE, MODE_EASY);
                            MainActivity.this.startActivity(intent);

                }


            }
        };

        easyModeView.setOnClickListener(onClickListener);
        hardModeView.setOnClickListener(onClickListener);


    }

    private void animateModePreference(){

        LinearLayout linearLayout = findViewById(R.id.linear_layout);
        LinearLayout hidenLinearLayout = findViewById(R.id.hiden_linear_layout);


        linearLayout.animate().alpha(0f).setDuration(400).setInterpolator(new DecelerateInterpolator()).start();
        linearLayout.setVisibility(View.GONE);
        hidenLinearLayout.setAlpha(0f);
        hidenLinearLayout.setVisibility(View.VISIBLE);
        hidenLinearLayout.animate().alpha(1f).setDuration(400).setInterpolator(new DecelerateInterpolator()).start();

    }

    private void setSoundButtonOff(){

        ImageView button = findViewById(R.id.sound_button);
        button.setImageResource(R.drawable.round_volume_off_black_18);
        button.setColorFilter(ContextCompat.getColor(this, R.color.colorVolumeButtonPaused));

    }

    private void setSoundButtonOn(){

        ImageView button = findViewById(R.id.sound_button);
        button.setImageResource(R.drawable.round_volume_up_black_18);
        button.setColorFilter(ContextCompat.getColor(this, R.color.colorVolumeButton));

    }

    @Override
    protected void onStart() {

        LinearLayout linearLayout = findViewById(R.id.linear_layout);
        LinearLayout hidenLinearLayout = findViewById(R.id.hiden_linear_layout);

        linearLayout.setAlpha(1f);
        hidenLinearLayout.setVisibility(View.GONE);

        super.onStart();
    }


    @Override
    public void onBackPressed() {

        switch (state){
            case STATE_MAIN_MENU:
                super.onBackPressed();
                break;

            case STATE_PREFERENCE:

                reverceAnimation();
                state = STATE_MAIN_MENU;
        }

    }

    private void reverceAnimation() {

        LinearLayout linearLayout = findViewById(R.id.linear_layout);
        LinearLayout hidenLinearLayout = findViewById(R.id.hiden_linear_layout);

        hidenLinearLayout.animate().alpha(0f).setDuration(400).setInterpolator(new DecelerateInterpolator()).start();
        hidenLinearLayout.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);
        linearLayout.animate().alpha(1f).setDuration(400).setInterpolator(new DecelerateInterpolator()).start();

    }

    @Override
    protected void onResume() {
        super.onResume();


        LinearLayout linearLayout = findViewById(R.id.linear_layout);
        LinearLayout hidenLinearLayout = findViewById(R.id.hiden_linear_layout);
        linearLayout.setVisibility(View.VISIBLE);
        hidenLinearLayout.setVisibility(View.GONE);
        state = STATE_MAIN_MENU;

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        musicButtonState = sh.getInt("musicState", MUSIC_PLAY);

        if (musicButtonState == MUSIC_STOP){
            BackgroundMusic.get(this).pause();
            setSoundButtonOff();
        }

    }




    protected void onPause()
    {
        super.onPause();

        SharedPreferences sharedPreferences =  getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putInt("musicState", musicButtonState);
        myEdit.apply();
    }


}





