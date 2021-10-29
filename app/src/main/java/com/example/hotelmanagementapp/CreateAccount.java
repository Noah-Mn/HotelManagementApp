package com.example.hotelmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class CreateAccount extends AppCompatActivity {
    TextView materialLogin;
    MaterialButton signupBtn;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseHelper databaseHelper;
    TextInputEditText textUsername, textEmailAddress, textPassword, textConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        materialLogin = findViewById(R.id.materialLogin);
        signupBtn = findViewById(R.id.signupBtn);
        textUsername = findViewById(R.id.textUsername);
        textEmailAddress = findViewById(R.id.textEmailAddress);
        textPassword = findViewById(R.id.textPassword);
        textConfirmPassword = findViewById(R.id.textConfirmPassword);

        signupBtn.setOnClickListener(v -> {
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference("Users");

            String Username = Objects.requireNonNull(textUsername.getText()).toString();
            String EmailAddress = Objects.requireNonNull(textEmailAddress.getText()).toString();
            String Password = Objects.requireNonNull(textPassword.getText()).toString();
            String ConfirmPassword = Objects.requireNonNull(textConfirmPassword.getText()).toString();

            databaseHelper = new DatabaseHelper(Username, EmailAddress, Password, ConfirmPassword);
            databaseReference.child(Username).setValue(databaseHelper);

//                startActivity(new Intent(CreateAccount.this, Login.class));
        });
        materialLogin.setOnClickListener(v -> startActivity(new Intent(CreateAccount.this, Login.class)));
    }
}