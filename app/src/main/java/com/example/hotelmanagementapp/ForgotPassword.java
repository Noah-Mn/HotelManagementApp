package com.example.hotelmanagementapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class ForgotPassword extends AppCompatActivity {
    Button confirmBtn;
    TextInputEditText newPassword, confirmPassword, emailAddress;
    ProgressDialog progressDialog;
    TextInputLayout materialEmailAddress, materialNewPassword, materialConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        confirmBtn = findViewById(R.id.confirmBtn);
        newPassword = findViewById(R.id.newPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
        emailAddress = findViewById(R.id.emailAddress);
        progressDialog = new ProgressDialog(this);
        materialEmailAddress = findViewById(R.id.materialEmailAddress);
        materialNewPassword = findViewById(R.id.materialNewPassword);
        materialConfirmPassword = findViewById(R.id.materialConfirmPassword);

       confirmBtn.setOnClickListener(view -> ResetPassword());
    }
    private void ResetPassword(){
      FirebaseUser manager = FirebaseAuth.getInstance().getCurrentUser();
      FirebaseAuth auth = FirebaseAuth.getInstance();
      String New_Password = Objects.requireNonNull(newPassword.getText()).toString();
      String Confirm_Password = Objects.requireNonNull(confirmPassword.getText()).toString();
      String Email = Objects.requireNonNull(emailAddress.getText()).toString();

      if (New_Password.equals(Confirm_Password)){

          progressDialog.setMessage("Loading please wait...");
          progressDialog.setTitle("Password Reset");
          progressDialog.setCanceledOnTouchOutside(false);
          progressDialog.show();

          assert manager != null;
          manager.updatePassword(New_Password).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Password updated ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ForgotPassword.this, Login.class));
                auth.sendPasswordResetEmail(Email).addOnCompleteListener(task1 -> {
                    if (task1.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Email sent", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "" + task1.getException(), Toast.LENGTH_SHORT).show();
                    }
                });
            }else {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "" + task.getException(), Toast.LENGTH_SHORT).show();
            }
          });
      }else {
          materialNewPassword.setError("Passwords don't match!");
          materialConfirmPassword.setError("Passwords don't match!");
      }

    }
}