package net.boondockradio.activitiesandintents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class TwoActivity extends AppCompatActivity {

    private static final String KEY_MESSAGE = "message";

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, TwoActivity.class);
        intent.putExtra(KEY_MESSAGE, "on moved 2");
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        Intent intent = getIntent();

        String message = intent.getStringExtra(KEY_MESSAGE);
        TextView text = (TextView) findViewById(R.id.text);

        text.setText(message);
    }
}
