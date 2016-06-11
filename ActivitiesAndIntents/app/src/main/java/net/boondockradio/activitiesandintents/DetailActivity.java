package net.boondockradio.activitiesandintents;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String KEY_MESSAGE = "message";

    public static void startActivity(Context context, String message) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(KEY_MESSAGE, message);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        String message = intent.getStringExtra(KEY_MESSAGE);
        TextView text = (TextView) findViewById(R.id.txt_activity_one);

        text.setText(message);
    }
}
