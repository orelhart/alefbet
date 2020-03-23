package com.orelhart.alefbet;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.GridLayout;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.orelhart.alefbet.data.AlphaBet;

public class LetterGridLayout extends GridLayout {

  private LetterView[] mLetterViewsArray;

  public LetterGridLayout(Context context) {
    super(context);
  }

  public LetterGridLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public LetterGridLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public LetterGridLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  public void setLetterViewsToGrid(Context context, AlphaBet alphabet, int cellWidth) {

    mLetterViewsArray = new LetterView[alphabet.getAlphabetSize()];

    for (int i = 0; i < alphabet.getAlphabetSize(); i++) {

      LayoutInflater inflater =
          (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
      FrameLayout frameLayout =
          (FrameLayout) inflater.inflate(R.layout.letter_frame_layout, this, false);
      LetterView v = frameLayout.findViewById(R.id.letter_view_in_frame);
      v.setLetter(alphabet.getLetter(i));
      v.setLetterVisibility(false);
      v.setRotationY(180);
      v.setTextColor(ContextCompat.getColor(context, alphabet.getLetter(i).getmColorResource()));
      mLetterViewsArray[i] = v;

      int cellMargin = (int) getResources().getDimension(R.dimen.cell_margin);

      this.addView(frameLayout, cellWidth, (int) (cellWidth / 0.6));

      int topMargin = 0;
      int bottomMargin = cellMargin;
      int leftMargin = 0;
      int rightMargin = cellMargin;

      if (i % this.getColumnCount() == 0) {
        leftMargin = cellMargin;
      }

      if (i / this.getColumnCount() == 0) {
        topMargin = cellMargin;
      }
      ((GridLayout.LayoutParams) frameLayout.getLayoutParams())
          .setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
    }
  }

  public int setGridLayoutParams(Context context, GridLayout gridLayout, AlphaBet alphaBet) {

    int numCellsInRow = getResources().getInteger(R.integer.letters_in_row);
    int numOfCells = alphaBet.getAlphabetSize();
    int numOfRows = (int) Math.ceil(numOfCells / numCellsInRow);
    int cellMargin = (int) getResources().getDimension(R.dimen.cell_margin);

    int width = context.getResources().getDisplayMetrics().widthPixels;
    int cellWidth = (width - ((numCellsInRow + 1) * cellMargin)) / numCellsInRow;

    gridLayout.setColumnCount(numCellsInRow);
    gridLayout.setRowCount(numOfRows);

    return cellWidth;
  }

  public LetterView getGridLayoutLetterViewByIndex(int index) {

    return mLetterViewsArray[index];
  }

  public LetterView[] getmLetterViewsArray() {
    return mLetterViewsArray;
  }
}
