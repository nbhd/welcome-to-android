package net.boondockradio.activitiesandintents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.btn_activity1);
        button.setOnClickListener(this);
        button.setEnabled(false);

        EditText edit = (EditText) findViewById(R.id.edit_txt);
        assert edit != null;
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    button.setEnabled(false);
                    return;
                }

                button.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void onClick(View v) {
        EditText edit = (EditText) findViewById(R.id.edit_txt);
        String message = edit.getText().toString();
        DetailActivity.startActivity(this, message);
    }
}
