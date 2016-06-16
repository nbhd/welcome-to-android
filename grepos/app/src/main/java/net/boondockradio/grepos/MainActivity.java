package net.boondockradio.grepos;

import net.boondockradio.grepos.adapter.RepositoryAdpter;
import net.boondockradio.grepos.api.GithubApi;
import net.boondockradio.grepos.dto.Repository;
import net.boondockradio.grepos.dto.Repositories;
import net.boondockradio.grepos.service.ApiClient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "grepos";

    private List<Repository> mRepositoryItems;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchRepositories();
    }

    private void fetchRepositories() {
        GithubApi api = ApiClient.getClient().create(GithubApi.class);
        Call<Repositories> call = api.getRepositories("language:java", "Repositories", "100");

        final ProgressBar progress = (ProgressBar) findViewById(R.id.progress_main_activity);
        progress.setVisibility(View.VISIBLE);

        call.enqueue(new Callback<Repositories>() {
            @Override
            public void onResponse(Call<Repositories> call, Response<Repositories> response) {
                Log.d(TAG, "success");
                mRepositoryItems = response.body().items;
                showRepositories();
                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Repositories> call, Throwable t) {
                Log.d(TAG, "failure");
                progress.setVisibility(View.GONE);
            }
        });
    }

    private void showRepositories() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_main_activity);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new RepositoryAdpter(mRepositoryItems);

        mRecyclerView.setAdapter(mAdapter);
    }
}
