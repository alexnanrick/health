package dit.ie.health;

import android.app.Activity;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.LinearLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ViewOnClick extends Activity
{
    LinearLayout.LayoutParams layoutParams;
    LinearLayout ll;
    static int i;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button)findViewById(R.id.list_item);
        ll = (LinearLayout)findViewById(R.id.list_item);

        layoutParams = new LinearLayout.LayoutParams
                (AbsoluteLayout.LayoutParams.WRAP_CONTENT, AbsoluteLayout.LayoutParams.WRAP_CONTENT);
        b.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                TextView view = new TextView(ViewOnClick.this);
                view.setText(++i + " " + " new view is displayed here test");
                ll.addView(view, layoutParams);

            }
        });
    }
}