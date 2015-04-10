package dit.ie.health;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public  class Food extends Activity implements View.OnClickListener {

    SQLiteDatabase foodDB = null;

    Button createDBButton, addFoodButton, deleteFoodButton, getFoodButton,
            deleteDBButton;
    EditText nameEditText, caloriesEditText, foodListEditText, idEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_view);

        // link buttons to xml buttons
        createDBButton = (Button) findViewById(R.id.createDBButton);
        addFoodButton = (Button) findViewById(R.id.addFoodButton);
        deleteFoodButton = (Button) findViewById(R.id.deleteFoodButton);
        getFoodButton = (Button) findViewById(R.id.getFoodButton);
        deleteDBButton = (Button) findViewById(R.id.deleteDBButton);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        caloriesEditText = (EditText) findViewById(R.id.caloriesEditText);
        foodListEditText = (EditText) findViewById(R.id.foodListEditText);
        idEditText = (EditText) findViewById(R.id.idEditText);

    }

    public void createDatabase(View view) {

        try{

            // Opens a current database or creates it
            // Pass the database name, designate that only this app can use it
            // and a DatabaseErrorHandler in the case of database corruption
            foodDB = this.openOrCreateDatabase("MyFood", MODE_PRIVATE, null);

            // Execute an SQL statement that isn't select
            foodDB.execSQL("CREATE TABLE IF NOT EXISTS food " +
                    "(id integer primary key, name VARCHAR, calories INT);");

            // The database on the file system
            File database = getApplicationContext().getDatabasePath("MyFood.db");

            // Check if the database exists
            if (database.exists()) {
                Toast.makeText(this, "Database Created", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Database Missing", Toast.LENGTH_SHORT).show();
            }

        }

        catch(Exception e){

            Log.e("FOOD ERROR", "Error Creating Database");

        }

        // Make buttons clickable since the database was created (initialised as false)
        addFoodButton.setClickable(true);
        deleteFoodButton.setClickable(true);
        getFoodButton.setClickable(true);
        deleteDBButton.setClickable(true);

    }

    public void addFood(View view) {

        // Get the food name and calories entered
        String foodName = nameEditText.getText().toString();
        String foodCalories = caloriesEditText.getText().toString();

        // Execute SQL statement to insert new data
        foodDB.execSQL("INSERT INTO food (name, calories) VALUES ('" +
                foodName + "', '" + foodCalories + "');");

    }

    public void getFood(View view) {

        // A Cursor provides read and write access to database results
        Cursor cursor = foodDB.rawQuery("SELECT * FROM food", null);

        // Get the index for the column name provided
        int idColumn = cursor.getColumnIndex("id");
        int nameColumn = cursor.getColumnIndex("name");
        int caloriesColumn = cursor.getColumnIndex("calories");

        // Move to the first row of results
        cursor.moveToFirst();

        String foodList = "";

        // Verify that we have results
        if(cursor != null && (cursor.getCount() > 0)){

            do{
                // Get the results and store them in a String
                String id = cursor.getString(idColumn);
                String name = cursor.getString(nameColumn);
                String calories = cursor.getString(caloriesColumn);

                foodList = foodList + id + " : " + name + " : " + calories + "\n";

                // Keep getting results as long as they exist
            }while(cursor.moveToNext());

            foodListEditText.setText(foodList);

        } else {

            Toast.makeText(this, "No Results to Show", Toast.LENGTH_SHORT).show();
            foodListEditText.setText("");

        }

    }

    public void deleteFood(View view) {

        // Get the id to delete
        String id = idEditText.getText().toString();

        // Delete matching id in database
        foodDB.execSQL("DELETE FROM food WHERE id = " + id + ";");

    }

    public void deleteDatabase(View view) {

        // Delete database
        this.deleteDatabase("MyFood");

    }

    @Override
    protected void onDestroy() {

        foodDB.close();

        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, Food.class);
        startActivity(i);
    }


}