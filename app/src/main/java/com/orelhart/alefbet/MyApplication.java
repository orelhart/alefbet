package com.orelhart.alefbet;

import android.app.Application;
import android.preference.PreferenceManager;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

public class MyApplication extends Application implements LifecycleObserver {

  @Override
  public void onCreate() {
    super.onCreate();
    ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
  public void onAppBackgrounded() {
    BackgroundMusic.get(getApplicationContext()).pause();
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_START)
  public void onAppForegrounded() {

    if (PreferenceManager.getDefaultSharedPreferences(this)
            .getInt(MainActivity.BACKGROUND_MUSIC_STATE, MainActivity.MUSIC_PLAY)
        == MainActivity.MUSIC_PLAY) {
      BackgroundMusic.get(getApplicationContext()).resume();
    }
  }
}
