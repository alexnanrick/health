package dit.ie.health;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.NumberPicker;


public class Calculator extends Activity implements OnClickListener, NumberPicker.OnValueChangeListener {

    private static TextView tv;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc_view);

        tv = (TextView) findViewById(R.id.textView1);
        Button b = (Button) findViewById(R.id.button11);
        b.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v) {
                show();
            }
        });
    }

    public void show()
    {
        final Dialog d = new Dialog(Calculator.this);
        d.setTitle("Your Calories");

        d.setContentView(R.layout.dialog);
        Button b1 = (Button) d.findViewById(R.id.button1);

        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);

        np.setMaxValue(5000);
        np.setMinValue(100);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        b1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v) {
                tv.setText(String.valueOf(np.getValue()));
                d.dismiss();
            }
        });
        d.show();
    }

    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        Log.i("Your Calories: ", "" + newVal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, Calculator.class);
        startActivity(i);
    }
}