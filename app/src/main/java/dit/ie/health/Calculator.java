package dit.ie.health;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class Calculator extends Activity implements OnClickListener{

    EditText operand1;
    Button btnSubmit;
    Button btnClear;
    TextView Result;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc_view);


        //operation fields from the main screen
        operand1 = (EditText) findViewById(R.id.editOperand1);
        //associate buttons;
        btnClear = (Button) findViewById(R.id.btnClr);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        //associate result textfield
        Result = (TextView) findViewById(R.id.textResult);

        //add functionality
        btnSubmit.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                try {
                    float oper1 = Float.parseFloat(operand1.getText().toString());
                    Result.setText(Float.toString(oper1));
                }catch(Exception e){}
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                operand1.setText("");
                Result.setText("0");
            }
        });
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