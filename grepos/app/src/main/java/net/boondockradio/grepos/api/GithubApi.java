package net.boondockradio.grepos.api;

import net.boondockradio.grepos.dto.Repositories;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// https://api.github.com/search/repositories?q=language%3Ajava&type=Repositories

public interface GithubApi {
    @GET("/search/repositories")
    Call<Repositories> getRepositories(
            @Query("q") String q,
            @Query("type") String type,
            @Query("per_page") String per_page
    );
}
