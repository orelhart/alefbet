package com.orelhart.alefbet;


import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final int MODE_EASY = 1;
    public static final int MODE_HARD = 2;
    public static final String MODE = "mode";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        forceRTLIfSupported();
        setContentView(R.layout.activity_main);

        TextView lettersOrder = findViewById(R.id.game_2);
        TextView identifyTheLetter = findViewById(R.id.game_1);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent;
                switch(v.getId()){

                    case R.id.game_2:
                        intent = new Intent(MainActivity.this, LettersOrderActivity.class);
                        MainActivity.this.startActivity(intent);
                        break;

                    case R.id.game_1:

                        modePreferation();


                        break;
                }
            }
        };
        lettersOrder.setOnClickListener(onClickListener);
        identifyTheLetter.setOnClickListener(onClickListener);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void forceRTLIfSupported()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }


    private void modePreferation(){

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
        hidenLinearLayout.setAlpha(0f);
        hidenLinearLayout.setVisibility(View.VISIBLE);
        hidenLinearLayout.animate().alpha(1f).setDuration(400).setInterpolator(new DecelerateInterpolator()).start();



    }

    @Override
    protected void onStart() {

        LinearLayout linearLayout = findViewById(R.id.linear_layout);
        LinearLayout hidenLinearLayout = findViewById(R.id.hiden_linear_layout);

        linearLayout.setAlpha(1f);
        hidenLinearLayout.setVisibility(View.GONE);

        super.onStart();
    }





}




