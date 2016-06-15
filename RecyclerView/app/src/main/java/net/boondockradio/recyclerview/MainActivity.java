package net.boondockradio.recyclerview;

import net.boondockradio.recyclerview.dto.StateItem;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, StatesRemoveListener {

    private ArrayList<StateItem> states;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createDummyData();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_main_activity);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new StatesAdapter(states, this);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_add_state, null);
        final EditText editText = (EditText) view.findViewById(R.id.state_name);

        new AlertDialog.Builder(this)
                .setCancelable(true)
                .setTitle(R.string.dialog_add_title)
                .setView(view)
                .setPositiveButton(R.string.dialog_label_add,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            states.add(0, new StateItem(editText.getText().toString(),
                                    R.drawable.alabama));
                            adapter.notifyItemInserted(0);
                        }
                    }
                )
                .create()
                .show();
    }

    @Override
    public void remove(int position) {
        states.remove(position);
        adapter.notifyItemRemoved(position);
    }

    private void createDummyData() {
        states = new ArrayList();
        states.add(new StateItem("Alabama", R.drawable.alabama));
        states.add(new StateItem("Alaska", R.drawable.alaska));
        states.add(new StateItem("Arizona", R.drawable.arizona));
        states.add(new StateItem("Arkansas", R.drawable.arkansas));
        states.add(new StateItem("California", R.drawable.california));
        states.add(new StateItem("Georgia", R.drawable.georgia));
        states.add(new StateItem("Hawaii", R.drawable.hawaii));
        states.add(new StateItem("Idaho", R.drawable.idaho));
        states.add(new StateItem("Illinois", R.drawable.illinois));
        states.add(new StateItem("Indiana", R.drawable.indiana));
        states.add(new StateItem("Iowa", R.drawable.iowa));
        states.add(new StateItem("Kansas", R.drawable.kansas));
        states.add(new StateItem("Kentucky", R.drawable.kentucky));
        states.add(new StateItem("Louisiana", R.drawable.louisiana));
        states.add(new StateItem("Maine", R.drawable.maine));
        states.add(new StateItem("Maryland", R.drawable.maryland));
        states.add(new StateItem("Massachusetts", R.drawable.massachusetts));
        states.add(new StateItem("Michigan", R.drawable.michigan));
        states.add(new StateItem("Minnesota", R.drawable.minnesota));
        states.add(new StateItem("Mississippi", R.drawable.mississippi));
        states.add(new StateItem("Missouri", R.drawable.missouri));
        states.add(new StateItem("Montana", R.drawable.montana));
        states.add(new StateItem("Nebraska", R.drawable.nebraska));
        states.add(new StateItem("Nevada", R.drawable.nevada));
        states.add(new StateItem("New Hampshire", R.drawable.new_hampshire));
        states.add(new StateItem("New Jersey", R.drawable.new_jersey));
        states.add(new StateItem("New Mexico", R.drawable.new_mexico));
        states.add(new StateItem("New York", R.drawable.new_york));
        states.add(new StateItem("North Carolina", R.drawable.north_carolina));
        states.add(new StateItem("North Dakota", R.drawable.north_dakota));
        states.add(new StateItem("Ohio", R.drawable.ohio));
        states.add(new StateItem("Oklahoma", R.drawable.oklahoma));
        states.add(new StateItem("Oregon", R.drawable.oregon));
        states.add(new StateItem("Pennsylvania ", R.drawable.pennsylvania));
        states.add(new StateItem("Rhode Island", R.drawable.rhode_island));
        states.add(new StateItem("South Carolina", R.drawable.south_carolina));
        states.add(new StateItem("South Dakota", R.drawable.south_dakota));
        states.add(new StateItem("Tennessee", R.drawable.tennessee));
        states.add(new StateItem("Texas", R.drawable.texas));
        states.add(new StateItem("Utah", R.drawable.utah));
        states.add(new StateItem("Vermont", R.drawable.vermont));
        states.add(new StateItem("Virginia", R.drawable.virginia));
        states.add(new StateItem("Washington", R.drawable.washington));
        states.add(new StateItem("West Virginia", R.drawable.west_virginia));
        states.add(new StateItem("Wisconsin", R.drawable.wisconsin));
        states.add(new StateItem("Wyoming", R.drawable.wyoming));
    }
}
