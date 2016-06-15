package net.boondockradio.recyclerview;

import net.boondockradio.recyclerview.dto.StateItem;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StatesAdapter extends RecyclerView.Adapter<StatesAdapter.ItemViewHolder> {

    private ArrayList<StateItem> states;
    private StatesRemoveListener removeListener;

    public StatesAdapter(ArrayList<StateItem> states, StatesRemoveListener removeListener) {
        this.states = states;
        this.removeListener = removeListener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.raw_item, parent, false);

        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        final StateItem item = states.get(position);
        String stateText = item.title;

        final Context context = holder.itemView.getContext();
        holder.textView.setText(stateText);

        holder.imageView.setImageResource(item.resId);

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hateIndex = (holder.getAdapterPosition() == states.size() - 1) ? 0 : holder.getAdapterPosition() + 1;
                String message = context
                        .getString(R.string.states_lover, item.title,
                                states.get(hateIndex).title);
                Toast.makeText(
                        context,
                        message,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, item.title);
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder
                        .setMessage(
                                context.getString(
                                        R.string.dialog_remove_message,
                                        item.title
                                )
                        )
                        .setCancelable(true)
                        .setPositiveButton(R.string.dialog_label_yes,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        removeListener.remove(holder.getAdapterPosition());
                                    }
                                })
                        .create()
                        .show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public ImageView imageView;

        public ItemViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.text_raw_item);
            imageView = (ImageView) v.findViewById(R.id.image_raw_item);
        }
    }
}
