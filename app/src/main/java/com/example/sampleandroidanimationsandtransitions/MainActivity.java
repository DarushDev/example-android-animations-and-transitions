package com.example.sampleandroidanimationsandtransitions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnPropertyAnimationOverview;
    Button btnDrawableGraphics;
    Button btnRevealOrHide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnPropertyAnimationOverview = findViewById(R.id.button_main_propertyanimation);
        btnDrawableGraphics = findViewById(R.id.button_main_drawablegraphics);
        btnRevealOrHide = findViewById(R.id.button_main_revealorhide);

        btnPropertyAnimationOverview.setOnClickListener(this);
        btnDrawableGraphics.setOnClickListener(this);
        btnRevealOrHide.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_main_propertyanimation:
                startActivity(new Intent(MainActivity.this, PropertyAnimationActivity.class));
                break;
            case R.id.button_main_drawablegraphics:

                break;
            case R.id.button_main_revealorhide:

                break;
        }
    }
}
