package com.orelhart.alefbet;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.orelhart.alefbet.data.Letter;

//public class LetterView extends androidx.appcompat.widget.AppCompatTextView {
public class LetterView extends AppCompatTextView {


    private boolean hint;
    private Letter letter;

    public LetterView(Context context) {
        super(context);
    }

    public LetterView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public LetterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean isHint() {
        return hint;
    }

    public void setHint(boolean hint) {
        this.hint = hint;
        setAlpha(hint? 0.2f : 1f);
    }

    public Letter getLetter() {
        return letter;
    }

    public void setLetter(Letter letter) {
        this.letter = letter;
        setText(letter.getLetter());

    }

    public void setLetterVisibility(boolean isLetterVisible) {
        if (!isLetterVisible) {
            setText("");
        } else {
            setText(letter.getLetter());

        }
    }

     public interface OnClickListener{

         void onClickRecyclerView(LetterView letterView);
     }



}





