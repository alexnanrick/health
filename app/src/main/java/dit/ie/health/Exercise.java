package dit.ie.health;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


public  class Exercise extends Activity implements OnClickListener{

   //pull missing calories from the calorie calculator database here
    //missing code....
   private float calories = 2600;//temp var
   private TextView StepView;
   private Button distance;
   private TextView cal1,cal2,cal3;
   private float calcRun;


    //getters
    public TextView getStepView() {
        return StepView;
    }

    //calculate exercise based on calories
    public float getCalories() {
        return calories;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex_view);
        onExercise();

        //distance = (Button)findViewById(R.id.newButton);
        //distance.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
                Intent i = new Intent(this, Exercise.class);
                startActivity(i);
        }

    //display running time based on the calories consumed
    public void onExercise()
    {
        calcRun = calories / 398;
        cal1 = (TextView) findViewById(R.id.textView);
        cal2 = (TextView) findViewById(R.id.textView2);
        cal3 = (TextView) findViewById(R.id.textView3);
        String result = String.format("%.1f", calcRun);
        cal1.setText("run for: " + result + " hours");
        cal2.setText("walk for: " + result + " hours");
        cal3.setText("run for: " + result + " hours");

    }

}