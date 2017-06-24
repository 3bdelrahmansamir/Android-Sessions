package com.example.abdelrahmansamir.abdelrahmanpixel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        Log.e("ex", width + "");
        int height = dm.heightPixels;
        Log.e("ex", height + "");
        double wi = (double) width / (double) dm.xdpi;
        Log.e("ex", wi + "");
        double hi = (double) height / (double) dm.ydpi;
        Log.e("ex", hi + "");
        double x = Math.pow(wi, 2);
        Log.e("ex", x + "");
        double y = Math.pow(hi, 2);
        Log.e("ex", y + "");
        double screenInches = Math.sqrt(x + y);
        Log.e("ex", screenInches + "");
    }
}
