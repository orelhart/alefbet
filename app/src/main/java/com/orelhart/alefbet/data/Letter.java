package com.orelhart.alefbet.data;

public class Letter {

    private char mLetter;
    private int mSerialNumber;

    public String getLetter() {
        return String.valueOf(mLetter);
    }

    public void setLetter(char letter) {
        this.mLetter = letter;
    }

    public int getmSerialNumber() {
        return mSerialNumber;
    }

    public void setmSerialNumber(int mSerialNumber) {
        this.mSerialNumber = mSerialNumber;
    }
}
