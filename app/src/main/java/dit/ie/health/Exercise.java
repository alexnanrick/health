package dit.ie.health;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public  class Exercise extends Activity implements OnClickListener{

   //pull missing calories from the calorie calculator database here
    //missing code....
   private int calories = 2600;//temp var
   private TextView StepView;
   private Button distance;


    //getters
    public TextView getStepView() {
        return StepView;
    }
    public int getCalories() {
        return calories;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex_view);

        //distance = (Button)findViewById(R.id.newButton);
        //distance.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
                Intent i = new Intent(this, Exercise.class);
                startActivity(i);
        }
    }