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
import android.widget.AbsoluteLayout;
import android.widget.EditText;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.widget.Button;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener
{
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    //step sensor
    private TextView textView;
    private SensorManager mSensorManager;
    private Sensor mStepCounterSensor;
    private Sensor mStepDetectorSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupLoginButton();

        // get the list view and text view
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        textView = (TextView) findViewById(R.id.textview);

        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        mStepCounterSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        mStepDetectorSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);


        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
        {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,int groupPosition, int childPosition, long id)
            {
                if(childPosition == 0 && groupPosition == 2)
                {

                }
                else
                {

                }

                //display steps if steps area clicked
                if(childPosition == 0 && groupPosition == 1)
                {
                    textView.setText("Steps taken: " + "  " + " " +   value);
                }
                else
                {
                    textView.setText("");//clear the text box
                }//end if

               /* Toast.makeText(getApplicationContext(), listDataHeader.get(groupPosition) + " : " +
                        listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();*/

                return false;
            }

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

               /* Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) ,
                        Toast.LENGTH_SHORT).show();*/
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
    private void prepareListData()
    {
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
        STEPS.add("STEPS TAKEN" + " " + value);

        List<String> CALCULATE_CALORIES = new ArrayList<String>();
        CALCULATE_CALORIES.add("BMI");

        List<String> WEIGHT = new ArrayList<String>();
        WEIGHT.add("BMI");

        List<String> EXERCISE = new ArrayList<String>();
        EXERCISE.add("Start your daily exercise");

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

    int value = -1;
    @Override
    public void onSensorChanged(SensorEvent event)
    {
        Sensor sensor = event.sensor;
        float[] values = event.values;


        if (values.length > 0)
        {
            value = (int) values[0];
        }

        if (sensor.getType() == Sensor.TYPE_STEP_COUNTER)
        {

            textView.setText("Step Counter Detected : " + value);

        }
        else if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR)
        {
            // For test only. Only allowed value is 1.0 i.e. for step taken
            textView.setText("Step Detector Detected : " + value);
        }//end if

    }//end onSensorChanged()


    protected void onResume()
    {
        super.onResume();
        mSensorManager.registerListener(this, mStepCounterSensor,SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(this, mStepDetectorSensor,SensorManager.SENSOR_DELAY_FASTEST);
    }

    protected void onStop()
    {
        super.onStop();
        mSensorManager.unregisterListener(this, mStepCounterSensor);
        mSensorManager.unregisterListener(this, mStepDetectorSensor);
    }

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
    }
}
