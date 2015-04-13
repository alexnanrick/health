package dit.ie.health;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;


public class SpinnerActivity extends Activity implements OnItemSelectedListener {

    private float burn = 260;

    public float getBurn()
    {
        return burn;
    }



    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

        parent.getItemAtPosition(1);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        if(parent.getItemAtPosition(pos) == 0)
        {
            burn = 232;
        }
        else if(parent.getItemAtPosition(pos) == 1)
        {
            burn = 300;
        }

    }

    public void onNothingSelected(AdapterView<?> parent) {

        burn = 100;

    }
}