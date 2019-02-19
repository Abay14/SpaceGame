package com.example.adygha.spacegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ParallaxBackground {
    Bitmap bitmap;
    Bitmap bitmapReversed;

    int width;
    int height;
    boolean reversedFirst;
    float speed;

    int xClip;
    int startY;
    int endY;

    ParallaxBackground(Context context, int screenWidth, int screenHeight, String bitmapName, int sY, int eY, float s){
         // Make a resource id out of the string of the file name
         int resID = context.getResources().getIdentifier(bitmapName,
            "drawable", context.getPackageName());

         // Load the bitmap using the id
//         bitmap = BitmapFactory.decodeResource(context.getResources(), resID);
    }

    public void update(long fps){

    }
}
