package com.example.quizstudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button login,toRegisterActivity , fake;
    EditText loginEmail,loginPassword;
    TextView forgotPassword;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toPasswordReset = new Intent(MainActivity.this,PasswordResetActivity.class);
                startActivity(toPasswordReset);
            }
        });

        toRegisterActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toRegister = new Intent(MainActivity.this,RegistrationActivity.class);
                startActivity(toRegister);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        //To remove Later
        fake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AttemptActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initView()
    {
        login = findViewById(R.id.login);
        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        toRegisterActivity = findViewById(R.id.to_register);
        mAuth = FirebaseAuth.getInstance();
        forgotPassword = findViewById(R.id.forgot_password);
        fake = findViewById(R.id.fake);
    }

    void signIn()
    {
        String loginEmailText,loginEmailPassword;
        loginEmailPassword=loginPassword.getText().toString();
        loginEmailText = loginEmail.getText().toString().trim();
        mAuth.signInWithEmailAndPassword(loginEmailText,loginEmailPassword)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            if(mAuth.getCurrentUser().isEmailVerified()){
                                Intent intent = new Intent(MainActivity.this,AttemptActivity.class);
                               startActivity(intent);
                                }
                            else
                                Toast.makeText(MainActivity.this,"Please verify your Email ",Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(MainActivity.this,"Failed to Authenticate",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

