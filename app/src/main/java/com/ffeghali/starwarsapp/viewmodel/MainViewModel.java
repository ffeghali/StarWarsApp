package com.ffeghali.starwarsapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ffeghali.starwarsapp.model.TaskModel;
import com.ffeghali.starwarsapp.repository.StarWarsRepository;

import okhttp3.ResponseBody;

public class MainViewModel extends ViewModel {
        private final StarWarsRepository repository;

        public MainViewModel() {
            repository = StarWarsRepository.getInstance();
        }

        public LiveData<ResponseBody> makeQuery(){
            return repository.makeReactiveQuery();
        }

    }

