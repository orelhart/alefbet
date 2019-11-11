package com.orelhart.alefbet.data;

import java.util.Collections;
import java.util.LinkedList;

public class AlphaBet implements Cloneable {

    static private final AlphaBet HEBREW = new AlphaBet(true, 'א', 'ב','ג','ד',
            'ה', 'ו', 'ז', 'ח', 'ט', 'י', 'כ',
            'ל', 'מ', 'נ', 'ס', 'ע', 'פ', 'צ',
            'ק', 'ר', 'ש', 'ת');


    private LinkedList<Letter> mLinkedList = new LinkedList<>();
    private boolean mIsRTL;

    public AlphaBet(Boolean isRTL, char... LetterArray) {


        for (int i = 0; i < LetterArray.length; i++) {
            char value = LetterArray[i];
            Letter letter = new Letter();
            letter.setLetter(value);
            letter.setmSerialNumber(i);
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
