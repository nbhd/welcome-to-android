package net.boondockradio.activitiesandintents;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ThreeActivity extends AppCompatActivity {

    private static final String KEY_MESSAGE = "message";

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ThreeActivity.class);
        intent.putExtra(KEY_MESSAGE, "on moved 3");
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        Intent intent = getIntent();

        String message = intent.getStringExtra(KEY_MESSAGE);
        TextView text = (TextView) findViewById(R.id.txt_activity_three);

        text.setText(message);
    }
}
