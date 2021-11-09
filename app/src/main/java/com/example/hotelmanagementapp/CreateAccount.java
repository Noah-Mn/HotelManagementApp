package com.example.hotelmanagementapp;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class CreateAccount extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    private void updateUI(FirebaseUser currentUser) {

    }

    TextView materialLogin;
    TextInputLayout materialPassword, materialConfirmPassword, materialEmailAddress, materialUsername;
    MaterialButton signupBtn;
    ProgressDialog progressDialog;
    TextInputEditText textUsername, textEmailAddress, textPassword, textConfirmPassword;
    String emailPattern = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
            + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
            + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
            + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

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
        materialPassword = findViewById(R.id.materialPassword);
        materialUsername = findViewById(R.id.materialUsername);
        materialEmailAddress = findViewById(R.id.materialEmailAddress);
        materialConfirmPassword = findViewById(R.id.materialConfirmPassword);
        progressDialog = new ProgressDialog(this);

        signupBtn.setOnClickListener(v -> performSignup());

        materialLogin.setOnClickListener(v -> startActivity(new Intent(CreateAccount.this, Login.class)));
    }
    private void performSignup(){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        String Username = Objects.requireNonNull(textUsername.getText()).toString();
        String EmailAddress = Objects.requireNonNull(textEmailAddress.getText()).toString();
        String Password = Objects.requireNonNull(textPassword.getText()).toString();
        String ConfirmPassword = Objects.requireNonNull(textConfirmPassword.getText()).toString();

        if (!EmailAddress.matches(emailPattern)){
            materialEmailAddress.setError("Please enter a valid email address");
        }else if (Username.isEmpty()){
            materialUsername.setError("Enter username");
        } else if (EmailAddress.isEmpty()){
            materialEmailAddress.setError("Enter email address");
        }else if (Password.isEmpty()){
            materialPassword.setError("Enter password");
        }else if(ConfirmPassword.isEmpty()){
            materialConfirmPassword.setError("Enter password");
        }else {
                if (Password.equals(ConfirmPassword)){
                    progressDialog.setMessage("Registering please wait...");
                    progressDialog.setTitle("Registration");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    firebaseAuth.createUserWithEmailAndPassword(EmailAddress,Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(),"Account successfully created!",Toast.LENGTH_SHORT).show();
                                FirebaseUser manager = firebaseAuth.getCurrentUser();
                                updateUI(manager);
                                startActivity(new Intent(CreateAccount.this, Login.class));
                            }else {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), ""+task.getException(), Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }
                        }
                    });

                }else{
                    materialPassword.setError("Wrong password");
                    materialConfirmPassword.setError("Wrong password");
                    Toast.makeText(getApplicationContext(),"Passwords do not match",Toast.LENGTH_SHORT).show();
                }


        }
    }
}