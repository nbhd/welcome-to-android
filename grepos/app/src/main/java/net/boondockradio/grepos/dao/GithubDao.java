package net.boondockradio.grepos.dao;

import net.boondockradio.grepos.api.GithubApi;
import net.boondockradio.grepos.dto.Repositories;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GithubDao {

    private GithubApi mGithubApi;

    public GithubDao(GithubApi githubApi) {
        mGithubApi = githubApi;
    }

    public Observable<Repositories> getRepositories(String q, String type, int page, int per_page) {
        return mGithubApi
                .getRepositories(
                        q,
                        type,
                        page + "",
                        per_page + ""
                )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
