package com.example.sampleandroidanimationsandtransitions;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvHello = findViewById(R.id.text_main_hello);

        tvHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateTheText();
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

}
