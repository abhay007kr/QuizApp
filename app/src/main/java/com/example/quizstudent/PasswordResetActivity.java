package com.example.quizstudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordResetActivity extends AppCompatActivity {
    private EditText resetEmail;
    private Button resetPassword,toLoginActivity;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        initView();

        toLoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toLogin = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(toLogin);
            }
        });

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordReset();
            }
        });
    }

    private void initView(){
        resetEmail = findViewById(R.id.reset_email);
        resetPassword = findViewById(R.id.reset_password);
        mAuth = FirebaseAuth.getInstance();
        toLoginActivity = findViewById(R.id.to_login);
    }

    private void passwordReset()
    {
        final String resetEmailText = resetEmail.getText().toString().trim();

        if(TextUtils.isEmpty(resetEmailText))
        {
            Toast.makeText(getApplicationContext(),"Please enter registered Email",Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.sendPasswordResetEmail(resetEmailText)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                            Toast.makeText(getApplicationContext(),"We have sent you reset instructions to "+resetEmailText,Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getApplicationContext(),"Failed to send Instructions",Toast.LENGTH_SHORT).show();
                    }
                });
    }

}