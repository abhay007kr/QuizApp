package com.example.quizstudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.quizstudent.databinding.ActivityAttemptBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AttemptActivity extends AppCompatActivity {
        FirebaseAuth mAuth;
        ActivityAttemptBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAttemptBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        // setContentView(R.layout.activity_attempt);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            binding.userId.setText(email+"");
        }

        binding.attemptQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),QuestionActivity.class);
                startActivity(intent);
            }
        });
    }
}