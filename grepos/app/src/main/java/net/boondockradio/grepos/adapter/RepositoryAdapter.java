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
    private enum  ViewType {
        Progress(0),
        Item(1);

        private int type;

        private ViewType(int type) {
            this.type = type;
        }

        public int toValue() {
            return type;
        }

        public static ViewType fromValue(int type) {
            ViewType result = Progress;

            for (ViewType vt: values()) {
                if (vt.toValue() == type) {
                    result = vt;
                    break;
                }
            }
            return result;
        }
    };

    private boolean mIsLoading;
    private List<Repository> mRepositoryItems;

    public RepositoryAdapter(List<Repository> repositoryItems) {
        mRepositoryItems = repositoryItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v;
        ViewType vt = ViewType.fromValue(viewType);

        if (vt == ViewType.Item) {
            v = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.row_item, parent, false);
            vh = new ItemViewHolder(v);
        } else {
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
        return position == mRepositoryItems.size() ? ViewType.Progress.toValue() : ViewType.Item.toValue();
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
