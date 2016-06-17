package net.boondockradio.grepos.adapter;

import net.boondockradio.grepos.R;
import net.boondockradio.grepos.dto.Repository;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RepositoryAdpter extends RecyclerView.Adapter<RepositoryAdpter.ItemViewHolder> {

    private List<Repository> mRepositoryItems;

    public RepositoryAdpter(List<Repository> repositoryItems) {
        mRepositoryItems = repositoryItems;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.raw_item, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Repository item = mRepositoryItems.get(position);
        holder.textView.setText(item.fullName);
    }

    @Override
    public int getItemCount() {
        return mRepositoryItems.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ItemViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.text_raw_item);
        }
    }
}
