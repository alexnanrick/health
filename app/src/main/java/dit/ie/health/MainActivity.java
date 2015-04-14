package dit.ie.health;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;


public class MainActivity extends Activity {


    //calculator
    EditText operand1;
    EditText operand2;
    Button btnPlus;
    Button btnSubtract;
    Button btnDivide;
    Button btnMultiply;
    Button btnClear;
    TextView Result;
    Button stepButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //prepare and display user login, exercise and steps buttons
        setupLoginButton();
        setupExerciseButton();
        setupCalculatorButton();
        setupFoodButton();
        setupCaloriesButton();
        setupStepsButton();
        setupDiaryButton();

        //display steps menu
        stepButton = (Button) findViewById(R.id.stepButton);
        stepButton.setText("Steps");



        //calculator
        operand1 = (EditText) findViewById(R.id.editOperand1);
        operand2 = (EditText) findViewById(R.id.editOperand2);

        //associate buttons;
        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnSubtract = (Button) findViewById(R.id.btnSubtract);
        btnDivide = (Button) findViewById(R.id.btnDivide);
        btnMultiply = (Button) findViewById(R.id.btnMultiply);
        btnClear = (Button) findViewById(R.id.btnClr);
        //end buttons

        //display calculator result
        Result = (TextView) findViewById(R.id.textResult);
    }

    public void setupLoginButton() {
        // 1. Reference button
        Button loginButton = (Button) findViewById(R.id.loginButtonMain);
        // 2. Set the click listener
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });
    }//end setupLoginButton()

    public void setupExerciseButton() {

        Button cButton = (Button) findViewById(R.id.textview2);
        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Exercise.class));
            }
        });
    }//end setupExerciseButton()

    public void setupCalculatorButton() {

        Button cButton = (Button) findViewById(R.id.exButton);
        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Calculator.class));
            }
        });
    }//end setupCalculatorButton()

    public void setupFoodButton() {

        Button cButton = (Button) findViewById(R.id.foodButton);
        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Food.class));
            }
        });
    }//end setupFoodButton()

    public void setupCaloriesButton() {

        Button cButton = (Button) findViewById(R.id.calButton);
        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Calories.class));
            }
        });
    }//end setupCaloriesButton()

    public void setupStepsButton() {

        Button cButton = (Button) findViewById(R.id.stepButton);
        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Steps.class));
            }
        });
    }

    public void setupDiaryButton() {

        Button cButton = (Button) findViewById(R.id.diaryButton);
        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Diary.class));
            }
        });
    }
}