package com.example.sampleandroidanimationsandtransitions;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RevealOrHideActivity extends AppCompatActivity {

    private View mContentView;
    private View mLoadingView;
    private int mAnimationDuration = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal_or_hide);

        mContentView = findViewById(R.id.content);
        mLoadingView = findViewById(R.id.loading_spinner);

        //mAnimationDuration = getResources().getInteger(android.R.integer.config_longAnimTime);
    }

    @Override
    protected void onStart() {
        super.onStart();
        crossfade();
    }

    private void crossfade() {

        mContentView.setAlpha(0f);
        mContentView.setVisibility(View.VISIBLE);
        mContentView.animate()
                .alpha(1f)
                .setDuration(mAnimationDuration)
                .setListener(null);

        mLoadingView.animate()
                .alpha(0f)
                .setDuration(mAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mLoadingView.setVisibility(View.GONE);
                    }
                });
    }
}
