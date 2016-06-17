package net.boondockradio.grepos.adapter;

import net.boondockradio.grepos.R;
import net.boondockradio.grepos.dto.Repository;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class RepositoryAdpter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROGRESS = 0;

    private final int LOAD_MORE_THRESHOLD = 2;

    private List<Repository> mRepositoryItems;
    private RecyclerView mRecyclerView;

    private OnLoadMoreListener onLoadMoreListener;
    private int total = 0;
    private int last = 0;
    private boolean isLoading = false;

    public RepositoryAdpter(List<Repository> repositoryItems, RecyclerView recyclerView) {
        mRepositoryItems = repositoryItems;
        mRecyclerView = recyclerView;

        final LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                total = layoutManager.getItemCount();
                last = layoutManager.findLastVisibleItemPosition();

                if (dy < 0) {
                    return;
                }

                if (!isLoading && last + LOAD_MORE_THRESHOLD >= total) {
                    onLoadMoreListener.onLoadMore();
                    isLoading = true;
                }
            }
        });
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
        Repository item = mRepositoryItems.get(position);
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).textView.setText(item.fullName);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mRepositoryItems.get(position) != null ? VIEW_ITEM : VIEW_PROGRESS;
    }

    @Override
    public int getItemCount() {
        return mRepositoryItems.size();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading = isLoading;
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

    public interface OnLoadMoreListener {
        void onLoadMore();
    }
}
