package com.example.swe_graphicalpasswordauthenticator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class RegisterActivity extends AppCompatActivity {
    private ScramblerBoardView boardView;
    private Bitmap imageBitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // This code programmatically adds the ScramblerBoardView to the UI.
        RelativeLayout container = (RelativeLayout) findViewById(R.id.scrambler_container);
        boardView = new ScramblerBoardView(this);
        // boardView is the where the image resides, then it is put in the relativeLayout which then appears on the screen
        boardView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        container.addView(boardView);

      initHomeButton(); //calling the function.
    }

    // Initializing the Home button to go to the Home page (Main activity)
    private void initHomeButton() {
        Button login = findViewById(R.id.goHome);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    // This is the button function that displays and resets the image when clicked on
    public void displayResetImage(View view) {
        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.spongebob); // the image is decode into bitmap
        boardView.initialize(imageBitmap); // the boardView is initialized with the image. The initialize function is in the ScramblerBoardView file
        boardView.displayReset(); // the boardView is then linked to the function displayReset() which is in the ScramblerBoardView file
    }



}