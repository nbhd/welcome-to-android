package net.boondockradio.grepos.adapter;

import net.boondockradio.grepos.R;
import net.boondockradio.grepos.dto.Repository;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class RepositoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROGRESS = 0;

    private boolean mIsLoading;
    private List<Repository> mRepositoryItems;

    public RepositoryAdapter(List<Repository> repositoryItems) {
        mRepositoryItems = repositoryItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v;

        switch (viewType) {
            case VIEW_ITEM:
                v = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.row_item, parent, false);
                vh = new ItemViewHolder(v);
                break;

            default:
                v = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.row_progress, parent, false);
                vh = new ProgressViewHolder(v);
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position >= mRepositoryItems.size()) {
            return;
        }

        Repository item = mRepositoryItems.get(position);
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).textView.setText(item.fullName);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == mRepositoryItems.size() ? VIEW_PROGRESS : VIEW_ITEM;
    }

    @Override
    public int getItemCount() {
        int loading = mIsLoading ? 1 : 0;
        return mRepositoryItems.size() + loading;
    }

    public void add(List<Repository> repositories) {
        mRepositoryItems.addAll(repositories);
        notifyItemRangeChanged(mRepositoryItems.size() - repositories.size(), repositories.size());
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading = isLoading;
        if (mIsLoading) {
            notifyItemInserted(mRepositoryItems.size());
        } else {
            notifyItemRemoved(mRepositoryItems.size());
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ItemViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.text_row_item);
        }
    }

    class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progress_row);
        }
    }
}
