package net.boondockradio.activitiesandintents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttons[] = new Button[4];
        buttons[0] = (Button) findViewById(R.id.activity1);
        buttons[1] = (Button) findViewById(R.id.activity2);
        buttons[2] = (Button) findViewById(R.id.activity3);
        buttons[3] = (Button) findViewById(R.id.activity4);

        for (Button b: buttons) {
            if (b == null) {
                continue;
            }
            b.setOnClickListener(this);
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity1:
                OneActivity.startActivity(this);
                break;

            case R.id.activity2:
                TwoActivity.startActivity(this);
                break;

            case R.id.activity3:
                ThreeActivity.startActivity(this);
                break;

            case R.id.activity4:
                FourActivity.startActivity(this);
                break;
        }
    }
}
