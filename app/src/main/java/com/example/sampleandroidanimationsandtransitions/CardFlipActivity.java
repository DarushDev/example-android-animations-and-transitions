package com.example.sampleandroidanimationsandtransitions;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CardFlipActivity extends AppCompatActivity {

    private boolean mIsShowingBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_flip);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame_cardflip_container, new CardFrontFragment())
                    .commit();
        }

        findViewById(R.id.frame_cardflip_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard();
            }
        });
    }

    private void flipCard() {
        if (mIsShowingBack) {
            getSupportFragmentManager().popBackStack();
            mIsShowingBack = false;
            return;
        }

        mIsShowingBack = true;

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.animator_card_flip_right_in,
                        R.animator.animator_card_flip_right_out,
                        R.animator.animator_card_flip_left_in,
                        R.animator.animator_card_flip_left_out)
                .replace(R.id.frame_cardflip_container, new CardBackFragment())
                .addToBackStack(null)
                .commit();
    }

    public static class CardFrontFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_card_front, container, false);
        }
    }

    public static class CardBackFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_card_back, container, false);
        }
    }

}
