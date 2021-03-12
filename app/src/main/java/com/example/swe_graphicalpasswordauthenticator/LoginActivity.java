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

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // getting the entire page layout. Check login.xml

        initLogin1Button(); // calling the function
    }

    //Initializing the login page to go to the Login image splitting page
    private void initLogin1Button() {
        Button register = findViewById(R.id.btn_login1); // btn_login1 is the id for the button (LOGIN) . Check login.xml file
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) { //reaction when we click the LOGIN button
                Intent intent = new Intent(LoginActivity.this, LoginImageSplitActivity.class); // going from Login page to LoginImageSplit page
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent); // starting the intent. Two things will happen here when the button is clicked, check for database validation then go to LoginImageSplit if authorized. (database not implemented yet)
            }
        });
    }
}