package net.boondockradio.activitiesandintents;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ThreeActivity extends AppCompatActivity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, OneActivity.class);
        intent.putExtra("message", "on moved 3");
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        Intent intent = getIntent();

        if (intent != null) {
            String message = intent.getStringExtra("message");
            TextView text = (TextView) findViewById(R.id.text);

            if (text != null) {
                text.setText(message);
            }
        }
    }
}
