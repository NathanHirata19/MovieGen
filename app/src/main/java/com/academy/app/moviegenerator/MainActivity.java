package com.academy.app.moviegenerator;

import android.support.v7.app.AppCompatActivity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.util.FloatMath;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView answerText;

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private float acceleration;
    private float currentAcceleration;
    private float previousAcceleration;

    private final SensorEventListener sensorListener = new SensorEventListener(){
    @Override
        public void OnSensorChanged(SensorEvent event){
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

        previousAcceleration = currentAcceleration;
        currentAcceleration = FloatMath.sqrt(x*x+y*y+z*z);
        float delta = currentAcceleration - previousAcceleration;
        acceleration = acceleration * 0.9f + delta;

        if (acceleration > 15){
                Toast toast = Toast.makeText(getApplication(), "Device is shook", Toast.LENGTH_SHORT);
            Toast.show();

        }
    }
        @Override
        public void OnAccuracyChanged(Sensor sensor, int accuracy){


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager)getSystemService(Context.SYSTEM_SERVICE);
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
    }
}








