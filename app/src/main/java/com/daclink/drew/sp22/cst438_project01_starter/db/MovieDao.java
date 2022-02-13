package com.daclink.drew.sp22.cst438_project01_starter.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(MovieEntity movie);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovies(List<MovieEntity> movies);

    @Delete
    void deleteMovie(MovieEntity movie);

    @Query("SELECT * FROM " + AppDatabase.MOVIE_TABLE + " WHERE movieId = :movieId")
    MovieEntity getMovieById(int movieId);

    @Query("SELECT * FROM " + AppDatabase.MOVIE_TABLE + " WHERE imdbId = :imdbId")
    MovieEntity getMovieByImdbId(String imdbId);

    @Query("SELECT * FROM " + AppDatabase.MOVIE_TABLE + " ORDER BY title DESC")
    List<MovieEntity> getAllMovies();

}