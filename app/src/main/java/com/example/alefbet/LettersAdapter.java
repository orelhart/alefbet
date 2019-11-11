package com.example.alefbet;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alefbet.data.AlphaBet;
import com.example.alefbet.data.Letter;

import java.util.Arrays;


public class LettersAdapter extends RecyclerView.Adapter<LettersAdapter.LettersViewHolder> {

    private AlphaBet mAlphabet;
    private LetterView.OnClickListener mOnClickListiner;

    public LettersAdapter(AlphaBet alphaBet, LetterView.OnClickListener onClickListener) {
        mAlphabet = alphaBet;
        mOnClickListiner = onClickListener;
    }


    @NonNull
    @Override
    public LettersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LetterView letterView = (LetterView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.letter_view, parent, false);
        letterView.setRotationY(180);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mOnClickListiner.onClickRecyclerView(letterView);
            }
        };

        letterView.setOnClickListener(onClickListener);


        return new LettersViewHolder(letterView);
    }

    @Override
    public void onBindViewHolder(@NonNull LettersViewHolder holder, int position) {
        Letter currentLetter = mAlphabet.getLetter(position);
        LetterView letterView = (LetterView) holder.itemView;
        letterView.setLetter(currentLetter);

    }

    @Override
    public int getItemCount() {
        return mAlphabet.getAlphabetSize();
    }

    public class LettersViewHolder extends RecyclerView.ViewHolder {

        public LettersViewHolder(@NonNull LetterView currentLetterView) {
            super(currentLetterView);
        }
    }

}


