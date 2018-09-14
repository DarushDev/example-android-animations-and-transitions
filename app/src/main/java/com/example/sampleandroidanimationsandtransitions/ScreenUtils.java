package com.example.sampleandroidanimationsandtransitions;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;

public class ScreenUtils {

    public static int getScreenWidth(Activity context) {
        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    public static int getScreenHeight(Activity context) {
        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    public static int getAbsoluteX(View v) {
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        return location[0];
    }

    public static int getAbsoluteY(View v) {
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        return location[1];
    }

}
