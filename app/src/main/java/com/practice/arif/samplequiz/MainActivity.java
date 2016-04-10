package com.practice.arif.samplequiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mTrueBtn;
    private Button mFalseBtn;
    private Button mNextbtn;
    private TextView mQueTxtView;
    private static final String KEY_INDEX="index";

//Question(int , boolean)
    private Question[] mQueBank=new Question[]{
            new Question(R.string.question_oceans,true),
            new Question(R.string.question_africa,false),
            new Question(R.string.question_asia,true),
            new Question(R.string.question_americas,true),
            new Question(R.string.question_mideast,false)
    };

    private int mIndex=0;

    private void updateQue(){
        int que=mQueBank[mIndex].getTextResId();
        mQueTxtView.setText(que);
    }

    private void checkAns(boolean userPressed){

        boolean Ans =mQueBank[mIndex].isAnsTrue();
        int msgResId=0;

        if(userPressed== Ans){
            msgResId=R.string.correct_toast;
        }else {
            msgResId=R.string.incorrect_toast;
        }
        Toast.makeText(this, msgResId,Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting Question
        mQueTxtView =(TextView) findViewById(R.id.que_txtView);

        //Button Listerners
        mTrueBtn=(Button) findViewById(R.id.true_button);
        mTrueBtn.setOnClickListener(new View.OnClickListener()
        {
                                        public void onClick(View v){
//                                            Toast.makeText(MainActivity.this,
//                                                    R.string.correct_toast,
//                                                    Toast.LENGTH_SHORT).show();
                                            checkAns(true);
                                        }
                                    }
        );
        mFalseBtn=(Button) findViewById(R.id.false_button);
        mFalseBtn.setOnClickListener(new View.OnClickListener()
                                    {
                                        public void onClick(View v){
//                                            Toast.makeText(MainActivity.this,
//                                                    R.string.incorrect_toast,
//                                                    Toast.LENGTH_SHORT).show();
                                            checkAns(false);
                                        }
                                    });

        mNextbtn=(Button) findViewById(R.id.next_button);
        mNextbtn.setOnClickListener(new View.OnClickListener(){
                                        @Override
                                        public void onClick(View v) {
                                         mIndex=(mIndex+1)%mQueBank.length;
                                            updateQue();
                                        }
                                    });
        if(savedInstanceState != null){
            mIndex= savedInstanceState.getInt(KEY_INDEX,0);

        }
        updateQue();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX,mIndex);
        Toast.makeText(this, "inside Save instance state",Toast.LENGTH_SHORT).show();

    }
}
