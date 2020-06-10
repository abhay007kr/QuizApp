package com.example.quizstudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {
        FirebaseFirestore db;
        ActivityQuestionBinding binding;
        int q=0;
         ArrayList<Integer> option1Selected = new ArrayList<>();
         ArrayList<Integer> option2Selected = new ArrayList<>();
         ArrayList<Integer> option3Selected = new ArrayList<>();
         ArrayList<Integer> option4Selected = new ArrayList<>();
         String correctAnswer ="";
         int noOfCorrect = 0;
         int noOfInCorrect = 0;
         boolean b1,b2,b3,b4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
       // setContentView(R.layout.activity_question);
        option1Selected.add(0);
        option2Selected.add(0);
        option3Selected.add(0);
        option4Selected.add(0);

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

        binding.option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n =q;
                 b1 = true; b2=false;b3=false;b4=false;
                if(b1){
                    binding.option1.setBackground(getResources().getDrawable(R.drawable.option_selected));
                    binding.option4.setBackground(getResources().getDrawable(R.drawable.question_button));
                    binding.option2.setBackground(getResources().getDrawable(R.drawable.question_button));
                    binding.option3.setBackground(getResources().getDrawable(R.drawable.question_button));
                    option1Selected.add(n);

                    binding.option4.setEnabled(true);
                    binding.option2.setEnabled(true);
                    binding.option3.setEnabled(true);
                    if(binding.option1.getText().toString().equals(correctAnswer) && b1)
                    {
                        noOfCorrect++;
                        Log.d("Correct ","Correct "+noOfCorrect);
                    }
                    else{
                        noOfInCorrect++;
                        Log.d("InCorrect ","InCorrect "+noOfInCorrect);
                    }
                    binding.option1.setEnabled(false);}
                else
                {

                }

            }
        });

        binding.option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n =q;
                option2Selected.add(n);
                b1 = false; b2=true;b3=false;b4=false;
                if(b2){
                    binding.option2.setBackground(getResources().getDrawable(R.drawable.option_selected));
                    binding.option1.setBackground(getResources().getDrawable(R.drawable.question_button));
                    binding.option4.setBackground(getResources().getDrawable(R.drawable.question_button));
                    binding.option3.setBackground(getResources().getDrawable(R.drawable.question_button));

                    binding.option1.setEnabled(true);
                    binding.option4.setEnabled(true);
                    binding.option3.setEnabled(true);
                    if(binding.option2.getText().toString().equals(correctAnswer) && b2)
                    {
                        noOfCorrect++;
                        Log.d("Correct ","Correct "+noOfCorrect);
                    }
                    else{
                        noOfInCorrect++;
                        Log.d("InCorrect ","InCorrect "+noOfInCorrect);
                    }
                    binding.option2.setEnabled(false);}
                else
                {

                }
            }
        });

        binding.option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n =q;
                b1 = false; b2=false;b3=true;b4=false;
                if(b3){
                    binding.option3.setBackground(getResources().getDrawable(R.drawable.option_selected));
                    binding.option1.setBackground(getResources().getDrawable(R.drawable.question_button));
                    binding.option2.setBackground(getResources().getDrawable(R.drawable.question_button));
                    binding.option4.setBackground(getResources().getDrawable(R.drawable.question_button));
                    option3Selected.add(n);

                    binding.option1.setEnabled(true);
                    binding.option2.setEnabled(true);
                    binding.option4.setEnabled(true);
                    if(binding.option3.getText().toString().equals(correctAnswer) && b3)
                    {
                        noOfCorrect++;
                        Log.d("Correct ","Correct "+noOfCorrect);
                    }
                    else{
                        noOfInCorrect++;
                        Log.d("InCorrect ","InCorrect "+noOfInCorrect);
                    }
                    binding.option3.setEnabled(false);}
                else
                {

                }

            }
        });

        binding.option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n =q;
                b1 = false; b2=false;b3=false;b4=true;
                if(b4){
                    binding.option4.setBackground(getResources().getDrawable(R.drawable.option_selected));
                    binding.option1.setBackground(getResources().getDrawable(R.drawable.question_button));
                    binding.option2.setBackground(getResources().getDrawable(R.drawable.question_button));
                    binding.option3.setBackground(getResources().getDrawable(R.drawable.question_button));
                    option4Selected.add(n);

                    binding.option1.setEnabled(true);
                    binding.option2.setEnabled(true);
                    binding.option3.setEnabled(true);
                    if(binding.option4.getText().toString().equals(correctAnswer) && b4)
                    {
                        noOfCorrect++;
                        Log.d("Correct ","Correct "+noOfCorrect);
                    }
                    else{
                        noOfInCorrect++;
                        Log.d("InCorrect ","InCorrect "+noOfInCorrect);
                    }
                    binding.option4.setEnabled(false);}
                else
                {

                }

            }
        });

    }
    void getNextQuestion()
    {
        setColorToOption();

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
                            correctAnswer = documentSnapshot.getString("correct");

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
        setColorToOption();
        /*binding.option1.setBackground(getResources().getDrawable(R.drawable.question_button));
        binding.option2.setBackground(getResources().getDrawable(R.drawable.question_button));
        binding.option3.setBackground(getResources().getDrawable(R.drawable.question_button));
        binding.option4.setBackground(getResources().getDrawable(R.drawable.question_button));*/
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
                            correctAnswer = documentSnapshot.getString("correct");
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    void setColorToOption()
    {
        if(option1Selected.contains(q))
            binding.option1.setBackground(getResources().getDrawable(R.drawable.option_selected));
        else
            binding.option1.setBackground(getResources().getDrawable(R.drawable.question_button));

        if(option2Selected.contains(q))
            binding.option2.setBackground(getResources().getDrawable(R.drawable.option_selected));
        else
            binding.option2.setBackground(getResources().getDrawable(R.drawable.question_button));

        if(option3Selected.contains(q))
            binding.option3.setBackground(getResources().getDrawable(R.drawable.option_selected));
        else
            binding.option3.setBackground(getResources().getDrawable(R.drawable.question_button));

        if(option4Selected.contains(q))
            binding.option4.setBackground(getResources().getDrawable(R.drawable.option_selected));
        else
            binding.option4.setBackground(getResources().getDrawable(R.drawable.question_button));

    }

}