package com.orelhart.alefbet.data;

public class Letter {

  private char mLetter;
  private int mSerialNumber;
  private int mSoundResourceFile;
  private int mColorResource;

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

  public int getmSoundResourceFile() {
    return this.mSoundResourceFile;
  }

  public void setmSoundResourceFile(int resource) {
    this.mSoundResourceFile = resource;
  }

  public int getmColorResource() {
    return mColorResource;
  }

  public void setmColorResource(int mColorResource) {
    this.mColorResource = mColorResource;
  }
}
