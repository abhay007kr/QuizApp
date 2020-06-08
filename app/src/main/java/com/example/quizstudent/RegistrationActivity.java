package com.example.quizstudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {
    EditText regEmail,regPassword;
    Button register;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initView();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });
    }

    private void initView()
    {
        regEmail = findViewById(R.id.reg_email);
        regPassword = findViewById(R.id.reg_password);
        register = findViewById(R.id.register);
        mAuth = FirebaseAuth.getInstance();
    }

    private void createAccount() {
        String regEmailText, regPasswordText;
        regEmailText = regEmail.getText().toString().trim();
        regPasswordText = regPassword.getText().toString().trim();
        if (regPasswordText.length() >= 6) {
            mAuth.createUserWithEmailAndPassword(regEmailText, regPasswordText)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegistrationActivity.this, "Succesfully Registered", Toast.LENGTH_SHORT).show();
                                mAuth.getCurrentUser().sendEmailVerification()
                                        .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(RegistrationActivity.this, "Email Verification Sent to :" + regEmail, Toast.LENGTH_SHORT).show();
                                                    Intent toLogin = new Intent(RegistrationActivity.this,MainActivity.class);
                                                    startActivity(toLogin);
                                                } else {
                                                    Toast.makeText(RegistrationActivity.this, "Failed to send Email Verification " + task.getException(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            } else {
                                Toast.makeText(RegistrationActivity.this, "Registration Failed " + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
        else
            Toast.makeText(RegistrationActivity.this,"Enter minimum 6 characters",Toast.LENGTH_SHORT).show();
    }

}