package com.example.weaponz;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Patterns;
import android.content.SharedPreferences;
import android.view.View;
import android.os.Bundle;
import android.widget.Toast;
import android.content.Intent;
import com.example.weaponz.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    MyDatabaseHelper databaseHelper;

    private static final String SHARED_PREFS_NAME = "mypref";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseHelper = new MyDatabaseHelper(this);

        binding.signupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String user = binding.signupUsername.getText().toString();
                String email = binding.signupEmail.getText().toString();
                String phone = binding.signupPhone.getText().toString();
                String pass = binding.signupPassword.getText().toString();

                if(user.isEmpty() || email.isEmpty() || phone.isEmpty() || pass.isEmpty()){
                    Toast.makeText(SignupActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else if(user.length() < 3 || pass.length() < 3){
                    Toast.makeText(SignupActivity.this, "Username and password must not be less than 3 characters", Toast.LENGTH_SHORT).show();
                }
                else if(user.length() > 15 || pass.length() > 15){
                    Toast.makeText(SignupActivity.this, "Username and password must not exceed 15 characters", Toast.LENGTH_SHORT).show();
                }
                else if(!phone.matches("^05\\d{8}$")){
                    Toast.makeText(SignupActivity.this, "Please enter a valid Israeli phone number", Toast.LENGTH_SHORT).show();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches() || email.length() > 30){
                    Toast.makeText(SignupActivity.this, "Please enter a valid Gmail address not exceeding 30 characters", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(!databaseHelper.checkUserExists(email)){
                        boolean insert = databaseHelper.insertData(user, email, phone, pass);
                        if(insert){
                            Intent intent = new Intent(getApplicationContext(), SigncheckActivity.class);
                            intent.putExtra("USER_EMAIL", email); // Pass the email to SigncheckActivity
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(KEY_EMAIL, email);
                            editor.putString(KEY_USERNAME, user);
                            editor.apply();
                        }
                        else {
                            Toast.makeText(SignupActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignupActivity.this, "User with that email already exists!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        // Create an intent that defines the target activity
        Intent intent = new Intent(this, IntroActivity.class);
        startActivity(intent);
        // Apply the slide-in-left and slide-out-right animations
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        // Finish the current activity
        finish();
        return true;
    }
}