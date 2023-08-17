package com.ffeghali.starwarsapp.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import com.ffeghali.starwarsapp.api.ServiceGenerator;
import com.ffeghali.starwarsapp.model.TaskModel;

import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * TODO
 *
 * */
public class StarWarsRepository {
    private static StarWarsRepository instance;

    public static StarWarsRepository getInstance(){
        if(instance == null){
            instance=new StarWarsRepository();
        }
        return instance;
    }

    public LiveData<ResponseBody> makeReactiveQuery() {
        return LiveDataReactiveStreams.fromPublisher(ServiceGenerator.getRequestApi()
                .makeQuery()
                .subscribeOn(Schedulers.io())
                .onErrorReturn(error -> ResponseBody.create(null, "error fromPublisher")));
    }
}
