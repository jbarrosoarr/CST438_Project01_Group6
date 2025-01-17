package com.daclink.drew.sp22.cst438_project01_starter.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.daclink.drew.sp22.cst438_project01_starter.models.APIValues;
import com.daclink.drew.sp22.cst438_project01_starter.repositories.Repository;

public class SearchViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<APIValues> responseLiveData;

    public SearchViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        repository = new Repository();
        responseLiveData = repository.getResponseLiveData();
    }

    public void searchVolumes(String title) {
        repository.searchVolumes(title);
    }

    public LiveData<APIValues> getVolumesResponseLiveData() {
        return responseLiveData;
    }
}
