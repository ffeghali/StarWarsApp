package com.ffeghali.starwarsapp.api;

import com.ffeghali.starwarsapp.util.Constants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static RequestApi requestApi = retrofit.create(RequestApi.class);

    public static RequestApi getRequestApi(){
        return requestApi;
    }
}
