package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserDao;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserEntity;
import com.daclink.drew.sp22.cst438_project01_starter.utilities.constants;

public class MovieDetailsActivity extends AppCompatActivity {

    private String mTitle;
    private String mDirector;
    private String mActors;
    private String mRuntime;
    private String mReleased;
    private String mIMDB_Rating;
    private String mPlot;

    private TextView mTitleTextView;
    private TextView mDirectorTextView;
    private TextView mActorsTextView;
    private TextView mRuntimeTextView;
    private TextView mReleasedTextView;
    private TextView mIMDB_Rating_TextView;
    private TextView mPlotTextView;

    private ImageView mPosterImageView;

    private UserEntity mUser;
    private UserDao mUserDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        mUserDao = getDatabase();
        wireUpDisplay();
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
        mReleasedTextView = findViewById(R.id.movie_details_rating);
        mPlotTextView = findViewById(R.id.movie_details_plot);
    }

    private void exit() {

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
                exit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static Intent newIntent(Context packageContext, String imdbId) {
        Intent intent = new Intent(packageContext, MovieDetailsActivity.class);
        intent.putExtra(constants.IMDD_ID, imdbId);
        return intent;
    }
}