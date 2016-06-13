package net.boondockradio.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class StatesAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private ArrayList<String> states;

    public StatesAdapter(ArrayList<String> states) {
        this.states = states;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.raw_item, parent, false);

        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final String data;
        data = states.get(position);

        holder.textView.setText(data);
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

}
