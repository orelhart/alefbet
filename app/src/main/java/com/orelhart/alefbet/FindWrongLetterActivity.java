package com.orelhart.alefbet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.orelhart.alefbet.data.AlphaBet;

import java.util.Random;

import static com.orelhart.alefbet.EffectsUtils.getSoundEffectResource;
import static com.orelhart.alefbet.EffectsUtils.randomWinAnimation;

public class FindWrongLetterActivity extends AppCompatActivity {


    LetterView letterView1;
    LetterView letterView2;
    LetterView letterView3;
    LetterView letterView4;
    AlphaBet mAlphabet;
    LetterView[] letterViewArray;
    int correctLetter;
    int wrongLetterIndex;
    MediaPlayer mediaPlayer;
    ImageView hintButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_wrong_letter);

        init();

    }

    private void init(){

        letterView1 =findViewById(R.id.sequence_letter_no_1);
        letterView2 =findViewById(R.id.sequence_letter_no_2);
        letterView3 =findViewById(R.id.sequence_letter_no_3);
        letterView4 =findViewById(R.id.sequence_letter_no_4);
        letterViewArray = new LetterView[]{letterView1, letterView2,letterView3, letterView4};
        mAlphabet = AlphaBet.getHebrew();

        getLettersSequence();

        View.OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (isItTHeWrongLetter(v)){

                    if (mediaPlayer != null) {
                        mediaPlayer.stop();
                        if (!mediaPlayer.isPlaying()) {
                            mediaPlayer.release();
                        }
                    }
                    mediaPlayer =
                            MediaPlayer.create(FindWrongLetterActivity.this, getSoundEffectResource());
                    mediaPlayer.setVolume(1f, 1f);
                    mediaPlayer.start();

                    setLettersDisabled();

                    AnimatorSet set =randomWinAnimation(v);
                    set.setDuration(1000);
                    set.start();


                    set.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {

                            setLettersEnabled();
                            init();
                        }
                    });

                }
                else {

                    ObjectAnimator transAnimation =
                            ObjectAnimator.ofFloat(v, "translationX", -10, 10, -10, 10, -10, 10, 0);
                    transAnimation.setDuration(440);
                    transAnimation.start();
                }
            }
        };

        for (LetterView letterView : letterViewArray) {
            letterView.setOnClickListener(onClickListener);
        }


        hintButton = findViewById(R.id.wrong_letter_hint_button);
        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    if (!mediaPlayer.isPlaying()) {
                        mediaPlayer.release();
                    }
                }
                mediaPlayer =
                        MediaPlayer.create(FindWrongLetterActivity.this, letterViewArray[correctLetter].getLetter().getmSoundResourceFile());
                mediaPlayer.setVolume(1f, 1f);
                mediaPlayer.start();
            }
        });

    }

    private boolean isItTHeWrongLetter(View v) {

         return (((LetterView) v).getLetter().getmSerialNumber() == wrongLetterIndex);
    }

    private void getLettersSequence() {

        int firstIndex = getRandomNumber(17);

        correctLetter = getRandomNumber(3);

        wrongLetterIndex = getWrongLetterIndex(firstIndex +correctLetter);


        for (int i = 0; i < letterViewArray.length ; i++ ){

            if (i != correctLetter){

                letterViewArray[i].setLetter(mAlphabet.getLetter(firstIndex + i));
                letterViewArray[i].setTextColor(ContextCompat.getColor(this, mAlphabet.getLetter(firstIndex + i).getmColorResource()));
            }

            else{

                letterViewArray[i].setLetter(mAlphabet.getLetter(wrongLetterIndex));
                letterViewArray[i].setTextColor(ContextCompat.getColor(this, mAlphabet.getLetter(wrongLetterIndex).getmColorResource()));
            }

        }

    }

    private int getWrongLetterIndex(int correctLetterIndex) {

        int i =getRandomNumber(21);

        if ( i == correctLetterIndex){

            i++;
        }
        return i;
    }

    private int getRandomNumber(int number) {

        Random r = new Random();
        return r.nextInt(number);
    }


    private void setLettersDisabled() {

       for (LetterView letterView : letterViewArray){
           letterView.setEnabled(false);
       }
    }

    private void setLettersEnabled() {

        for (LetterView letterView : letterViewArray){
            letterView.setEnabled(true);
        }

    }

}
