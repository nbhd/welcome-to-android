package net.boondockradio.activitiesandintents;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OneActivity extends AppCompatActivity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, OneActivity.class);
        intent.putExtra("message", "on moved 1");
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        Intent intent = getIntent();

        String message = intent.getStringExtra("message");
        TextView text = (TextView) findViewById(R.id.text);

        text.setText(message);
    }
}
