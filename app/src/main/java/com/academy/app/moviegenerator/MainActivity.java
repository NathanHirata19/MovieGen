package com.academy.app.moviegenerator;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView answerText;

    MediaPlayer mediaPlayer;

    private SensorManager sensorManager;
    private Sensor accelerometer;

    private double acceleration;
    private double currentAcceleration;
    private double previousAcceleration;

    private final SensorEventListener sensorListener = new SensorEventListener(){
    @Override
        public void onSensorChanged(SensorEvent event){
                double x = event.values[0];
                double y = event.values[1];
                double z = event.values[2];

        previousAcceleration = currentAcceleration;
        currentAcceleration = Math.sqrt(x * x + y * y + z * z);
        double delta = currentAcceleration - previousAcceleration;
        acceleration = acceleration * 0.9f + delta;

        if (acceleration > 15){
                Toast toast = Toast.makeText(getApplication(), "Device is shook", Toast.LENGTH_SHORT);
            toast.show();
            mediaPlayer.start();

        }
    }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy){

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this,R.raw.mgsound);
        mediaPlayer.start();

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        acceleration = 0.0f;
        currentAcceleration = sensorManager.GRAVITY_EARTH;
        previousAcceleration = sensorManager.GRAVITY_EARTH;

        answerText = (TextView) findViewById(R.id.answerText);
        answerText.setText(Generator.get().getGenerator());
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorListener);
    }
}








