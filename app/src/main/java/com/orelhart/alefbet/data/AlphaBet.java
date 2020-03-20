package com.orelhart.alefbet.data;

import com.orelhart.alefbet.R;

import java.util.Collections;
import java.util.LinkedList;

public class AlphaBet implements Cloneable {

    static private final int[] SOUND_RESOURCE = {R.raw.alef, R.raw.bet, R.raw.gimel, R.raw.daled, R.raw.hei, R.raw.vav, R.raw.zain, R.raw.het, R.raw.tet, R.raw.yud, R.raw.kaf, R.raw.lamed,
            R.raw.mem, R.raw.nun, R.raw.sameh, R.raw.ain, R.raw.pei, R.raw.zadi, R.raw.kuf, R.raw.resh, R.raw.shin, R.raw.taf};

    static private final int[] COLOR_RESURCE = {R.color.color1, R.color.color2, R.color.color3, R.color.color4, R.color.color5, R.color.color6, R.color.color7, R.color.color8, R.color.color9,
            R.color.color10, R.color.color11, R.color.color12, R.color.color13, R.color.color14, R.color.color15, R.color.color16, R.color.color17, R.color.color18, R.color.color19, R.color.color20, R.color.color21, R.color.color22};

    static private final AlphaBet HEBREW = new AlphaBet(true, SOUND_RESOURCE,COLOR_RESURCE,'א', 'ב','ג','ד',
            'ה', 'ו', 'ז', 'ח', 'ט', 'י', 'כ',
            'ל', 'מ', 'נ', 'ס', 'ע', 'פ', 'צ',
            'ק', 'ר', 'ש', 'ת');




    private LinkedList<Letter> mLinkedList = new LinkedList<>();
    private boolean mIsRTL;

    public AlphaBet(Boolean isRTL, int[] soundResource, int[] colorResource,  char... letterArray) {


        for (int i = 0; i < letterArray.length; i++) {
            char value = letterArray[i];
            Letter letter = new Letter();
            letter.setLetter(value);
            letter.setmSerialNumber(i);
            letter.setmSoundResourceFile(soundResource[i]);
            letter.setmColorResource(colorResource[i]);
            mLinkedList.add(letter) ;

        }
        setmIsRTL(isRTL);
    }

    private AlphaBet(AlphaBet alphaBet) {
        mIsRTL = alphaBet.mIsRTL;
        mLinkedList = (LinkedList<Letter>) alphaBet.mLinkedList.clone();

    }

    public boolean ismIsRTL() {
        return mIsRTL;
    }

    public void setmIsRTL(boolean mIsRTL) {
        this.mIsRTL = mIsRTL;
    }

    public Letter getLetter(int position) {
        return mLinkedList.get(position);

    }

    public int getAlphabetSize() {
        return mLinkedList.size();

    }

    public AlphaBet clone() {
        return new AlphaBet(this);
    }

    public void shuffleLettersOrder() {

        Collections.shuffle(this.mLinkedList);

    }

    public static AlphaBet getHebrew(){

        return AlphaBet.HEBREW.clone();
    }

    public int removeLetterFromList( int index){

        for(int i = 0; i< mLinkedList.size(); i++){

            if (mLinkedList.get(i).getmSerialNumber() == index) {
                mLinkedList.remove(i);
                return i;

            }
        }
        return -1;
    }

}
