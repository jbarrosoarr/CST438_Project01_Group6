package com.daclink.drew.sp22.cst438_project01_starter.viewModels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.daclink.drew.sp22.cst438_project01_starter.db.MovieEntity;
import com.daclink.drew.sp22.cst438_project01_starter.repositories.MovieRepository;

import java.util.List;

public class MovieListViewModel extends AndroidViewModel {
    private MovieRepository mRepository;
    private MutableLiveData<List<MovieEntity>> mMovies;

    public MovieListViewModel(@NonNull Application application) {
        super (application);
    }

    public void init() {
        mRepository = MovieRepository.getInstance(getApplication().getApplicationContext());
        mMovies = mRepository.getMovies();
    }

    public LiveData<List<MovieEntity>> getMovies() {
        return mMovies;
    }
}
