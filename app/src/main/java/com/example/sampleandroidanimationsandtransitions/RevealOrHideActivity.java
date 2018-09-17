package com.example.sampleandroidanimationsandtransitions;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
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
                circularReveal();
            }
        });

        findViewById(R.id.button_hide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                circularHide();
            }
        });

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

    private void circularReveal() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // get the center for the clipping circle
            int cx = mTvLorem.getWidth() / 2;
            int cy = mTvLorem.getHeight() / 2;

            //get the final radius for the clipping circle
            float finalRadius = (float) Math.hypot(cx, cy);

            Animator animator = ViewAnimationUtils.createCircularReveal(mTvLorem, cx, cy, 0, finalRadius);

            mTvLorem.setVisibility(View.VISIBLE);
            animator.start();
        } else {
            mTvLorem.setVisibility(View.VISIBLE);
        }
    }

    private void circularHide() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int cx = mTvLorem.getWidth() / 2;
            int cy = mTvLorem.getHeight() / 2;

            float initialRadius = (float) Math.hypot(cx, cy);

            Animator animator = ViewAnimationUtils.createCircularReveal(mTvLorem, cx, cy, initialRadius, 0);

            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mTvLorem.setVisibility(View.INVISIBLE);
                }
            });

            animator.start();
        } else {
            mTvLorem.setVisibility(View.VISIBLE);
        }
    }
}
