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
    EditText operand2;
    Button btnPlus;
    Button btnSubtract;
    Button btnDivide;
    Button btnMultiply;
    Button btnClear;
    TextView Result;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc_view);


        //operation fields from the main screen
        operand1 = (EditText) findViewById(R.id.editOperand1);
        operand2 = (EditText) findViewById(R.id.editOperand2);

        //associate buttons;
        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnSubtract = (Button) findViewById(R.id.btnSubtract);
        btnDivide = (Button) findViewById(R.id.btnDivide);
        btnMultiply = (Button) findViewById(R.id.btnMultiply);
        btnClear = (Button) findViewById(R.id.btnClr);

        //associate result textfield
        Result = (TextView) findViewById(R.id.textResult);

        //add functionality
        btnPlus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                float oper1 = Float.parseFloat(operand1.getText().toString());
                float oper2 = Float.parseFloat(operand2.getText().toString());
                float theResult= oper1 + oper2;
                Result.setText(Float.toString(theResult));
            }
        });


        btnSubtract.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                float oper1 = Float.parseFloat(operand1.getText().toString());
                float oper2 = Float.parseFloat(operand2.getText().toString());
                float theResult= oper1 - oper2;
                Result.setText(Float.toString(theResult));
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                float oper1 = Float.parseFloat(operand1.getText().toString());
                float oper2 = Float.parseFloat(operand2.getText().toString());
                float theResult= oper1 / oper2;
                Result.setText(Float.toString(theResult));
            }
        });


        btnMultiply.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                float oper1 = Float.parseFloat(operand1.getText().toString());
                float oper2 = Float.parseFloat(operand2.getText().toString());
                float theResult= oper1 * oper2;
                Result.setText(Float.toString(theResult));
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                operand1.setText("");
                operand2.setText("");
                Result.setText("0.00");
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