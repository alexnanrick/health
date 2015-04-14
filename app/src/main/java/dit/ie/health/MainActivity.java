package dit.ie.health;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import static android.os.SystemClock.elapsedRealtime;


public class MainActivity extends Activity implements SensorEventListener {


    //step sensor
    private int value = -1;
    private Button stepButton;
    private SensorManager mSensorManager;
    private Sensor mStepDetectorSensor;
    private Sensor mStepCounterSensor;

    //calculator
    EditText operand1;
    EditText operand2;
    Button btnPlus;
    Button btnSubtract;
    Button btnDivide;
    Button btnMultiply;
    Button btnClear;
    TextView Result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //prepare and display user login, exercise and steps buttons
        setupLoginButton();
        setupExerciseButton();
        setupCalculatorButton();
        setupFoodButton();
        setupCaloriesButton();
        setupStepsButton();
        setupDiaryButton();




        stepButton = (Button) findViewById(R.id.stepButton);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mStepCounterSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        mStepDetectorSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

        //calculator
        operand1 = (EditText) findViewById(R.id.editOperand1);
        operand2 = (EditText) findViewById(R.id.editOperand2);

        //associate buttons;
        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnSubtract = (Button) findViewById(R.id.btnSubtract);
        btnDivide = (Button) findViewById(R.id.btnDivide);
        btnMultiply = (Button) findViewById(R.id.btnMultiply);
        btnClear = (Button) findViewById(R.id.btnClr);
        //end buttons

        //display calculator result
        Result = (TextView) findViewById(R.id.textResult);

        //display steps taken
        if(value < 0)
        {
            stepButton.setText("Steps: ...loading");
        }
    }


    //return time since boot in hours
    long time = (((elapsedRealtime() / 1000) / 60)/60);

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        float[] values = event.values;

        if (values.length > 0) {
            value = (int) values[0];
        }

        //reset step counter after 24 hour period
        if (time > 24 )
        {
            value = 0;
            for(int i=0;i<values.length;i++)
            {
                values[i] = 0;
            }

            time = 0;
        }


        if (sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
             stepButton.setText("Counting Steps: " + value);
        }
        else if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            // For test only. Only allowed value is 1.0 i.e. for step taken
            stepButton.setText("Counting Steps: " + value);
        }
        else
        {
            stepButton.setText(value);
        }


    }



    //get number of steps
    public int getValue()
    {
        return value;
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mStepCounterSensor, SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(this, mStepDetectorSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }//end onResume()

    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this, mStepCounterSensor);
        mSensorManager.unregisterListener(this, mStepDetectorSensor);
    }//end onStop()

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }


    public void setupLoginButton() {
        // 1. Reference button
        Button loginButton = (Button) findViewById(R.id.loginButtonMain);
        // 2. Set the click listener
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });
    }//end setupLoginButton()

    public void setupExerciseButton() {

        Button cButton = (Button) findViewById(R.id.textview2);
        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Exercise.class));
            }
        });
    }//end setupExerciseButton()

    public void setupCalculatorButton() {

        Button cButton = (Button) findViewById(R.id.exButton);
        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Calculator.class));
            }
        });
    }//end setupCalculatorButton()

    public void setupFoodButton() {

        Button cButton = (Button) findViewById(R.id.foodButton);
        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Food.class));
            }
        });
    }//end setupFoodButton()

    public void setupCaloriesButton() {

        Button cButton = (Button) findViewById(R.id.calButton);
        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Calories.class));
            }
        });
    }//end setupCaloriesButton()

    public void setupStepsButton() {

        Button cButton = (Button) findViewById(R.id.stepButton);
        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Steps.class));
            }
        });
    }

    public void setupDiaryButton() {

        Button cButton = (Button) findViewById(R.id.diaryButton);
        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Diary.class));
            }
        });
    }
}