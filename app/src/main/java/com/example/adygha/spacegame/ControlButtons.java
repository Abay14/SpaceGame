package com.example.adygha.spacegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class ControlButtons {
    protected float x; // координаты
    protected float y;
    protected float size;
    protected int bitmapId; //id картинки
    protected Bitmap bitmap; //управление
    protected Paint alphaPaint; //особая прозрачная кисть

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

        alphaPaint=new Paint();
        alphaPaint.setAlpha(35);

        computeButtonCoordinates();
    }

    void init(Context context) { // сжимаем картинку до нужных размеров
        Bitmap cBitmap = BitmapFactory.decodeResource(context.getResources(), bitmapId);
        bitmap = Bitmap.createScaledBitmap(
                cBitmap, (int)(size * GameView.unitW), (int)(size * GameView.unitH), false);
        cBitmap.recycle();
    }

    void drow(Paint paint, Canvas canvas){ // рисуем картинку
        canvas.drawBitmap(bitmap, x*GameView.unitW, y*GameView.unitH, alphaPaint);
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
