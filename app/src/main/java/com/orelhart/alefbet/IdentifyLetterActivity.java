package com.orelhart.alefbet;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.orelhart.alefbet.data.AlphaBet;

import java.util.LinkedList;
import java.util.Random;

public class IdentifyLetterActivity extends AppCompatActivity {
  final LinkedList<LetterView> viewList = new LinkedList<>();
  AlphaBet mShuffledAlphabet;
  int mCurrentLetterId;
  MediaPlayer mediaPlayer;
  int mMode;
  LetterView viewLetter1;
  LetterView viewLetter2;
  LetterView viewLetter3;
  LetterView viewLetter4;
  LetterView viewLetter5;
  LetterView viewLetter6;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Intent intent = getIntent();
    mMode = intent.getIntExtra(MainActivity.MODE, MainActivity.MODE_EASY);
    setContentView(R.layout.activity_identify_letter);
    init();
  }

  private boolean isItTheCorrectLetter(LetterView letterView) {

    return viewList.get(mCurrentLetterId).getLetter().getmSerialNumber()
        == letterView.getLetter().getmSerialNumber();
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

    viewLetter1 = findViewById(R.id.letter_no_1);
    viewLetter2 = findViewById(R.id.letter_no_2);
    viewLetter3 = findViewById(R.id.letter_no_3);
    viewLetter4 = findViewById(R.id.letter_no_4);
    viewLetter5 = findViewById(R.id.letter_no_5);
    viewLetter6 = findViewById(R.id.letter_no_6);

    viewList.add(viewLetter1);
    viewList.add(viewLetter2);
    viewList.add(viewLetter3);

    mShuffledAlphabet = AlphaBet.getHebrew();
    mShuffledAlphabet.shuffleLettersOrder();

    viewLetter1.setLetter(mShuffledAlphabet.getLetter(0));
    viewLetter2.setLetter(mShuffledAlphabet.getLetter(1));
    viewLetter3.setLetter(mShuffledAlphabet.getLetter(2));

    viewLetter1.setTextColor(
        ContextCompat.getColor(this, mShuffledAlphabet.getLetter(0).getmColorResource()));
    viewLetter2.setTextColor(
        ContextCompat.getColor(this, mShuffledAlphabet.getLetter(1).getmColorResource()));
    viewLetter3.setTextColor(
        ContextCompat.getColor(this, mShuffledAlphabet.getLetter(2).getmColorResource()));

    if (mMode == MainActivity.MODE_HARD) {
      LinearLayout hardModeLinearLayout = findViewById(R.id.hard_mode_linear_layout);
      hardModeLinearLayout.setVisibility(View.VISIBLE);

      viewList.add(viewLetter4);
      viewList.add(viewLetter5);
      viewList.add(viewLetter6);

      viewLetter4.setLetter(mShuffledAlphabet.getLetter(3));
      viewLetter5.setLetter(mShuffledAlphabet.getLetter(4));
      viewLetter6.setLetter(mShuffledAlphabet.getLetter(5));

      viewLetter4.setTextColor(
          ContextCompat.getColor(this, mShuffledAlphabet.getLetter(3).getmColorResource()));
      viewLetter5.setTextColor(
          ContextCompat.getColor(this, mShuffledAlphabet.getLetter(4).getmColorResource()));
      viewLetter6.setTextColor(
          ContextCompat.getColor(this, mShuffledAlphabet.getLetter(5).getmColorResource()));
    }

    Random r = new Random();
    mCurrentLetterId = r.nextInt(viewList.size());

    if (mediaPlayer != null) {
      mediaPlayer.stop();
      if (!mediaPlayer.isPlaying()) {
        mediaPlayer.release();
      }
    }
    mediaPlayer =
        MediaPlayer.create(
            this, viewList.get(mCurrentLetterId).getLetter().getmSoundResourceFile());
    mediaPlayer.setVolume(1f, 1f);
    mediaPlayer.start();

    View.OnClickListener onClickListener =
        new View.OnClickListener() {

          @Override
          public void onClick(final View v) {

            if (isItTheCorrectLetter((LetterView) v)) {

              showSnackbar(true, v);

              setLettersDisabled();
              new Handler()
                  .postDelayed(
                      new Runnable() {
                        @Override
                        public void run() {
                          init();
                          setLettersEnabled();
                        }
                      },
                      1500);

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

    ImageView playButton = findViewById(R.id.play_again_button);
    playButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            mediaPlayer.start();
          }
        });
  }

  private void setLettersDisabled() {

    viewLetter1.setEnabled(false);
    viewLetter2.setEnabled(false);
    viewLetter3.setEnabled(false);

    if (mMode == MainActivity.MODE_HARD) {

      viewLetter4.setEnabled(false);
      viewLetter5.setEnabled(false);
      viewLetter6.setEnabled(false);
    }
  }

  private void setLettersEnabled() {

    viewLetter1.setEnabled(true);
    viewLetter2.setEnabled(true);
    viewLetter3.setEnabled(true);

    if (mMode == MainActivity.MODE_HARD) {

      viewLetter4.setEnabled(true);
      viewLetter5.setEnabled(true);
      viewLetter6.setEnabled(true);
    }
  }

  @Override
  public void onBackPressed() {

    super.onBackPressed();
  }
}
