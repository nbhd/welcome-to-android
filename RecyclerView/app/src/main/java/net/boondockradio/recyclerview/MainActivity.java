package net.boondockradio.recyclerview;

import net.boondockradio.recyclerview.dto.StateItem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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

        adapter = new StatesAdapter(states);
        recyclerView.setAdapter(adapter);

    }

    private void createDummyData() {
        states = new ArrayList();
        states.add(new StateItem("Alabama"));
        states.add(new StateItem("Alaska"));
        states.add(new StateItem("Arizona"));
        states.add(new StateItem("Arkansas"));
        states.add(new StateItem("California"));
        states.add(new StateItem("Georgia"));
        states.add(new StateItem("Hawaii"));
        states.add(new StateItem("Idaho"));
        states.add(new StateItem("Illinois"));
        states.add(new StateItem("Indiana"));
        states.add(new StateItem("Iowa"));
        states.add(new StateItem("Kansas"));
        states.add(new StateItem("Kentucky"));
        states.add(new StateItem("Louisiana"));
        states.add(new StateItem("Maine"));
        states.add(new StateItem("Maryland"));
        states.add(new StateItem("Massachusetts"));
        states.add(new StateItem("Michigan"));
        states.add(new StateItem("Minnesota"));
        states.add(new StateItem("Mississippi"));
        states.add(new StateItem("Missouri"));
        states.add(new StateItem("Montana"));
        states.add(new StateItem("Nebraska"));
        states.add(new StateItem("Nevada"));
        states.add(new StateItem("New Hampshire"));
        states.add(new StateItem("New Jersey"));
        states.add(new StateItem("New Mexico"));
        states.add(new StateItem("New York"));
        states.add(new StateItem("North Carolina"));
        states.add(new StateItem("North Dakota"));
        states.add(new StateItem("Ohio"));
        states.add(new StateItem("Oklahoma"));
        states.add(new StateItem("Oregon"));
        states.add(new StateItem("Pennsylvania "));
        states.add(new StateItem("Rhode Island"));
        states.add(new StateItem("South Carolina"));
        states.add(new StateItem("South Dakota"));
        states.add(new StateItem("Tennessee"));
        states.add(new StateItem("Texas"));
        states.add(new StateItem("Utah"));
        states.add(new StateItem("Vermont"));
        states.add(new StateItem("Virginia"));
        states.add(new StateItem("Washington"));
        states.add(new StateItem("West Virginia"));
        states.add(new StateItem("Wisconsin"));
        states.add(new StateItem("Wyoming"));
    }
}
