package net.boondockradio.grepos.dao;

import net.boondockradio.grepos.api.GithubApi;
import net.boondockradio.grepos.dto.Repositories;
import net.boondockradio.grepos.service.ApiClient;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GithubDao {

    private GithubApi mGithubApi = ApiClient.getClient().create(GithubApi.class);

    public GithubDao() {
    }

    public Observable<Repositories> getRepositories(String q, String type, int page, int per_page) {
        return mGithubApi
                .getRepositories(
                        q,
                        type,
                        String.valueOf(page),
                        String.valueOf(per_page)
                )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
