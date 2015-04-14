package dit.ie.health;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    private Button stepButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //prepare and display user login, exercise and steps buttons
        setupMenu();


        //display steps menu
        stepButton = (Button) findViewById(R.id.stepButton);
        stepButton.setText("Steps");
    }

    public void setupMenu() {
        // 1. Reference button
        Button loginButton = (Button) findViewById(R.id.loginButtonMain);
        // 2. Set the click listener
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });

        Button cButton = (Button) findViewById(R.id.textview2);
        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Exercise.class));
            }
        });
        Button eButton = (Button) findViewById(R.id.textview2);
        eButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Exercise.class));
            }
        });
        Button calButton = (Button) findViewById(R.id.exButton);
        calButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Calculator.class));
            }
        });
        Button fButton = (Button) findViewById(R.id.foodButton);
        fButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Food.class));
            }
        });
        Button clButton = (Button) findViewById(R.id.calButton);
        clButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Calories.class));
            }
        });
        Button sButton = (Button) findViewById(R.id.stepButton);
        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Steps.class));
            }
        });
        Button dButton = (Button) findViewById(R.id.diaryButton);
        dButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Diary.class));
            }
        });
    }//end setupMenu()

}