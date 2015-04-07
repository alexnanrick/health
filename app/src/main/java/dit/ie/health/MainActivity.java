package dit.ie.health;//default package
//all the imports
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.widget.Button;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements SensorEventListener {

    //expandable list
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    //get exercise and calories values
    Exercise ex = new Exercise();
    TextView StepView = ex.getStepView();
    int calories = ex.getCalories();

    //step sensor
    private int value = -1;
    private TextView textView;
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
    Button setCal;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // login button
        setupLoginButton();


        textView = (TextView) findViewById(R.id.textview);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mStepCounterSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        mStepDetectorSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);


        //calculator
        operand1= (EditText) findViewById(R.id.editOperand1);
        operand2= (EditText) findViewById(R.id.editOperand2);

        //associate buttons;
        btnPlus=(Button) findViewById(R.id.btnPlus);
        btnSubtract=(Button) findViewById(R.id.btnSubtract);
        btnDivide=(Button) findViewById(R.id.btnDivide);
        btnMultiply=(Button) findViewById(R.id.btnMultiply);
        btnClear=(Button) findViewById(R.id.btnClr);
        //end buttons


        //display calculator result
        Result = (TextView) findViewById(R.id.textResult);


        // get the list view and text view
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener(){
           @Override
            public boolean onChildClick(ExpandableListView parent, View v,int groupPosition, int childPosition, long id){
                //display steps if steps area clicked
                if(childPosition == 0 && groupPosition == 0){
                    textView.setText("Steps taken: " + "  " + " " + value);
                }else{
                    textView.setText("");//clear the text box
                }//end if

               if(childPosition == 0 && groupPosition == 1){
                   setContentView(R.layout.calc_view);
               }
               /* Toast.makeText(getApplicationContext(), listDataHeader.get(groupPosition) + " : " +
                        listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();*/
                return false;}
        });

        // preparing list data
        prepareListData();
        // Listview Group expanded listener,
        // What to display when expanded
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener()
        {

            //displays what has been clicked
            @Override
            public void onGroupExpand(int groupPosition)
            {

            }


        });
        // Listview Group collasped listener, display when collapsed
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                /*Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) ,
                        Toast.LENGTH_SHORT).show();*/
            }
        });
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    //fill list with values
    private void prepareListData(){

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();


        // Adding child data
        listDataHeader.add("Steps");
        listDataHeader.add("Calorie Calculator");
        listDataHeader.add("Check Your BMI");
        listDataHeader.add("Exercise");
        listDataHeader.add("Food Database");

        // Adding child data
        List<String> STEPS = new ArrayList<String>();
        if(value < 0){
            STEPS.add("No steps detected yet");
        }else{
            STEPS.add("STEPS TAKEN" + " " + value);
        }

        List<String> CALCULATE_CALORIES = new ArrayList<String>();
        CALCULATE_CALORIES.add("Calculate:");


        List<String> WEIGHT = new ArrayList<String>();
        WEIGHT.add("BMI");

        List<String> EXERCISE = new ArrayList<String>();
        if(calories < 2500) {
            EXERCISE.add("Calories: " + " " + calories);
        }
        else {
            EXERCISE.add("You had over 2500 calories");
        }

        List<String> HEALTHY_FOODS_DATABASE = new ArrayList<String>();
        HEALTHY_FOODS_DATABASE.add("Item 1");
        HEALTHY_FOODS_DATABASE.add("Item 2");
        HEALTHY_FOODS_DATABASE.add("Item 3");

         // Header, Child data
        listDataChild.put(listDataHeader.get(0), STEPS);
        listDataChild.put(listDataHeader.get(1), CALCULATE_CALORIES);
        listDataChild.put(listDataHeader.get(2), WEIGHT);
        listDataChild.put(listDataHeader.get(3), EXERCISE);
        listDataChild.put(listDataHeader.get(4), HEALTHY_FOODS_DATABASE);
    }//end prepareListData()


    @Override
    public void onSensorChanged(SensorEvent event){

        Sensor sensor = event.sensor;
        float[] values = event.values;

        if (values.length > 0){
            value = (int) values[0];
        }
        if (sensor.getType() == Sensor.TYPE_STEP_COUNTER){
            textView.setText("Step Counter Detected : " + value);
        }else if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR){
            // For test only. Only allowed value is 1.0 i.e. for step taken
            textView.setText("Step Detector Detected : " + value);
        }//end if
    }//end onSensorChanged()

    protected void onResume(){
        super.onResume();
        mSensorManager.registerListener(this, mStepCounterSensor,SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(this, mStepDetectorSensor,SensorManager.SENSOR_DELAY_FASTEST);
    }

    protected void onStop(){
        super.onStop();
        mSensorManager.unregisterListener(this, mStepCounterSensor);
        mSensorManager.unregisterListener(this, mStepDetectorSensor);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }


    public void setupLoginButton(){
        // 1. Reference button
        Button loginButton = (Button) findViewById(R.id.loginButtonMain);
        // 2. Set the click listener
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });
    }
}