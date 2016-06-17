package net.boondockradio.grepos;

import net.boondockradio.grepos.adapter.RepositoryAdpter;
import net.boondockradio.grepos.api.GithubApi;
import net.boondockradio.grepos.dto.Repositories;
import net.boondockradio.grepos.dto.Repository;
import net.boondockradio.grepos.service.ApiClient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "grepos";

    private List<Repository> mRepositoryItems;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private RepositoryAdpter mAdapter;

    private int mPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRepositoryItems = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_main_activity);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        fetchRepositories();
    }

    private void fetchRepositories() {
        GithubApi api = ApiClient.getClient().create(GithubApi.class);
        Call<Repositories> call = api.getRepositories("language:java", "Repositories", ++mPage + "", "100");

        final ProgressBar progress = (ProgressBar) findViewById(R.id.progress_main_activity);

        mRepositoryItems.add(null);
        if (mRepositoryItems.size() > 1) {
            mAdapter.notifyItemInserted(mRepositoryItems.size() - 1);
        }

        call.enqueue(new Callback<Repositories>() {
            @Override
            public void onResponse(Call<Repositories> call, Response<Repositories> response) {
                Log.d(TAG, "success");
                mRepositoryItems.remove(mRepositoryItems.size() - 1);

                mRepositoryItems.addAll(response.body().items);
                showRepositories();
                progress.setVisibility(View.GONE);

                mAdapter.setIsLoading(false);
            }

            @Override
            public void onFailure(Call<Repositories> call, Throwable t) {
                Log.d(TAG, "failure");
                progress.setVisibility(View.GONE);
            }
        });
    }

    private void showRepositories() {
        if (mAdapter == null) {
            mAdapter = new RepositoryAdpter(mRepositoryItems, mRecyclerView);
            mAdapter.setOnLoadMoreListener(new RepositoryAdpter.OnLoadMoreListener() {
                @Override
                public void onLoadMore() {
                    fetchRepositories();
                }
            });

            mRecyclerView.setAdapter(mAdapter);
            return;
        }

        mAdapter.notifyDataSetChanged();
    }
}
