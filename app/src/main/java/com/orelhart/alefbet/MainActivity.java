package com.orelhart.alefbet;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.DecelerateInterpolator;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.orelhart.alefbet.data.AlphaBet;

public class MainActivity extends AppCompatActivity implements LetterView.OnClickListener{

    int index = 0;
    LetterGridLayout letterGridLayout;
    AlphaBet mShuffledAlphabet;
    RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        AlphaBet mAlphabet = AlphaBet.getHebrew();
        mShuffledAlphabet = AlphaBet.getHebrew();
        mShuffledAlphabet.shuffleLettersOrder();
        letterGridLayout = findViewById(R.id.grid_layout);


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);


        LinearLayoutManager layoutManager;
        if (mAlphabet.ismIsRTL()) {
            layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
            layoutManager.setReverseLayout(false);
        } else {
            layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        }

        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new LettersAdapter(mShuffledAlphabet, this);
        recyclerView.setAdapter(mAdapter);

        int mCellWidth = letterGridLayout.setGridLayoutParams(this, letterGridLayout, mAlphabet);
        letterGridLayout.setLetterViewsToGrid(this, mAlphabet, mCellWidth);

    }

    public void onHintClick(){

        int letterViewArryLength = letterGridLayout.getmLetterViewsArray().length;
        LetterView letterView;

        for (int i = index ; i<letterViewArryLength ; i++){
           letterView= letterGridLayout.getGridLayoutLetterViewByIndex(i);
           letterView.setHint(!letterView.isHint());
        }

        for (int i=index ; i<letterViewArryLength; i++){
            letterView= letterGridLayout.getGridLayoutLetterViewByIndex(i);
            if(letterView.isHint()){
                letterView.setLetterVisibility(true);
                letterView.setHint(true);
            }
            else{
                letterView.setLetterVisibility(false);
                letterView.setHint(false);
            }

        }

    }

    @Override
    public void onClickRecyclerView(LetterView clickedLetter){


        if (clickedLetter.getLetter().getmSerialNumber() == index){

           LetterView letterView =letterGridLayout.getGridLayoutLetterViewByIndex(index);
           letterView.setLetterVisibility(true);
           letterView.setHint(false);

            letterView.setScaleX(1.5f);
            letterView.setScaleY(1.5f);
            letterView.setAlpha(0.6f);
            letterView.setRotation(45);
            letterView.animate().scaleX(1f).scaleY(1f).alpha(1f).setDuration(500).rotation(0).setInterpolator(new DecelerateInterpolator()).start();

            int indexInSuffeledList = mShuffledAlphabet.removeLetterFromList(index);
            mAdapter.notifyItemRemoved(indexInSuffeledList);
            mAdapter.notifyItemRangeChanged(indexInSuffeledList, mShuffledAlphabet.getAlphabetSize());


            if(mAdapter.getItemCount() == 0){
                showAlertDialog();
            }
            index++;

        }
        else {
            ObjectAnimator transAnimation = ObjectAnimator.ofFloat(clickedLetter,"translationX",-10,10,-10,10,-10,10,0);
            transAnimation.setDuration(440);
            transAnimation.start();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            onHintClick();
        }
        return super.onOptionsItemSelected(item);
    }

    public void showAlertDialog() {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.alert_dialog_title);
        builder.setMessage(R.string.alert_dialog_text);
        builder.setCancelable(false);


        builder.setPositiveButton(R.string.alert_dialog_button, new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

