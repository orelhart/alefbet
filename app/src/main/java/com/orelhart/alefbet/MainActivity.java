package com.orelhart.alefbet;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        TextView lettersOrder = findViewById(R.id.letters_order);
        TextView identifyTheLetter = findViewById(R.id.identify_letter);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){

                    case R.id.letters_order:
                        Intent intent = new Intent(MainActivity.this, LettersOrderActivity.class);
                        MainActivity.this.startActivity(intent);
                        break;

                    case R.id.identify_letter:
                        //TODO
                        break;

                }

            }
        };
        lettersOrder.setOnClickListener(onClickListener);
        identifyTheLetter.setOnClickListener(onClickListener);



    }


}

