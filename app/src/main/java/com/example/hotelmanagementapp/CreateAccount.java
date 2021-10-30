package com.example.hotelmanagementapp;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
            if (
                    Username.isEmpty() || EmailAddress.isEmpty() || Password.isEmpty() || ConfirmPassword.isEmpty()
            ) {
                Toast.makeText(getApplicationContext(),"Please fill all the fields",Toast.LENGTH_SHORT).show();
            }else {
                if (Password.equals(ConfirmPassword)){
                    databaseReference.child(Username).setValue(databaseHelper).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            //User successfully added
                            Toast.makeText(getApplicationContext(),"Account successfully created!",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(CreateAccount.this, Login.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // User not added!
                            Toast.makeText(getApplicationContext(),"Failed to create account!",Toast.LENGTH_SHORT).show();
                        }
                    });
                }else
                    Toast.makeText(getApplicationContext(),"Passwords do not match",Toast.LENGTH_SHORT).show();
            }

        });
        materialLogin.setOnClickListener(v -> startActivity(new Intent(CreateAccount.this, Login.class)));
    }
}