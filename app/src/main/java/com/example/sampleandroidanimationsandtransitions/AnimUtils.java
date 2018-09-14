package com.example.sampleandroidanimationsandtransitions;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.view.View;

import static com.example.sampleandroidanimationsandtransitions.ScreenUtils.getAbsoluteX;
import static com.example.sampleandroidanimationsandtransitions.ScreenUtils.getAbsoluteY;
import static com.example.sampleandroidanimationsandtransitions.ScreenUtils.getScreenHeight;
import static com.example.sampleandroidanimationsandtransitions.ScreenUtils.getScreenWidth;

public class AnimUtils {

    public static void animateHorizontally(Activity context, final View v) {
        ValueAnimator animator = ValueAnimator.ofInt(0,
                getScreenWidth(context) - getAbsoluteX(v) - v.getWidth());
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

    public static void animateVertically(Activity context, View v) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "translationY",
                getScreenHeight(context) - getAbsoluteY(v) - v.getHeight());
        animator.setDuration(1000);
        animator.start();
    }

    public static void animateDiagonally1(Activity context, View v) {
        ObjectAnimator animX = ObjectAnimator.ofFloat(v, "translationX",
                getScreenWidth(context) - getAbsoluteX(v) - v.getWidth());
        ObjectAnimator animY = ObjectAnimator.ofFloat(v, "translationY",
                getScreenHeight(context) - getAbsoluteY(v) - v.getHeight());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animX, animY);
        animatorSet.setDuration(1000);
        animatorSet.start();
    }

    public static void animateDiagonally2(Activity context, View v) {
        PropertyValuesHolder vHolderX = PropertyValuesHolder.ofFloat("translationX",
                getScreenWidth(context) - getAbsoluteX(v) - v.getWidth());
        PropertyValuesHolder vHolderY = PropertyValuesHolder.ofFloat("translationY",
                getScreenHeight(context) - getAbsoluteY(v) - v.getHeight());
        ObjectAnimator.ofPropertyValuesHolder(v, vHolderX, vHolderY).setDuration(1000).start();
    }

    public static void animateDiagonally3(Activity context, View v) {
        v.animate().setDuration(1000)
                .translationX(getScreenWidth(context) - getAbsoluteX(v) - v.getWidth())
                .translationY(getScreenHeight(context) - getAbsoluteY(v) - v.getHeight());
    }

    // Declare property animation in XML and use ValueAnimator tag <animator>
    public static void bounce(Context context, final View v) {
        ValueAnimator xmlAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(context,
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

    public static void blink(Context context, View v) {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(context,
                R.animator.animator_blink);
        set.setTarget(v);
        set.start();
    }

    public static void animateUsingAnimatorSet(Object target) {
        Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
        Keyframe kf1 = Keyframe.ofFloat(.5f, .36f);
        Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
        PropertyValuesHolder valuesHolder = PropertyValuesHolder.ofKeyframe("rotation",
                kf0, kf1, kf2);
        ObjectAnimator rotationAnim = ObjectAnimator.ofPropertyValuesHolder(target, valuesHolder);
        rotationAnim.setDuration(5000);
    }

}
