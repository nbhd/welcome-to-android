package net.boondockradio.grepos.api;

import net.boondockradio.grepos.dto.Repository;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// https://api.github.com/search/repositories?q=language%3Ajava&type=Repositories

public interface GithubApi {
    @GET("/search/repositories")
    Call<Repository> getRepositories(
            @Query("q") String q,
            @Query("type") String type
    );
}
