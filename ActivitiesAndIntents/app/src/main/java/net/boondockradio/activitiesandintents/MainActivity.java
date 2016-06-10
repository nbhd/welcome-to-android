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
        buttons[0] = (Button) findViewById(R.id.btn_activity1);
        buttons[1] = (Button) findViewById(R.id.btn_activity2);
        buttons[2] = (Button) findViewById(R.id.btn_activity3);
        buttons[3] = (Button) findViewById(R.id.btn_activity4);

        for (Button b: buttons) {
            b.setOnClickListener(this);
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_activity1:
                OneActivity.startActivity(this);
                break;

            case R.id.btn_activity2:
                TwoActivity.startActivity(this);
                break;

            case R.id.btn_activity3:
                ThreeActivity.startActivity(this);
                break;

            case R.id.btn_activity4:
                FourActivity.startActivity(this);
                break;
        }
    }
}
