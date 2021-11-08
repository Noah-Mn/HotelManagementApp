package com.example.hotelmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;

public class SelectUserType extends AppCompatActivity {
    MaterialButton ManagerButton, RecepButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user_type);
        ManagerButton = findViewById(R.id.ManagerButton);
        RecepButton = findViewById(R.id.RecepButton);


    }
}