package dit.ie.health;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public  class Food extends Activity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_view);
    }


    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, Food.class);
        startActivity(i);
    }
}