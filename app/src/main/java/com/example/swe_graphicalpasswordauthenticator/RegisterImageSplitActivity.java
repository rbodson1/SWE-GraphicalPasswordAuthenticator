/* DO NOT MODIFY THIS FILE
  *The file for splitting the large image in the ImageView into small images.
  * This page only exists because I am using the bitmap arraylist to store the small image split from the large image in ImageView
     - this page prevented me from manually splitting the image, which would've been 16 small images in the drawable (that's a lot of work).
       I did try that but its inefficient and time consuming because we'd have to do the same for every image that we want to use.
       However, the upside is that we could directly put the small image in the GridView using an array ( arr[] ) without needing an extra page for splitting the image.
  * In this page, the image is put in the ImageView, then split into 16 small images (4x4) programmatically, which can then be put in the GridView.
 */

package com.example.swe_graphicalpasswordauthenticator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;

public class RegisterImageSplitActivity extends AppCompatActivity {

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_imageview_split); // getting the entire page layout. Check imageview_split.xml
        Button split = (Button) findViewById(R.id.btn_split_image); // btn_split_image is the id for "Click here to split Image" button. Check imageview_split.xml
        assert split != null;
        split.setOnClickListener(small_listner);

    }



    public OnClickListener small_listner = new OnClickListener() {

        public void onClick(View v) {

            ImageView image = (ImageView) findViewById(R.id.source_image);

            // The number of blocks (small images that we want ). It has to be a perfect square
            int numberOfBlocks = 16; // 16 = 4 x 4
            splitImage(image,numberOfBlocks); // call the function splitImage to split the image using the number of blocks
        }

    };


    private void splitImage(ImageView image, int numberOfBlocks) {

        int rows,cols;
        int smallimage_Height,smallimage_Width;

        BitmapDrawable mydrawable = (BitmapDrawable) image.getDrawable();
        Bitmap bitmap = mydrawable.getBitmap();
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);

        rows = cols = (int) Math.sqrt(numberOfBlocks);
        smallimage_Height = bitmap.getHeight()/rows;
        smallimage_Width = bitmap.getWidth()/cols;


        int yCo = 0;
        Intent intent = new Intent(RegisterImageSplitActivity.this, RegisterImageActivity.class); // going from register image split page to register image page

        // all this is doing is split the image into small images, it's hard to explain the logic of, so just go with it.
        for(int x=0; x<rows; x++){
            int xCo = 0;
            for(int y=0; y<cols; y++){
                try {
                    Bitmap bmp =Bitmap.createBitmap(scaledBitmap, xCo, yCo, smallimage_Width, smallimage_Height);
                    String filename = "bitmap"+x+""+y+".png";
                    FileOutputStream stream = this.openFileOutput(filename, Context.MODE_PRIVATE);
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);

                    stream.close();
                    bmp.recycle();

                    intent.putExtra("image"+x+""+y, filename);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                xCo += smallimage_Width;
            }
            yCo+= smallimage_Height;
        }
        intent.putExtra("num",rows);

         /*Starting the button intent "Click here to split image" button. This does two things :
           - splits the image into small images
           - go to the login image page
        */
        startActivity(intent);

    }

}

