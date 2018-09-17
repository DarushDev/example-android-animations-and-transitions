package com.example.sampleandroidanimationsandtransitions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class RevealOrHideActivity extends AppCompatActivity {

    private View mContentView;
    private View mLoadingView;
    private int mAnimationDuration = 1500;
    private TextView mTvLorem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal_or_hide);

        mContentView = findViewById(R.id.content);
        mLoadingView = findViewById(R.id.loading_spinner);
        mTvLorem = findViewById(R.id.text_lorem);

        findViewById(R.id.button_reveal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimUtils.circularReveal(mTvLorem);
            }
        });

        findViewById(R.id.button_hide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimUtils.circularHide(mTvLorem);
            }
        });

        //mAnimationDuration = getResources().getInteger(android.R.integer.config_longAnimTime);
    }

    @Override
    protected void onStart() {
        super.onStart();
        AnimUtils.crossfade(mContentView, mLoadingView, mAnimationDuration);
    }




}
