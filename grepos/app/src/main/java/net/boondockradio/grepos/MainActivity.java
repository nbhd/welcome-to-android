package net.boondockradio.grepos;

import net.boondockradio.grepos.adapter.RepositoryAdapter;
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

    private final String TAG = MainActivity.class.getSimpleName();
    private final int LOAD_MORE_THRESHOLD = 1;
    private final String PER_PAGE = "50";

    private List<Repository> mRepositoryItems;
    private RepositoryAdapter mAdapter;
    private ProgressBar mProgress;

    private int mPage = 0;

    private int total = 0;
    private int last = 0;
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgress = (ProgressBar) findViewById(R.id.progress_main_activity);
        mRepositoryItems = new ArrayList<>();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_main_activity);
        recyclerView.setHasFixedSize(true);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                total = layoutManager.getItemCount();
                last = layoutManager.findLastVisibleItemPosition();

                if (dy < 0) {
                    return;
                }

                if (!isLoading && last + LOAD_MORE_THRESHOLD >= total) {
                    fetchRepositories();
                    mAdapter.setIsLoading(true);
                    isLoading = true;
                }
            }
        });

        mAdapter = new RepositoryAdapter(mRepositoryItems);
        recyclerView.setAdapter(mAdapter);

        fetchRepositories();
    }

    private void fetchRepositories() {
        GithubApi api = ApiClient.getClient().create(GithubApi.class);
        Call<Repositories> call = api.getRepositories("language:java", "Repositories", ++mPage + "", PER_PAGE);

        call.enqueue(new Callback<Repositories>() {
            @Override
            public void onResponse(Call<Repositories> call, Response<Repositories> response) {
                Log.d(TAG, "success");
                mProgress.setVisibility(View.GONE);

                mAdapter.add(response.body().items);
                mAdapter.setIsLoading(false);
                isLoading = false;
            }

            @Override
            public void onFailure(Call<Repositories> call, Throwable t) {
                Log.d(TAG, "failure");
                mProgress.setVisibility(View.GONE);
            }
        });
    }
}
