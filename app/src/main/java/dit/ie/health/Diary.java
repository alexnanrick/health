package dit.ie.health;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public  class Diary extends Activity implements OnClickListener {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_view);



    }


    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, Diary.class);
        startActivity(i);
    }

}