package com.example.hotelmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ForgotPassword extends AppCompatActivity {

    Button confirmBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        confirmBtn = findViewById(R.id.confirmBtn);

        confirmBtn.setOnClickListener(v -> startActivity(new Intent(ForgotPassword.this, Login.class)));
    }
}