package dit.ie.health;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


public  class Steps extends Activity implements OnClickListener {

    private TextView text;
    MainActivity val = new MainActivity();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.steps_view);

        text = (TextView) findViewById(R.id.textView4);

        text.setText("You have taken:" + (val.getValue() + 1) + " " + "steps " + "today");


    }


    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, Steps.class);
        startActivity(i);
    }

}