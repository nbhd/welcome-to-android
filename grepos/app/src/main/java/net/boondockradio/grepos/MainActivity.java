package net.boondockradio.grepos;

import net.boondockradio.grepos.adapter.RepositoryAdapter;
import net.boondockradio.grepos.api.GithubApi;
import net.boondockradio.grepos.dao.GithubDao;
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

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    private final int LOAD_MORE_THRESHOLD = 1;
    private static final int PER_PAGE = 100;

    private RepositoryAdapter mAdapter;
    private ProgressBar mProgress;

    private CompositeSubscription mCompositeSubscription;

    private int mPage = 0;
    private int mTotal = 0;
    private boolean isLoading = false;

    private GithubDao mGithubDao = new GithubDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCompositeSubscription = new CompositeSubscription();

        mProgress = (ProgressBar) findViewById(R.id.progress_main_activity);
        final List<Repository> repositoryItems = new ArrayList<>();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_main_activity);
        recyclerView.setHasFixedSize(true);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy < 0) {
                    return;
                }

                int last = layoutManager.findLastVisibleItemPosition();

                if (!isLoading && last + LOAD_MORE_THRESHOLD >= mTotal) {
                    fetchRepositories();
                    mAdapter.setIsLoading(true);
                    isLoading = true;
                }
            }
        });

        mAdapter = new RepositoryAdapter(repositoryItems);
        recyclerView.setAdapter(mAdapter);

        fetchRepositories();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeSubscription.unsubscribe();
    }

    private void fetchRepositories() {

        Subscription subscription = mGithubDao
                .getRepositories(
                        "language:java",
                        "Repositories",
                        ++mPage,
                        PER_PAGE
                )
                .subscribe(
                        repositories -> {
                            Log.d(TAG, "onNext");
                            mProgress.setVisibility(View.GONE);

                            mAdapter.add(repositories.items);
                            mAdapter.setIsLoading(false);
                            isLoading = false;
                            mTotal = mAdapter.getItemCount();
                        }, err -> {
                            Log.d(TAG, "onError");
                        }, () -> {
                            Log.d(TAG, "onCompleted");
                        }
                );
        mCompositeSubscription.add(subscription);
    }
}
