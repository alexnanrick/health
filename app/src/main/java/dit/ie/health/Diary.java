package dit.ie.health;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public  class Diary extends Activity implements OnClickListener {

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, Diary.class);
        startActivity(i);
    }

    EditText notesEditText;
    Button btnSettings;
    private static final int SETTINGS_INFO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_view);

        notesEditText = (EditText) findViewById(R.id.notesEditText);

        if(savedInstanceState != null){

            String notes = savedInstanceState.getString("Your Diary");

            notesEditText.setText(notes);

        }

        // 2. Retrieves the String stored in shared preferences or "EMPTY" if nothing
        String sPNotes = getPreferences(Context.MODE_PRIVATE).getString("NOTES", "EMPTY");

        if(!sPNotes.equals("EMPTY")){

            notesEditText.setText(sPNotes);
        }

        // 3. Get a reference to the settings button
        btnSettings = (Button) findViewById(R.id.btnSettings);

        // 3. If the button is clicked create an intent to open the SettingsActivity class
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPreferences = new Intent(getApplicationContext(),
                        SettingsActivity.class);

                // 3. Start the activity and then pass results to onActivityResult
                startActivityForResult(intentPreferences, SETTINGS_INFO);
            }
        });

    }

    // 3. Called after the settings intent closes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode, resultCode, data);

        // 3. Check that the intent with the id SETTINGS_INFO called here
        if(requestCode == SETTINGS_INFO){

            updateNoteText();

        }

    }

    // 3. Update the text changes in the EditText box
    private void updateNoteText(){

        // Shared key value pairs are here
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Check if the checkbox was clicked
        if(sharedPreferences.getBoolean("pref_text_bold", false)){

            // Set the text to bold
            notesEditText.setTypeface(null, Typeface.BOLD_ITALIC);

        }

        else {

            // If not checked set the text to normal
            notesEditText.setTypeface(null, Typeface.NORMAL);

        }



        // Get the value stored in the list preference or give a value of 16
        String textSizeStr = sharedPreferences.getString("pref_text_size", "16");

        // Convert the string returned to a float
        float textSizeFloat = Float.parseFloat(textSizeStr);

        // Set the text size for the EditText box
        notesEditText.setTextSize(textSizeFloat);

    }

    // 1. Called before Android kills an application, but doesn't protect you
    // if the user kills the app or restarts the device
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        // Save the value in the EditText using the key NOTES
        outState.putString("NOTES",
                notesEditText.getText().toString());

        super.onSaveInstanceState(outState);
    }

    // 2. Will save key value pairs to SharedPreferences
    private void saveSettings(){


        SharedPreferences.Editor sPEditor = getPreferences(Context.MODE_PRIVATE).edit();

        // Add the key "NOTES" and assign it to the value
        sPEditor.putString("NOTES", notesEditText.getText().toString());

        // Save the shared preferences
        sPEditor.commit();

    }

    // 2. Called if the app is forced to close
    @Override
    protected void onStop() {

        saveSettings();

        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}