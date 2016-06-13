package net.boondockradio.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;

    public ItemViewHolder(View v) {
        super(v);
        textView = (TextView) v.findViewById(R.id.text_raw_item);
        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Context context = textView.getContext();
                String message = context.getString(R.string.states_lover, textView.getText());

                Toast.makeText(
                        context,
                        message,
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}
