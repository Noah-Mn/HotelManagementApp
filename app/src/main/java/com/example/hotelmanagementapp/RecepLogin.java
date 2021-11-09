package com.example.hotelmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RecepLogin extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    ProgressDialog progressDialog;
    TextInputEditText loginEmail, loginPassword;
    MaterialButton loginBtn;
    TextInputLayout materialLogEmail, materialLogPassword;
    String emailPattern = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
            + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
            + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
            + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
            + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recep_login);
        progressDialog = new ProgressDialog(this);
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        materialLogEmail = findViewById(R.id.materialLogEmail);
        materialLogPassword = findViewById(R.id.materialLogPassword);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(view -> performLogin());

    }
    private void performLogin(){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        String logEmailAddress = loginEmail.getText().toString();
        String logPassword = loginPassword.getText().toString();

        if (!logEmailAddress.matches(emailPattern)){
            materialLogEmail.setError("Please enter a valid email address");
        }else if (logEmailAddress.isEmpty()){
            materialLogEmail.setError("Enter email address");
        } else if (logPassword.isEmpty()){
            materialLogPassword.setError("Please enter password");
        }else{
            progressDialog.setMessage("Logging in please wait...");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            firebaseAuth.signInWithEmailAndPassword(logEmailAddress, logPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                        FirebaseUser receptionist = firebaseAuth.getCurrentUser();
                        updateUI(receptionist);
                        startActivity(new Intent(RecepLogin.this, Dashboard.class));
                    }else {
                        progressDialog.dismiss();
                        updateUI(null);
                        Toast.makeText(RecepLogin.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void updateUI(FirebaseUser receptionist) {
    }
}
