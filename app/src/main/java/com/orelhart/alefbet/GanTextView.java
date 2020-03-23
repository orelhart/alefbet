package com.orelhart.alefbet;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.res.ResourcesCompat;

public class GanTextView extends AppCompatTextView {
  public GanTextView(Context context) {
    super(context);
    init(context);
  }

  public GanTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public GanTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(Context context) {
    Typeface typeface = ResourcesCompat.getFont(context, R.font.gan);
    setTypeface(typeface);
  }
}
