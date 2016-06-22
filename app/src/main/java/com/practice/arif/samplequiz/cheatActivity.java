package com.practice.arif.samplequiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class cheatActivity extends AppCompatActivity {

    public static final String EXTRA_ANS_TRUE = "com.practice.arif.samplequiz.ans_true";
    public static final String EXTRA_CHEAT_INFO = "com.practice.arif.samplequiz.cheat_info";
    public static final String KEY_TRUE_ANS = "trueAns";
    boolean trueAns;
    Button mShowAns;
    TextView mAnsTxtView;


    public static Intent myIntent(Context pkgContext, boolean trueAns) {
        Intent intent = new Intent(pkgContext, cheatActivity.class);
        intent.putExtra(EXTRA_ANS_TRUE, trueAns);
        return intent;
    }

    //to help decode the "extra" into something that MainActivity.java can use
    public static Boolean wasAnsShown(Intent result) {
        return result.getBooleanExtra(EXTRA_CHEAT_INFO, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        trueAns = getIntent().getBooleanExtra(EXTRA_ANS_TRUE, false);

        /* My logic ; not working
        boolean i =getIntent().getBooleanExtra(EXTRA_ANS_TRUE,false);
        Log.d("ANSWER", String.valueOf(i));*/
        mAnsTxtView = (TextView) findViewById(R.id.ans_txtView);

        mShowAns = (Button) findViewById(R.id.show_ansBtn);
        mShowAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trueAns) {
                    mAnsTxtView.setText(R.string.true_btn);
                } else {
                    mAnsTxtView.setText(R.string.false_btn);
                }
                cheatInfo(true);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // to check that if the Cheating is already done
        if (savedInstanceState != null) {
            trueAns = savedInstanceState.getBoolean(KEY_TRUE_ANS, false);
        }
    }

    //This method is called when Show Ans button is clicked
    //Sends back an intent, that contains the info that ANS was shown or not
    private void cheatInfo(Boolean isAnsShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_CHEAT_INFO, isAnsShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_TRUE_ANS, trueAns);
        Toast.makeText(this, "inside Save instance state of cheatActivity", Toast.LENGTH_SHORT).show();
    }
}
