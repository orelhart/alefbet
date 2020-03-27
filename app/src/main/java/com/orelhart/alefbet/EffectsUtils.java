package com.orelhart.alefbet;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

import java.util.Random;

public class EffectsUtils {

    public static int getSoundEffectResource() {

        Random r = new Random();
        int i = r.nextInt(2);
        int resource;

        switch (i){
            case 0:
                resource = R.raw.positive_sound;
                break;

            case 1:
                resource = R.raw.positive_sound_2;
                break;

            default:
                resource = R.raw.positive_sound_2;
        }

        return resource;

    }


    public static AnimatorSet randomWinAnimation(View v){

        ObjectAnimator scaleAnimationX;
        ObjectAnimator scaleAnimationY;
        ObjectAnimator rotationAnimation;
        ObjectAnimator transAnumationX;
        ObjectAnimator transAnumationY;

        AnimatorSet set = new AnimatorSet();
        Random r = new Random();
        int i = r.nextInt(3);

        switch (i){

            case 0:

                scaleAnimationX =
                        ObjectAnimator.ofFloat(v, "scaleX",  1f, 1.4f, 1f);
                scaleAnimationY =
                        ObjectAnimator.ofFloat(v, "scaleY",  1f, 1.4f, 1f);
                rotationAnimation =
                        ObjectAnimator.ofFloat(v, "rotation", 0, 50, 0);
                set.playTogether(scaleAnimationX, scaleAnimationY, rotationAnimation);
                break;

            case 1:

                rotationAnimation =
                        ObjectAnimator.ofFloat(v, "rotation", 0, 360, 720);
                set.playTogether(rotationAnimation);
                break;


            case 2:

                transAnumationX =
                        ObjectAnimator.ofFloat(v, "translationX", 0, 30, 0, -30, 0, 30, 0, -30, 0);
                transAnumationY =
                        ObjectAnimator.ofFloat(v, "translationY", 0, -30, 0, -30, 0, -30, 0, -30, 0  );

                set.playTogether(transAnumationX, transAnumationY );
                break;
        }

        return set;

    }


}
