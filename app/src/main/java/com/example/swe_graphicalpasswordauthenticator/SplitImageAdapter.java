/* DO NOT MODIFY THIS FILE
* The adapter for the dimensions, ids (position), items for the split image (small images).
* The file extends BaseAdapter ( The adapter for the GridView layout), which is an in-built android os file for the GridView. It can not be modified.
   - To view the content of the file, click on the search icon on the upper right corner of the screen and type in BaseAdapter.
 */

package com.example.swe_graphicalpasswordauthenticator;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;


public class SplitImageAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Bitmap> smallimages;
    private int imageWidth, imageHeight;

    public SplitImageAdapter(Context c, ArrayList<Bitmap> images){ // using arraylist of bitmap to store the small images, which is then pass to the GridView.

        mContext = c;

        smallimages = images; // initializing the arraylist of bitmap

        imageWidth = images.get(0).getWidth(); // initializing the width of each small image

        imageHeight = images.get(0).getHeight(); // initializing the length of each small image


    }


    public int getCount() {

        return smallimages.size(); // getting the size of the arraylist of bitmap.

    }

    public Object getItem(int position) {

        return smallimages.get(position); // getting the item (the small image)

    }

    public long getItemId(int position) {

        return position; // getting the id (position) of a small image

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView image;

        // Only modify this part of the code if necessary. You can play around with the layout parameters and padding to see the effects they have on the images
        if(convertView == null){
            image = new ImageView(mContext);
            image.setLayoutParams(new GridView.LayoutParams(imageWidth + 150 , imageHeight +250 )); // the width and height of each image (small image)
            image.setPadding(20, 20, 20, 20); // the padding of each small image
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);

        }else{
            image = (ImageView) convertView; // convert the view from one large image in the ImageView frame to small images
        }
        image.setImageBitmap(smallimages.get(position)); // get the images in each position and set them as bitmaps.
        return image;

    }

}


