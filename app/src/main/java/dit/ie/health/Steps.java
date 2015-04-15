package dit.ie.health;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.graphics.Color;

public  class Steps extends Activity implements SensorEventListener {

    private Button text;
    private Button text2;
    private Button button;

    private int value = -1;
    private int data = 0;
    //private long lastUpdate;

    private SensorManager mSensorManager;
    private Sensor mStepDetectorSensor;
    private Sensor mStepCounterSensor;
    private SensorManager sensorManager;

    private boolean color = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.steps_view);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mStepCounterSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        mStepDetectorSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);


        text = (Button) findViewById(R.id.textView4);
        text2 = (Button) findViewById(R.id.textView5);
        button  = (Button) findViewById(R.id.button);

        button.setBackgroundColor(Color.GREEN);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //lastUpdate = System.currentTimeMillis();

        if(value < 0)
        {
            text2.setText("loading...");
        }
    }


    private void getAccelerometer(SensorEvent event) {
        float[] values = event.values;

        float x = values[0];
        float y = values[1];
        float z = values[2];

        float accelationSquareRoot = (x * x + y * y + z * z)
                / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
        //long actualTime = event.timestamp;

        if (accelationSquareRoot >= 2) //
        {
            /*if (actualTime - lastUpdate < 200) {
                return;
            }*/
            //lastUpdate = actualTime;
            data++;
            text.setText("You are taking: " + ( data / 2  )+ " steps");



            if (color) {
                button.setBackgroundColor(Color.GREEN);
            } else {
                button.setBackgroundColor(Color.RED);
            }
            color = !color;
        }
    }


    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        float[] values = event.values;

        if (values.length > 0) {
            value = (int) values[0];
        }


        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }


        if (sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            text2.setText("You have taken:" + value + " steps today");

        }
        else if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            text2.setText(": " + value);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener((android.hardware.SensorEventListener) this, mStepCounterSensor, SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener((android.hardware.SensorEventListener) this, mStepDetectorSensor, SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(this,
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);

    }//end onResume()

    /*protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener( this, mStepCounterSensor);
        mSensorManager.unregisterListener( this, mStepDetectorSensor);
    }//end onStop()*/

    /*@Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
    }*/


    public void onClick(View v) {
        Intent i = new Intent(this, Steps.class);
        startActivity(i);
    }

}