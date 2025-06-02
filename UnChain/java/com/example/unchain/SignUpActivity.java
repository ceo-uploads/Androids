package com.example.unchain;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.unchain.Model.user;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        TextView goToLogin = findViewById(R.id.loginText);
        Button signUpBtn = findViewById(R.id.loginBtn3);
        TextView phone_email = findViewById(R.id.phone_email);
        TextView password = findViewById(R.id.password);
        TextView confirm_pass = findViewById(R.id.confirm_pass);
        TextView profile_name = findViewById(R.id.profile_name);

        goToLogin.setOnClickListener(view -> {
            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
        });

        signUpBtn.setOnClickListener(view -> {

            String name = profile_name.getText().toString();
            String email_or_phone = phone_email.getText().toString();
            String pass = password.getText().toString();

            if (!phone_email.getText().toString().isEmpty() &&
                    !password.getText().toString().isEmpty() &&
                    !confirm_pass.getText().toString().isEmpty() &&
                    confirm_pass.getText().toString().equals(password.getText().toString())) {

                auth.createUserWithEmailAndPassword(email_or_phone, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    user User = new user(email_or_phone, pass, name);
                                    String userId = Objects.requireNonNull(auth.getCurrentUser()).getUid();
                                    database.getReference("Users").child(userId).setValue(User)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                                                    } else {
                                                        Toast.makeText(SignUpActivity.this, "Database write failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(SignUpActivity.this, "Database write failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                } else {
                                    Toast.makeText(SignUpActivity.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

    }
}