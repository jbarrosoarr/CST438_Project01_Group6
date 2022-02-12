package com.daclink.drew.sp22.cst438_project01_starter.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.daclink.drew.sp22.cst438_project01_starter.MovieDetailsActivity;
import com.daclink.drew.sp22.cst438_project01_starter.models.APIValues;
import com.daclink.drew.sp22.cst438_project01_starter.repositories.Repository;

public class MovieDetailsViewModel extends AndroidViewModel {
    private Repository mRepository;
    private LiveData<APIValues> mResponseLiveData;

    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        mRepository = new Repository();
        mResponseLiveData = mRepository.getResponseLiveData();
    }

    public void searchMovieByIMDB_Id(String imdbId) {
        mRepository.searchMovieByIMDB_Id(imdbId);
    }

    public LiveData<APIValues> getResponseLiveData() {
        return mResponseLiveData;
    }
}
