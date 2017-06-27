package com.android.xmvp.net;

import com.android.xmvp.model.GankResults;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Dreamaner on 2017/5/15.
 */
public interface GankService {

    @GET("data/{type}/{number}/{page}")
    Flowable<GankResults> getGankData(@Path("type") String type,
                                      @Path("number") int pageSize,
                                      @Path("page") int pageNum);

}
