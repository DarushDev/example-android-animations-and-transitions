package com.example.sampleandroidanimationsandtransitions;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class DrawableGraphicsActivity extends AppCompatActivity {

    Button btnRotate;
    ImageView ivArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable_graphics);

        btnRotate = findViewById(R.id.button_main_rotate);
        ivArrow = findViewById(R.id.image_main_arrow);

        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotate(ivArrow);
            }
        });

    }

    private void rotate(ImageView image) {

        Drawable drawable = image.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }

    }

}
