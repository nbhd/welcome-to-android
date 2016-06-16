package net.boondockradio.grepos;

import net.boondockradio.grepos.api.GithubApi;
import net.boondockradio.grepos.dto.Item;
import net.boondockradio.grepos.dto.Repository;
import net.boondockradio.grepos.service.ApiClient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchRepositories();
    }

    private void fetchRepositories() {
        GithubApi api = ApiClient.getClient().create(GithubApi.class);
        Call<Repository> call = api.getRepositories("language:java", "Repositories");
        call.enqueue(new Callback<Repository>() {
            @Override
            public void onResponse(Call<Repository> call, Response<Repository> response) {
                Log.d(TAG, "success");
                mItems = response.body().getItems();
            }

            @Override
            public void onFailure(Call<Repository> call, Throwable t) {
                Log.d(TAG, "failure");
            }
        });
    }
}
