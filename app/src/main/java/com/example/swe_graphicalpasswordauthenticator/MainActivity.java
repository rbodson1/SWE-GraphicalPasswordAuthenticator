/*
   * This is the main screen. No need to modify this file.
   * We can go to the login and register page from here.
 */

package com.example.swe_graphicalpasswordauthenticator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // getting the entire main page layout. Check main.xml for layout
       initLoginButton();   //calling the function
       initRegisterButton();  //calling the function
    }

    // Initializing the login button to go to the Login page (login activity)
    private void initLoginButton() {
        Button login = findViewById(R.id.btn_mainLogin); // btn_mainLogin is the id for the button (LOGIN) . Check main.xml file for layout
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);// going from main page to login page
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent); // starting the intent, meaning going to the login page when the button is clicked
            }
        });
    }
    // Initializing the register button to go to the register page (register activity)
    private void initRegisterButton() {
        Button register = findViewById(R.id.btn_mainRegister); // btn_mainRegister is the id for the button (REGISTER) . Check main.xml file for layout
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);// going from main page to register page
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent); // starting the intent, meaning going to the register page when the button is clicked
            }
        });
    }

}