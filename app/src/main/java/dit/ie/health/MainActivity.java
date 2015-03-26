package dit.ie.health;//default package


//all the imports
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
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
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // Listview on child click listener, display after clicking
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
        {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,int groupPosition, int childPosition, long id)
            {
                Toast.makeText(getApplicationContext(), listDataHeader.get(groupPosition) + " : " +
                        listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
                return false;
            }

        });

        // preparing list data
        prepareListData();


        // Listview Group expanded listener, display when expanded
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {


            @Override
            public void onGroupExpand(int groupPosition)
            {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) ,
                        Toast.LENGTH_SHORT).show();

            }


        });



        // Listview Group collasped listener, display when collapsed
       /* expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) ,
                        Toast.LENGTH_SHORT).show();

            }
        });*/

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }



    /*
     * Preparing the list data
     */
    private void prepareListData()
    {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("USER DATA");
        listDataHeader.add("STEPS");
        listDataHeader.add("CALCULATE CALORIES");
        listDataHeader.add("WEIGHT");
        listDataHeader.add("EXERCISE");
        listDataHeader.add("HEALTHY FOODS DATABASE");

        // Adding child data
        List<String> USER_DATA = new ArrayList<String>();
        USER_DATA.add("AGE");
        USER_DATA.add("OCCUPATION");

        List<String> STEPS = new ArrayList<String>();
        STEPS.add("START COUNTING");

        List<String> CALCULATE_CALORIES = new ArrayList<String>();
        CALCULATE_CALORIES.add("RUN CALCULATOR");

        List<String> WEIGHT = new ArrayList<String>();
        WEIGHT.add("ENTER YOUR WEIGHT");

        List<String> EXERCISE = new ArrayList<String>();
        EXERCISE.add("START DOING SOMETHING");

        List<String> HEALTHY_FOODS_DATABASE = new ArrayList<String>();
        HEALTHY_FOODS_DATABASE.add("MEAT");
        HEALTHY_FOODS_DATABASE.add("NO CARBS");

        listDataChild.put(listDataHeader.get(0), USER_DATA); // Header, Child data
        listDataChild.put(listDataHeader.get(1), STEPS);
        listDataChild.put(listDataHeader.get(2), CALCULATE_CALORIES);
        listDataChild.put(listDataHeader.get(3), WEIGHT);
        listDataChild.put(listDataHeader.get(4), EXERCISE);
        listDataChild.put(listDataHeader.get(5), HEALTHY_FOODS_DATABASE);
    }
}