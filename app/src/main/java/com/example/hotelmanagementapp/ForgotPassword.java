package com.example.hotelmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgotPassword extends AppCompatActivity {
    private static final String TAG = " ";
    Button confirmBtn;
    TextInputEditText newPassword, confirmPassword, emailAddress;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        confirmBtn = findViewById(R.id.confirmBtn);
        newPassword = findViewById(R.id.newPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
        emailAddress = findViewById(R.id.emailAddress);
        progressDialog = new ProgressDialog(this);

       confirmBtn.setOnClickListener(view -> ResetPassword());
    }
    private void ResetPassword(){
      FirebaseUser manager = FirebaseAuth.getInstance().getCurrentUser();
      FirebaseAuth auth = FirebaseAuth.getInstance();
      String New_Password = newPassword.getText().toString();
      String Confirm_Password = confirmPassword.getText().toString();
      String Email = emailAddress.getText().toString();

      if (New_Password.equals(Confirm_Password)){

          progressDialog.setMessage("Loading please wait...");
          progressDialog.setTitle("Password Reset");
          progressDialog.setCanceledOnTouchOutside(false);
          progressDialog.show();

          manager.updatePassword(New_Password).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Password updated ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ForgotPassword.this, Login.class));
                auth.sendPasswordResetEmail(Email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Email sent", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }else {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "" + task.getException(), Toast.LENGTH_SHORT).show();
            }
          });
      }
    }
}