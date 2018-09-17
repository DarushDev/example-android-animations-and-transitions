package com.example.sampleandroidanimationsandtransitions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class PropertyAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    CardView cardBall;
    Button btnHorizontal;
    Button btnVertical;
    Button btnDiagonal;
    Button btnReset;
    Button btnBounce;
    Button btnBlink;
    Button btnFollowPath;
    Button btnFling;
    ImageView ivHeart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);

        cardBall = findViewById(R.id.card_main_ball);
        btnHorizontal = findViewById(R.id.button_main_horizontal);
        btnVertical = findViewById(R.id.button_main_vertical);
        btnDiagonal = findViewById(R.id.button_main_diagonal);
        btnReset = findViewById(R.id.button_main_reset);
        btnBounce = findViewById(R.id.button_main_bounce);
        btnBlink = findViewById(R.id.button_main_blink);
        btnFollowPath = findViewById(R.id.button_main_followpath);
        btnFling = findViewById(R.id.button_main_fling);
        ivHeart = findViewById(R.id.image_main_heart);

        btnHorizontal.setOnClickListener(this);
        btnVertical.setOnClickListener(this);
        btnDiagonal.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnBounce.setOnClickListener(this);
        btnBlink.setOnClickListener(this);
        btnFollowPath.setOnClickListener(this);
        btnFling.setOnClickListener(this);
        ivHeart.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.button_main_reset:
                resetTheView(cardBall);
                break;
            case R.id.button_main_horizontal:
                AnimUtils.animateHorizontally(this, cardBall);
                break;
            case R.id.button_main_vertical:
                AnimUtils.animateVertically(this, cardBall);
                break;
            case R.id.button_main_diagonal:
                //AnimUtils.animateDiagonally1(this, cardBall);
                //AnimUtils.animateDiagonally2(this, cardBall);
                AnimUtils.animateDiagonally3(this, cardBall);
                break;
            case R.id.button_main_bounce:
                AnimUtils.bounce(this, cardBall);
                break;
            case R.id.button_main_blink:
                AnimUtils.blink(this, cardBall);
                break;
            case R.id.image_main_heart:
                AnimUtils.playDrawableAnimation(ivHeart);
                break;
            case R.id.button_main_followpath:
                AnimUtils.followPath(cardBall);
                break;
            case R.id.button_main_fling:
                AnimUtils.flingAnimation(cardBall);
                break;

        }
    }

    private void resetTheView(View view) {
        view.setTranslationX(btnReset.getTranslationX());
        view.setTranslationY(btnReset.getTranslationY());
    }

}
