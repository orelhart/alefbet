package com.orelhart.alefbet;

import android.content.Context;
import android.media.MediaPlayer;

public class BackgroundMusic {

  private static BackgroundMusic backgroundMusic = null;

  private MediaPlayer mMediaPlayer;
  private Context appContext;

  private BackgroundMusic(Context context) {
    appContext = context.getApplicationContext();
  }

  public static BackgroundMusic get(Context context) {

    if (backgroundMusic == null) {
      backgroundMusic = new BackgroundMusic(context);
    }

    return backgroundMusic;
  }

  public void pause() {
    mMediaPlayer.pause();
  }

  public void resume() {
    mMediaPlayer.start();
  }

  public void start() {

    if (mMediaPlayer != null) {
      return;
    }

    mMediaPlayer = MediaPlayer.create(appContext, R.raw.background_music);
    mMediaPlayer.setLooping(true);
    mMediaPlayer.setVolume(0.015f, 0.015f);
    mMediaPlayer.start();
  }
}
