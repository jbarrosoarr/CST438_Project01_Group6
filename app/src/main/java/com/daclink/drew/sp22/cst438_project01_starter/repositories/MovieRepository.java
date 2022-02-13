package com.daclink.drew.sp22.cst438_project01_starter.repositories;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.MovieEntity;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MovieRepository {
    public static MovieRepository instance;

    public List<MovieEntity> mMovies;
    private AppDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    public static MovieRepository getInstance(Context context) {
        if (instance == null) {
            instance = new MovieRepository(context);
        }
        return instance;
    }

    private MovieRepository(Context context) {
        mDb = AppDatabase.getInstance(context);
        mMovies = getAllMovies();
    }

    public List<MovieEntity> getAllMovies() {
        return mDb.movieDao().getAllMovies();
    }

    public MovieEntity getMovieById(int movieId) {
        return mDb.movieDao().getMovieById(movieId);
    }

    public MovieEntity getMovieByImdbId(String imdbId) {
        return mDb.movieDao().getMovieByImdbId(imdbId);
    }

    public void insertMovie(MovieEntity movie) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.movieDao().insertMovie(movie);
            }
        });
    }

    public void insertMovies(List<MovieEntity> movies) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.movieDao().insertMovies(movies);
            }
        });
    }

    public void deleteMovie(MovieEntity movie) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.movieDao().deleteMovie(movie);
            }
        });
    }

    public MutableLiveData<List<MovieEntity>> getMovies() {
        MutableLiveData<List<MovieEntity>> movies = new MutableLiveData<>();
        movies.setValue(mMovies);
        return movies;
    }
}
