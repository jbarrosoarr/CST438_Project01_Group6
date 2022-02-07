package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChangePasswordActivity extends AppCompatActivity {

    private String mOldPassword;
    private String mNewPassword;

    private EditText mOldPasswordField;
    private EditText mNewPasswordField;

    private Button mConfirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        wireUpDisplay();
    }

    private void wireUpDisplay() {
        mOldPasswordField = findViewById(R.id.old_password_edittext);
        mNewPasswordField = findViewById(R.id.new_password_edittext);
        mConfirmBtn = findViewById(R.id.change_password_confirm_btn);

        mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValuesFromDisplay();

                if (!validateOldPassword()) {
                    Toast.makeText(ChangePasswordActivity.this, "Please enter your correct old password", Toast.LENGTH_SHORT).show();
                } else {

                }
            }
        });
    }

    private void getValuesFromDisplay() {
        mOldPassword = mOldPasswordField.getText().toString();
        mNewPassword = mNewPasswordField.getText().toString();
    }

    public boolean validateOldPassword() {
        return false;
    }

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, ChangePasswordActivity.class);
        return intent;
    }
}