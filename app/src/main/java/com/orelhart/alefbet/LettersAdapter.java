package com.orelhart.alefbet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.orelhart.alefbet.data.AlphaBet;
import com.orelhart.alefbet.data.Letter;

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
    final LetterView letterView =
        (LetterView)
            LayoutInflater.from(parent.getContext()).inflate(R.layout.letter_view, parent, false);
    letterView.setRotationY(180);

    View.OnClickListener onClickListener =
        new View.OnClickListener() {
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
    letterView.setTextColor(
        ContextCompat.getColor(letterView.getContext(), currentLetter.getmColorResource()));
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
