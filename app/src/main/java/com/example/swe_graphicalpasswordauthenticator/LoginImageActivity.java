package com.example.swe_graphicalpasswordauthenticator;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import java.io.FileInputStream;
import java.util.ArrayList;



import android.app.Activity;

import android.graphics.Bitmap;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class LoginImageActivity extends Activity {
    // this part is just declaring some variable. Pretty much self explanatory.
    private GridView imageGrid;
    private int clickCounter;
    private int click1;
    private int click2;
    private Bitmap temp;
    private int clickForSwaps = 0;




    public void onCreate(Bundle bundle) {

        super.onCreate(bundle);
        setContentView(R.layout.activity_login_image);// getting the entire layout. Check login_image.xml


        // adding the small images to the bitmap arraylist
        ArrayList<Bitmap> smallImage = new ArrayList<>();
        int num = getIntent().getIntExtra("num", 3);

        String firstLog = getIntent().getStringExtra("log1"); //get the concatenated string from the first login into firstReg


        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                Bitmap bmp = null;
                String filename = getIntent().getStringExtra("image" + i + "" + j);
                try {
                    FileInputStream is = this.openFileInput(filename);
                    bmp = BitmapFactory.decodeStream(is);
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                smallImage.add(bmp);
            }
        }

        imageGrid = (GridView) findViewById(R.id.gridView); //getting the layout of the gridview.

        imageGrid.setAdapter(new SplitImageAdapter(this, smallImage)); // setting the Adapter to the small images to get the ids,items etc.

        imageGrid.setNumColumns((int) Math.sqrt(smallImage.size())); // setting the the number of column to the size of the arraylist.


        imageGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // when a tile is clicked.
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                clickCounter += 1;  /* since we can only swap 2 images at a time, we use this clickCounter
                     to count only the first and second clicks, then it gets reset after the swap of the first clicked image and the second clicked image*/

                clickForSwaps++; /* since the clickCounter gets reset at every 2 clicks ( after every swap),
                     we need a counter that keeps the click counts and doesn't get reset, thus having the clickForSwaps.
                     we increment it by one at every click, however, 1 swap is equal to 2 clickForSwap (2 clicks)*/

                if (clickForSwaps > 6) { // check if we have reached 6 clicks, which is 3 swap (each swap is 2 clicks so 2x3=6)
                    disableSwapping(); // this function returns false, disabling the swapping
                    Toast.makeText(getApplicationContext(), "Maximum swapping reached (3).", Toast.LENGTH_SHORT).show();

                } else if (clickCounter == 1) { // check if we have click a tile for the first time
                    click1 = position; // store the position (id) of the first click in click1
                    temp = smallImage.get(position); // get the item (small image ) at that position and store it in temporary variable

                } else if (clickCounter == 2) { // check if we have click another tile for the second time
                    click2 = position; // store the position (id) of the second click in click2
                    smallImage.set(click1, smallImage.get(click2)); // get the item (small image) at the position of click2 and set it at position of click1
                    smallImage.set(click2, temp); // set the position of click2 with the item (small image) previously stored in the temp

                    String firstID = Integer.toString(click1); // assign id for click 1 to firstID as a string
                    String secondID = Integer.toString(click2); // assign id for click 2 to secondID as a string
                    String sequence = firstID + "-" + secondID + ","; // concatenate firstID and secondID and assign it to sequence
                    if(clickForSwaps == 6) {
                        storeImagePasswd(firstLog + sequence); // concatenate the first login string to the image string
                    }

                    imageGrid.invalidateViews(); //invalidate view to refresh gridview with the new gridview after each swap


                    clickCounter = 0; // reset the clickCounter to 0 and start over again for the second swap. Continue swapping untill we reach the max swap

                }

            }

            public boolean disableSwapping() { // boolean function for disabling the swapping
                return false;


            }

        });


    }

    public void storeImagePasswd(String sequence) {
        DatabaseHelper db = new DatabaseHelper(this);
        //String imagePass = sequence;
        Button login = findViewById(R.id.btn_login2); // btn_login1 is the id for the button (LOGIN) . Check register.xml file
        Boolean userExists = db.checkImagePasswd(sequence);
        if (userExists == true) {

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LoginImageActivity.this, SuccessScreenActivity.class); // going from Register page to RegisterImageSplit page
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


                    Toast.makeText(LoginImageActivity.this, "Access Granted ", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }


            });
        } else {
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LoginImageActivity.this, MainActivity.class); // going from Register page to RegisterImageSplit page
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


                    Toast.makeText(LoginImageActivity.this, "Access Denied", Toast.LENGTH_SHORT).show();
                    startActivity(intent); // duplicate checked and registration successful, now we move to the image password screen
                }


            });
        }
    }





}

