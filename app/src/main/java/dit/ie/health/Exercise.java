package dit.ie.health;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;


public  class Exercise extends Activity implements OnClickListener{

   private TextView cal1,cal2,cal3;
   Food b = new Food();
   public float calories;


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
    }


    @Override
    public void onClick(View v) {
                Intent i = new Intent(this, Exercise.class);
                startActivity(i);
        }//end onClick()

    //display running time based on the calories consumed
    public void onExercise()
    {
        calories = b.getFruits();

        cal1 = (TextView) findViewById(R.id.textView);
        cal2 = (TextView) findViewById(R.id.textView2);
        cal3 = (TextView) findViewById(R.id.textView3);
        String result1 = String.format("%.1f", calories / 398);
        String result2 = String.format("%.1f", calories / 232);
        String result3 = String.format("%.1f", calories /300);

        cal1.setText("run for: " + result1 + " hours");
        cal2.setText("walk for: " + result2 + " hours");
        cal3.setText("cycle for: " + result3 + " hours");
    }//end onExercise()
}