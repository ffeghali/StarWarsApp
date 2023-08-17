package com.ffeghali.starwarsapp.api;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface RequestApi {
    @GET("people/1")
    Flowable<ResponseBody> makeQuery();
}
