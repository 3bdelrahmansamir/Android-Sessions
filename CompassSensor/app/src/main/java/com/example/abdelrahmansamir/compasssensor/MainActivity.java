package com.example.abdelrahmansamir.compasssensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private ImageView compassImage;

    private float currentDegree = 0f;

    private SensorManager sensorManager;

    TextView tvCompassDegree;

    boolean checkCompassAvailable;

    SensorEventListener sensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        compassImage = (ImageView) findViewById(R.id.imageViewCompass);

        tvCompassDegree = (TextView) findViewById(R.id.tvHeading);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // for the system's orientation sensor registered listeners

        sensorEventListener = new SensorEventListener() {

            @Override
            public void onSensorChanged(SensorEvent event) {


                if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) {
                    return;
                }


                // get the angle around the z-axis rotated

                float degree = Math.round(event.values[0]);

                tvCompassDegree.setText("Heading: " + Float.toString(degree) + " degrees");

                // create a rotation animation (reverse turn degree degrees)

                RotateAnimation ra = new RotateAnimation(
                        currentDegree,
                        -degree,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF,
                        0.5f);

                // how long the animation will take place
                ra.setDuration(210);

                // set the animation after the end of the reservation status
                ra.setFillAfter(true);

                // Start the animation
                compassImage.startAnimation(ra);
                currentDegree = -degree;

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }

        };


        checkCompassAvailable = sensorManager.registerListener(sensorEventListener

                , sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);

        if (!checkCompassAvailable) {
            tvCompassDegree.setText("No Compass Found");
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        // to stop the listener and save battery

        if (sensorEventListener != null) {
            sensorManager.unregisterListener(sensorEventListener);
            sensorEventListener = null;
        }
    }


}