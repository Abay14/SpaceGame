package com.example.adygha.spacegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class ControlButtons extends Drawable{
    float upTopY; //координата верхней границы кнопки ВВЕРХ
    float upBottomY; //координата нижней границы кнопки ВНИЗ

    float downTopY; //коорд верхней границы кнопки ВНИЗ
    float downBottomY; //коорд нижней границы кнопки ВНИЗ

    float leftLeftX; //координата левой границы кнопки ВЛЕВО
    float leftRightX; //координата правой границы кнопки ВЛЕВО

    float rightLeftX; //координата левой границы кнопки ВПРАВО
    float rightRightX; //координата правой границы кнопки ВПРАВО

    public ControlButtons(Context context)
    {
        bitmapId=R.drawable.controls;
        x=3;
        y=35;
        size=7;

        init(context);

        computeButtonCoordinates();
    }

    void computeButtonCoordinates()
    {
        //выделяем координаты кнопок на общей кнопке управления
        float horizPixelsOfOnePiece = size / 3 * GameView.unitW;
        float vertPixelsOfOnePiece = size / 3 * GameView.unitW;

        upTopY = y * GameView.unitH;
        upBottomY = upTopY + vertPixelsOfOnePiece;

        downTopY = upBottomY + vertPixelsOfOnePiece;
        downBottomY = downTopY + vertPixelsOfOnePiece;

        leftLeftX = x * GameView.unitW;
        leftRightX = leftLeftX + horizPixelsOfOnePiece;

        rightLeftX = leftRightX + horizPixelsOfOnePiece;
        rightRightX = rightLeftX + horizPixelsOfOnePiece;
    }
}
