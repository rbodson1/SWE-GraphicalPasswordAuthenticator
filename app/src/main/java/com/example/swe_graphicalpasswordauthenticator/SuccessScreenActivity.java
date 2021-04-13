package com.example.swe_graphicalpasswordauthenticator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SuccessScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_screen);
        initHomeButton();
    }

    private void initHomeButton() {
        Button login = findViewById(R.id.homepage); // btn_mainLogin is the id for the button (LOGIN) . Check main.xml file for layout
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(SuccessScreenActivity.this, MainActivity.class);// going from main page to login page
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent); // starting the intent, meaning going to the login page when the button is clicked
            }
        });
    }
}
