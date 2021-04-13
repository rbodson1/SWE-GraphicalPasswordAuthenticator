/*
 * This is the first register page with the string input-type for username and the password.
 * This is the page where we will register(save) the string username and password in the database.
 */

package com.example.swe_graphicalpasswordauthenticator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText username;
    EditText password;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register); // getting the entire page layout. Check register.xml


        //Initializing the register page to go to the register image splitting page

         db = new DatabaseHelper(this);
         username = (EditText)findViewById(R.id.userRegister); // find the textfield id for username
         password = (EditText)findViewById(R.id.passwdRegister); // find the textfield id for password
         register = findViewById(R.id.btn_register1); // btn_login1 is the id for the button (LOGIN) . Check register.xml file

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, RegisterImageSplitActivity.class); // going from Register page to RegisterImageSplit page
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                String firstReg = user + pass; // concatenate user and pass into one string called firstReg

                Boolean alreadyExists = db.checkUsername(user); // check for existence (duplicate)

                if (alreadyExists == true) { // if user already exists
                    Toast.makeText(RegisterActivity.this, "User already exists, use a different user name", Toast.LENGTH_SHORT).show();

                } else if(username.length() == 0 || password.length() == 0) { // if there is no input
                    Toast.makeText(RegisterActivity.this, "Username and Password required", Toast.LENGTH_SHORT).show();
                    Intent remainOnSameScreen = new Intent(RegisterActivity.this, RegisterActivity.class); // going from Register page to RegisterImageSplit page
                    startActivity(remainOnSameScreen);

                } else {
                    long val = db.addUser(user, pass); // add value to the database
                    if (val > 0) { // check if username and password are inputted
                        Toast.makeText(RegisterActivity.this, "First Registration successfully", Toast.LENGTH_SHORT).show();
                        intent.putExtra("firstReg",firstReg); // carry the string into the designated screen(activity)
                        startActivity(intent); // duplicate checked and registration successful, now we move to the image password screen
                    }
                }
            }
        });
    }
}

