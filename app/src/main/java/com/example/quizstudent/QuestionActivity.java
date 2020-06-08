package com.example.quizstudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.quizstudent.databinding.ActivityAttemptBinding;
import com.example.quizstudent.databinding.ActivityQuestionBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class QuestionActivity extends AppCompatActivity {
        FirebaseFirestore db;
        ActivityQuestionBinding binding;
        int q=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
       // setContentView(R.layout.activity_question);

        db=FirebaseFirestore.getInstance();

        binding.nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(q==0)
                    binding.nextQuestion.setText("Start");
                else
                    binding.nextQuestion.setText("Next");
                q++;
                getNextQuestion();
            }
        });

        binding.previousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(q>0){ q--; getPreviousQuestion();}
               else
               {binding.previousQuestion.setEnabled(false);}
            }
        });

    }
    void getNextQuestion()
    {
        db.collection("Questions")
                .whereEqualTo("questionNo",String.valueOf(q))
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(QueryDocumentSnapshot documentSnapshot : task.getResult())
                        {
                            String question = documentSnapshot.getString("question");
                            binding.getQuestion.setText(question);
                            String option1 = documentSnapshot.getString("option1");
                            binding.option1.setText(option1);
                            String option2 = documentSnapshot.getString("option2");
                            binding.option2.setText(option2);
                            String option3 = documentSnapshot.getString("option3");
                            binding.option3.setText(option3);
                            String option4 = documentSnapshot.getString("option4");
                            binding.option4.setText(option4);
                            String correctAnswer = documentSnapshot.getString("correct");

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    void getPreviousQuestion()
    {
        db.collection("Questions")
                .whereEqualTo("questionNo",String.valueOf(q))
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(QueryDocumentSnapshot documentSnapshot : task.getResult())
                        {
                            String question = documentSnapshot.getString("question");
                            binding.getQuestion.setText(question);
                            String option1 = documentSnapshot.getString("option1");
                            binding.option1.setText(option1);
                            String option2 = documentSnapshot.getString("option2");
                            binding.option2.setText(option2);
                            String option3 = documentSnapshot.getString("option3");
                            binding.option3.setText(option3);
                            String option4 = documentSnapshot.getString("option4");
                            binding.option4.setText(option4);

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}