package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daclink.drew.sp22.cst438_project01_starter.databinding.ActivityMainBinding;
import com.daclink.drew.sp22.cst438_project01_starter.databinding.ActivityMovieDetailsBinding;
import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserDao;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserEntity;
import com.daclink.drew.sp22.cst438_project01_starter.utilities.constants;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity {

    private int mUserId;

    private String mTitle;
    private String mDirector;
    private String mActors;
    private String mRuntime;
    private String mReleased;
    private String mGenre;
    private String mPlot;
    private String mYear;
    private String mImageUrl;
    private String mImdbId;

    private TextView mTitleTextView;
    private TextView mDirectorTextView;
    private TextView mActorsTextView;
    private TextView mRuntimeTextView;
    private TextView mReleasedTextView;
    private TextView mGenreTextView;
    private TextView mPlotTextView;

    private ImageView mPosterImageView;

    private UserEntity mUser;
    private UserDao mUserDao;

    private ActivityMovieDetailsBinding mBinding;

    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        mPrefs = getSharedPreferences(constants.PREFERENCES_KEY, MODE_PRIVATE);
        mUserId = mPrefs.getInt(constants.USER_ID_KEY, -1);

        mBinding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        // mUserDao = getDatabase();
        // mUser = mUserDao.getUserById(mUserId);

        wireUpDisplay();
        setValues();
        setImageView();
        setTextViews();

        mBinding.movieDetailsFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (true) {
                    Snackbar.make(v, "You Already Saved This Movie", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    addMovieToDb();

                    Snackbar.make(v, "Movie Successfully Saved", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();                    // mUserDao.updateUser(mUser);
                }
            }
        });
    }

    private UserDao getDatabase() {
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        UserDao userDao = db.userDao();
        return userDao;
    }

    private void wireUpDisplay() {
        mTitleTextView = findViewById(R.id.movie_details_title);
        mDirectorTextView = findViewById(R.id.movie_details_director);
        mActorsTextView = findViewById(R.id.movie_details_actors);
        mRuntimeTextView = findViewById(R.id.movie_details_runtime);
        mReleasedTextView = findViewById(R.id.movie_details_released);
        mGenreTextView = findViewById(R.id.movie_details_genre);
        mPlotTextView = findViewById(R.id.movie_details_plot);
        mPosterImageView = findViewById(R.id.movie_details_imageview);
    }

    private void setValues() {
        mTitle = getIntent().getStringExtra(constants.TITLE);
        mDirector = getIntent().getStringExtra(constants.DIRECTOR);
        mActors = getIntent().getStringExtra(constants.ACTORS);
        mRuntime = getIntent().getStringExtra(constants.RUNTIME);
        mReleased = getIntent().getStringExtra(constants.RELEASED);
        mGenre = getIntent().getStringExtra(constants.GENRE);
        mPlot = getIntent().getStringExtra(constants.PLOT);
        mYear = getIntent().getStringExtra(constants.YEAR);
        mImdbId = getIntent().getStringExtra(constants.IMDB_ID);
    }

    private void setImageView() {
        mImageUrl = getIntent().getStringExtra(constants.POSTER);

        if (mImageUrl != null) {
            mImageUrl.replace("http://", "https://");

            Glide.with(getApplicationContext())
                    .load(mImageUrl)
                    .into(mPosterImageView);
        }
    }

    private void setTextViews() {
        mTitleTextView.setText(mTitle + " (" + mYear + ")");
        mDirectorTextView.append(": " + mDirector);
        mActorsTextView.setText("Starring: " + mActors);
        mRuntimeTextView.setText(mRuntime);
        mReleasedTextView.append(": " + mReleased);
        mGenreTextView.append(": " + mGenre);
        mPlotTextView.append(":\n" + mPlot);
    }

    private void addMovieToDb() {
        // check that movie isn't already in the db
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.exit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.exit:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static Intent newIntent(Context packageContext, List<String> movieInfo) {
        Intent intent = new Intent(packageContext, MovieDetailsActivity.class);

        intent.putExtra(constants.TITLE, movieInfo.get(0));
        intent.putExtra(constants.YEAR, movieInfo.get(1));
        intent.putExtra(constants.RELEASED, movieInfo.get(2));
        intent.putExtra(constants.GENRE, movieInfo.get(3));
        intent.putExtra(constants.DIRECTOR, movieInfo.get(4));
        intent.putExtra(constants.WRITER, movieInfo.get(5));
        intent.putExtra(constants.ACTORS, movieInfo.get(6));
        intent.putExtra(constants.PLOT, movieInfo.get(7));
        intent.putExtra(constants.LANGUAGE, movieInfo.get(8));
        intent.putExtra(constants.POSTER, movieInfo.get(9));
        intent.putExtra(constants.COUNTRY, movieInfo.get(10));
        intent.putExtra(constants.RUNTIME, movieInfo.get(11));
        intent.putExtra(constants.IMDB_ID, movieInfo.get(12));

        return intent;
    }
}