package com.example.weaponz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Patterns;

public class UpdateActivity extends AppCompatActivity {

    MyDatabaseHelper databaseHelper;
    EditText editUsername, editPhone, editPassword, editEmail;
    String userEmail;

    private static final String SHARED_PREFS_NAME = "mypref";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        databaseHelper = new MyDatabaseHelper(this);

        // Retrieve the user's email from the intent
        userEmail = getIntent().getStringExtra("USER_EMAIL");

        // Initialize EditTexts
        editUsername = findViewById(R.id.update_username);
        editPhone = findViewById(R.id.update_phone);
        editPassword = findViewById(R.id.update_password);
        editEmail = findViewById(R.id.update_email);

        // Pre-fill the email field with the user's email and make it non-editable
        editEmail.setText(userEmail);
        editEmail.setFocusable(false);
        editEmail.setEnabled(false); // Ensure the email field is not editable

        Button buttonUpdate = findViewById(R.id.update_button);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editUsername.getText().toString();
                String phone = editPhone.getText().toString();
                String password = editPassword.getText().toString();

                // Check if all fields are filled
                if (username.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                    Toast.makeText(UpdateActivity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(username.length() > 15 || password.length() > 15){
                    Toast.makeText(UpdateActivity.this, "Username and password must not exceed 15 characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(!phone.matches("^05\\d{8}$")){
                    Toast.makeText(UpdateActivity.this, "Please enter a valid Israeli phone number", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches() || userEmail.length() > 30){
                    Toast.makeText(UpdateActivity.this, "Please enter a valid Gmail address not exceeding 30 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Now call the update method from MyDatabaseHelper
                MyDatabaseHelper myDbHelper = new MyDatabaseHelper(UpdateActivity.this);
                boolean isUpdated = myDbHelper.updateUserData(username, userEmail, phone, password);

                if(isUpdated) {

                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_EMAIL, userEmail);
                    editor.putString(KEY_USERNAME, username);
                    editor.apply();

                    Toast.makeText(UpdateActivity.this, "User data updated successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
