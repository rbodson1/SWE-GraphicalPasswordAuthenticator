/*
 * This is the first register page with the string input-type for username and the password.
 * This is the page where we will register(save) the string username and password in the database.
 */

package com.example.swe_graphicalpasswordauthenticator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register); // getting the entire page layout. Check register.xml

        initRegister1Button(); //calling the function
    }

    //Initializing the register page to go to the register image splitting page
    private void initRegister1Button() {
        Button register = findViewById(R.id.btn_register1); // btn_login1 is the id for the button (LOGIN) . Check register.xml file
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) { //reaction when we click the REGISTER button
                Intent intent = new Intent(RegisterActivity.this, RegisterImageSplitActivity.class); // going from Register page to RegisterImageSplit page
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent); // starting the intent. Three things will happen here when the button is clicked, check for username duplicate, if no none, then register(save) go to RegisterImageSplit. (database not implemented yet)
            }
        });
    }
}