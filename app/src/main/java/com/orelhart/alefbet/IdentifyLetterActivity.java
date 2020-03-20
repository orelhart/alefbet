package com.orelhart.alefbet;

import android.annotation.TargetApi;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.orelhart.alefbet.data.AlphaBet;

import java.util.LinkedList;
import java.util.Random;

public class IdentifyLetterActivity extends AppCompatActivity {
    AlphaBet mShuffledAlphabet;
    int mCurrentLetterId;
    final LinkedList<LetterView> viewList = new LinkedList<>();
    MediaPlayer mediaPlayer;
    int mMode;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        mMode = intent.getIntExtra(MainActivity.MODE, MainActivity.MODE_EASY);
        setContentView(R.layout.activity_identify_letter);
        forceRTLIfSupported();
        init();

    }

    private boolean isItTheCorrectLetter(LetterView letterView) {


        return viewList.get(mCurrentLetterId).getLetter().getmSerialNumber() == letterView.getLetter().getmSerialNumber();
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void forceRTLIfSupported() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }

    private int getRendomToast() {

        Random r = new Random();
        int i = r.nextInt(3);
        int src;

        switch (i) {
            case 0:
                src = R.string.correct;
                break;
            case 1:
                src = R.string.perfect;
                break;
            case 2:
                src = R.string.good_job;
                break;
            default:
                src = 0;
        }
        return src;
    }


    private void showSnackbar(Boolean isTrue, View v) {

        String text;

        if (isTrue) {
            text = getString(getRendomToast());

        } else {
            text = getString(R.string.try_again);
        }

        final Snackbar snackbar = Snackbar.make(v, text, Snackbar.LENGTH_SHORT);
        snackbar.show();

    }

    private void init() {

        LetterView viewLetter1 = findViewById(R.id.letter_no_1);
        LetterView viewLetter2 = findViewById(R.id.letter_no_2);
        LetterView viewLetter3 = findViewById(R.id.letter_no_3);
        LetterView viewLetter4 = findViewById(R.id.letter_no_4);
        LetterView viewLetter5 = findViewById(R.id.letter_no_5);
        LetterView viewLetter6 = findViewById(R.id.letter_no_6);


        viewList.add(viewLetter1);
        viewList.add(viewLetter2);
        viewList.add(viewLetter3);

        mShuffledAlphabet = AlphaBet.getHebrew();
        mShuffledAlphabet.shuffleLettersOrder();

        viewLetter1.setLetter(mShuffledAlphabet.getLetter(0));
        viewLetter2.setLetter(mShuffledAlphabet.getLetter(1));
        viewLetter3.setLetter(mShuffledAlphabet.getLetter(2));


        if (mMode == MainActivity.MODE_HARD) {
            LinearLayout hardModeLinearLayout = findViewById(R.id.hard_mode_linear_layout);
            hardModeLinearLayout.setVisibility(View.VISIBLE);


            viewList.add(viewLetter4);
            viewList.add(viewLetter5);
            viewList.add(viewLetter6);

            viewLetter4.setLetter(mShuffledAlphabet.getLetter(3));
            viewLetter5.setLetter(mShuffledAlphabet.getLetter(4));
            viewLetter6.setLetter(mShuffledAlphabet.getLetter(5));


        }


        Random r = new Random();
        mCurrentLetterId = r.nextInt(viewList.size());


        if (mediaPlayer != null) {
            mediaPlayer.stop();
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.release();
            }
        }
        mediaPlayer = MediaPlayer.create(this, viewList.get(mCurrentLetterId).getLetter().getmSoundResourceFile());
        mediaPlayer.start();


        View.OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if (isItTheCorrectLetter((LetterView) v)) {

                    showSnackbar(true, v);


                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            init();
                        }
                    }, 1500);

                } else {

                    showSnackbar(false, v);
                }
            }
        };

        viewLetter1.setOnClickListener(onClickListener);
        viewLetter2.setOnClickListener(onClickListener);
        viewLetter3.setOnClickListener(onClickListener);

        if (mMode == MainActivity.MODE_HARD) {

            viewLetter4.setOnClickListener(onClickListener);
            viewLetter5.setOnClickListener(onClickListener);
            viewLetter6.setOnClickListener(onClickListener);

        }


        Button playButton = findViewById(R.id.play_again_view);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}



