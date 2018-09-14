package com.example.sampleandroidanimationsandtransitions;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import static com.example.sampleandroidanimationsandtransitions.ScreenUtils.getAbsoluteX;
import static com.example.sampleandroidanimationsandtransitions.ScreenUtils.getAbsoluteY;
import static com.example.sampleandroidanimationsandtransitions.ScreenUtils.getScreenHeight;
import static com.example.sampleandroidanimationsandtransitions.ScreenUtils.getScreenWidth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CardView cardBall;
    Button btnHorizontal;
    Button btnVertical;
    Button btnDiagonal;
    Button btnReset;
    Button btnBounce;
    Button btnBlink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardBall = findViewById(R.id.card_main_ball);
        btnHorizontal = findViewById(R.id.button_main_horizontal);
        btnVertical = findViewById(R.id.button_main_vertical);
        btnDiagonal = findViewById(R.id.button_main_diagonal);
        btnReset = findViewById(R.id.button_main_reset);
        btnBounce = findViewById(R.id.button_main_bounce);
        btnBlink = findViewById(R.id.button_main_blink);

        btnHorizontal.setOnClickListener(this);
        btnVertical.setOnClickListener(this);
        btnDiagonal.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnBounce.setOnClickListener(this);
        btnBlink.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.button_main_reset:
                resetTheView(cardBall);
                break;
            case R.id.button_main_horizontal:
                animateHorizontally(cardBall);
                break;
            case R.id.button_main_vertical:
                animateVertically(cardBall);
                break;
            case R.id.button_main_diagonal:
                //animateDiagonally1(cardBall);
                //animateDiagonally2(cardBall);
                animateDiagonally3(cardBall);
                break;
            case R.id.button_main_bounce:
                bounce(cardBall);
                break;
            case R.id.button_main_blink:
                blink(cardBall);
                break;

        }
    }

    private void resetTheView(View view) {
        view.setTranslationX(btnReset.getTranslationX());
        view.setTranslationY(btnReset.getTranslationY());
    }

    private void animateHorizontally(final View v) {
        ValueAnimator animator = ValueAnimator.ofInt(0,
                getScreenWidth(this) - getAbsoluteX(v) - v.getWidth());
        animator.setDuration(1000);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                int animatedValue = (int) valueAnimator.getAnimatedValue();
                v.setTranslationX(animatedValue);
            }
        });
    }

    private void animateVertically(View v) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "translationY",
                getScreenHeight(this) - getAbsoluteY(v) - v.getHeight());
        animator.setDuration(1000);
        animator.start();
    }

    private void animateDiagonally1(View v) {
        ObjectAnimator animX = ObjectAnimator.ofFloat(v, "translationX",
                getScreenWidth(this) - getAbsoluteX(v) - v.getWidth());
        ObjectAnimator animY = ObjectAnimator.ofFloat(v, "translationY",
                getScreenHeight(this) - getAbsoluteY(v) - v.getHeight());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animX, animY);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }

    private void animateDiagonally2(View v) {
        PropertyValuesHolder vHolderX = PropertyValuesHolder.ofFloat("translationX",
                getScreenWidth(this) - getAbsoluteX(v) - v.getWidth());
        PropertyValuesHolder vHolderY = PropertyValuesHolder.ofFloat("translationY",
                getScreenHeight(this) - getAbsoluteY(v) - v.getHeight());
        ObjectAnimator.ofPropertyValuesHolder(v, vHolderX, vHolderY).setDuration(1000).start();
    }

    private void animateDiagonally3(View v) {
        v.animate().setDuration(1000)
                .translationX(getScreenWidth(this) - getAbsoluteX(v) - v.getWidth())
                .translationY(getScreenHeight(this) - getAbsoluteY(v) - v.getHeight());
    }

    // Declare property animation in XML and use ValueAnimator tag <animator>
    private void bounce(final View v) {
        ValueAnimator xmlAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(this,
                R.animator.animator_bounce);
        xmlAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                v.setTranslationY(animatedValue);
            }
        });
        xmlAnimator.start();
    }

    private void blink(View v) {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this,
                R.animator.animator_blink);
        set.setTarget(v);
        set.start();
    }

    private void animateUsingAnimatorSet(Object target) {
        Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
        Keyframe kf1 = Keyframe.ofFloat(.5f, .36f);
        Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
        PropertyValuesHolder valuesHolder = PropertyValuesHolder.ofKeyframe("rotation",
                kf0, kf1, kf2);
        ObjectAnimator rotationAnim = ObjectAnimator.ofPropertyValuesHolder(target, valuesHolder);
        rotationAnim.setDuration(5000);
    }

}
