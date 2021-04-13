/*
 * This is the first login page with the string input-type for username and the password.
 * This is the page where we will have the first database validation with the string username and password.
 */

package com.example.swe_graphicalpasswordauthenticator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText username;
    EditText password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // getting the entire page layout. Check login.xml

       //Initializing the login page to go to the Login image splitting page
        db = new DatabaseHelper(this);
        username = (EditText)findViewById(R.id.userLogin); // find the textfield id for username
        password = (EditText)findViewById(R.id.passwdLogin); // find the textfield id for password
        login = findViewById(R.id.btn_login1); // btn_login1 is the id for the button (LOGIN) . Check login.xml file

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) { //reaction when we click the LOGIN button
                //Intent intent = new Intent(LoginActivity.this, LoginImageSplitActivity.class); // going from Login page to LoginImageSplit page
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                String firstLog = user + pass; // concatenate user and pass into one string called firstLog
                Boolean userExists = db.checkUser(user, pass);

                if (userExists == true) {
                    Intent intent = new Intent(LoginActivity.this, LoginImageSplitActivity.class); // going from Login page to LoginImageSplit page
                    intent.putExtra("firstLog",firstLog); // carry the string into the designated screen(activity)
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}