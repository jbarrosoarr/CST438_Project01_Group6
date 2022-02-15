package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.firestore.auth.User;

public class UserProfile extends AppCompatActivity {

    private Button changePwBtn;
    private Button logoutBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        changePwBtn = (Button) findViewById(R.id.changePW);
        logoutBtn = (Button) findViewById(R.id.logoutBtn) ;

        changePwBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openChangePasswordActivity();
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogout();
            }
        });

    }

    public void openChangePasswordActivity() {
            Intent intent = new Intent(this, ChangePasswordActivity.class);
            startActivity(intent);
    }

    public void userLogout() {
        Intent intent = new Intent(this, FirstFragment.class);
        startActivity(intent);
    }

}