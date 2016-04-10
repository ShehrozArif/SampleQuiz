package com.practice.arif.samplequiz;

/**
 * Created by khan on 4/5/2016.
 */
public class Question {
    private int mTextResId;
    private boolean mAnsTrue;

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnsTrue() {
        return mAnsTrue;
    }

    public void setAnsTrue(boolean ansTrue) {
        mAnsTrue = ansTrue;
    }

    Question(int textResId,boolean ansTrue){
        mTextResId=textResId;
        mAnsTrue=ansTrue;
    }
}
