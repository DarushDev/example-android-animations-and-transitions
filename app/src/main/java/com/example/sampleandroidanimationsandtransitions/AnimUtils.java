package com.example.sampleandroidanimationsandtransitions;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Path;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;

import static com.example.sampleandroidanimationsandtransitions.ScreenUtils.getAbsoluteX;
import static com.example.sampleandroidanimationsandtransitions.ScreenUtils.getAbsoluteY;
import static com.example.sampleandroidanimationsandtransitions.ScreenUtils.getScreenHeight;
import static com.example.sampleandroidanimationsandtransitions.ScreenUtils.getScreenWidth;

public class AnimUtils {

    // Using ValueAnimator
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

    // Using ObjectAnimator
    public static void animateVertically(Activity context, View v) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "translationY",
                getScreenHeight(context) - getAbsoluteY(v) - v.getHeight());
        animator.setDuration(1000);
        animator.start();
    }

    //Using AnimatorSet
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

    //Using PropertyValuesHolder class to hold different properties for a single ObjectAnimator
    public static void animateDiagonally2(Activity context, View v) {
        PropertyValuesHolder vHolderX = PropertyValuesHolder.ofFloat("translationX",
                getScreenWidth(context) - getAbsoluteX(v) - v.getWidth());
        PropertyValuesHolder vHolderY = PropertyValuesHolder.ofFloat("translationY",
                getScreenHeight(context) - getAbsoluteY(v) - v.getHeight());
        ObjectAnimator.ofPropertyValuesHolder(v, vHolderX, vHolderY).setDuration(1000).start();
    }

    //Using ViewPropertyAnimator
    public static void animateDiagonally3(Activity context, View v) {
        v.animate().setDuration(1000)
                .translationX(getScreenWidth(context) - getAbsoluteX(v) - v.getWidth())
                .translationY(getScreenHeight(context) - getAbsoluteY(v) - v.getHeight());
    }

    // Using ValueAnimator from XML resoure with tag <animator>
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

    //Using AnimatorSet from XML resource with tag <set>
    public static void blink(Context context, View v) {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(context,
                R.animator.animator_blink);
        set.setTarget(v);
        set.start();
    }

    //Using Keyframe for holding different time intervals that the animation changes
    public static void animateUsingAnimatorSet(Object target) {
        Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
        Keyframe kf1 = Keyframe.ofFloat(.5f, .36f);
        Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
        PropertyValuesHolder valuesHolder = PropertyValuesHolder.ofKeyframe("rotation",
                kf0, kf1, kf2);
        ObjectAnimator rotationAnim = ObjectAnimator.ofPropertyValuesHolder(target, valuesHolder);
        rotationAnim.setDuration(5000);
    }

    //Using AnimationDrawable
    public static void playDrawableAnimation(ImageView image) {
        AnimationDrawable animation = (AnimationDrawable) image.getBackground();
        if (animation.isRunning()) {
            animation.stop();
        } else {
            animation.start();
        }
    }

    public static void crossfade(View fadeInView, final View fadeOutView, int duration) {

        fadeInView.setAlpha(0f);
        fadeInView.setVisibility(View.VISIBLE);
        fadeInView.animate()
                .alpha(1f)
                .setDuration(duration)
                .setListener(null);

        fadeOutView.animate()
                .alpha(0f)
                .setDuration(duration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        fadeOutView.setVisibility(View.GONE);
                    }
                });
    }

    //Using ViewAnimationUtils.createCircularReveal() method
    public static void circularReveal(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // get the center for the clipping circle
            int cx = view.getWidth() / 2;
            int cy = view.getHeight() / 2;

            //get the final radius for the clipping circle
            float finalRadius = (float) Math.hypot(cx, cy);

            Animator animator = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);

            view.setVisibility(View.VISIBLE);
            animator.start();
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }

    //Using ViewAnimationUtils.createCircularReveal() method
    public static void circularHide(final View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int cx = view.getWidth() / 2;
            int cy = view.getHeight() / 2;

            float initialRadius = (float) Math.hypot(cx, cy);

            Animator animator = ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, 0);

            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    view.setVisibility(View.INVISIBLE);
                }
            });

            animator.start();
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }

    public static void followPath(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Path path = new Path();
            path.arcTo(100f, 300f, 1000f, 1000f, 270f, -180f, true);

            ObjectAnimator animator = ObjectAnimator.ofFloat(view, View.X, View.Y, path);
            animator.setDuration(2000);
            animator.start();
        } else {
            // Crate animator without using curved path
        }
    }

    //TODO: Needs to be checked. Gives error.
    public static void interpolatePath(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Path path = new Path();
            path.arcTo(0f, 0f, 1000f, 1000f, 270f, -180f, true);
            PathInterpolator pathInterpolator = new PathInterpolator(path);

            ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", 100f);
            animator.setInterpolator(pathInterpolator);
            animator.start();
        }
    }
}
