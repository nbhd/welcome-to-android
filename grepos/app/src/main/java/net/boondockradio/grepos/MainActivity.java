package net.boondockradio.grepos;

import net.boondockradio.grepos.adapter.RepositoryAdpter;
import net.boondockradio.grepos.api.GithubApi;
import net.boondockradio.grepos.dto.Item;
import net.boondockradio.grepos.dto.Repository;
import net.boondockradio.grepos.service.ApiClient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "grepos";

    private List<Item> mItems;
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
        Call<Repository> call = api.getRepositories("language:java", "Repositories", "100");
        call.enqueue(new Callback<Repository>() {
            @Override
            public void onResponse(Call<Repository> call, Response<Repository> response) {
                Log.d(TAG, "success");
                mItems = response.body().getItems();
                showRepositories();
            }

            @Override
            public void onFailure(Call<Repository> call, Throwable t) {
                Log.d(TAG, "failure");
            }
        });
    }

    private void showRepositories() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_main_activity);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new RepositoryAdpter(mItems);

        mRecyclerView.setAdapter(mAdapter);
    }
}
