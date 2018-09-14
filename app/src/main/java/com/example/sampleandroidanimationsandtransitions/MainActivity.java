package com.example.sampleandroidanimationsandtransitions;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvHello;
    TextView tvTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvHello = findViewById(R.id.text_main_hello);
        tvTwo = findViewById(R.id.text_main_text2);

        tvHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateTheText();
            }
        });

        tvTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateUsingObjectAnimator();
            }
        });


    }

    private void animateTheText() {
        ValueAnimator animator = ValueAnimator.ofInt(0, getScreenWith() - tvHello.getWidth());
        animator.setDuration(1000);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                int animatedValue = (int) valueAnimator.getAnimatedValue();
                tvHello.setTranslationX(animatedValue);
            }
        });
    }

    private void animateUsingObjectAnimator() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(tvTwo, "translationY",
                getScreenHeight() - getAbsoluteY(tvTwo) - tvTwo.getHeight());
        animator.setDuration(1000);
        animator.start();
    }

    private int getScreenWith() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    private int getScreenHeight() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    private int getAbsoluteX(View v) {
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        return location[0];
    }

    private int getAbsoluteY(View v) {
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        return location[1];
    }

}
