package com.practice.arif.samplequiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myDebugging";
    private Button mTrueBtn;
    private Button mFalseBtn;
    private TextView mQueTxtView;
    private Button mCheatBtn;
    private static final String KEY_INDEX = "index";
    private int mIndex = 0;
    private boolean mIsCheater;
    private static final int REQUEST_CODE_CHEAT = 0;//this is a user-defined integer that is send to the child


    //Question(int , boolean)
    private Question[] mQueBank = new Question[]{
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_asia, true),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_mideast, false)
    };


    private void updateQue() {
        //Log.d(TAG,"Updating Question Text for Que #"+mIndex,new Exception());  Logging on LogCat
        int que = mQueBank[mIndex].getTextResId();
        mQueTxtView.setText(que);
    }

    private void checkAns(boolean userPressed) {

        boolean ans = mQueBank[mIndex].isAnsTrue();
        int msgResId;

        if (mIsCheater) {
            msgResId = R.string.judgmentToast;
        } else {
            if (userPressed == ans) {
                msgResId = R.string.correct_toast;
            } else {
                msgResId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(this, msgResId, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting Question
        mQueTxtView = (TextView) findViewById(R.id.que_txtView);

        //Button Listerners
        mTrueBtn = (Button) findViewById(R.id.true_button);


        mTrueBtn.setOnClickListener(
                //Interface
                new View.OnClickListener() {
                    //callback method
                    public void onClick(View v) {
//                                            Toast.makeText(MainActivity.this,
//                                                    R.string.correct_toast,
//                                                    Toast.LENGTH_SHORT).show();
                        checkAns(true);
                    }
                });

        mFalseBtn = (Button) findViewById(R.id.false_button);
        mFalseBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                                            Toast.makeText(MainActivity.this,
//                                                    R.string.incorrect_toast,
//                                                    Toast.LENGTH_SHORT).show();
                checkAns(false);
            }
        });

        mCheatBtn = (Button) findViewById(R.id.cheatBtn);
        mCheatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent mCheatIntent=new Intent(MainActivity.this,cheatActivity.class); only to start another activity
                boolean ans = mQueBank[mIndex].isAnsTrue();

                /* My logic ; not working
                mCheatIntent.putExtra("EXTRA_ANS_TRUE",Ans);*/

                Intent mCheatIntent = cheatActivity.myIntent(MainActivity.this, ans);
                //startActivity(mCheatIntent);
                startActivityForResult(mCheatIntent, REQUEST_CODE_CHEAT);
            }
        });


        Button mNextbtn = (Button) findViewById(R.id.next_button);

        mNextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIndex = (mIndex + 1) % mQueBank.length;
                mIsCheater=false;
                updateQue();
            }
        });


        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        updateQue();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        //Below is for Best Practice
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            mIsCheater = cheatActivity.wasAnsShown(data);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, mIndex);
        Toast.makeText(this, "inside Save instance state", Toast.LENGTH_SHORT).show();
    }
}
