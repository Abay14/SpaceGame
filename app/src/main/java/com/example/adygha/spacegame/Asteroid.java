package com.example.adygha.spacegame;

import android.content.Context;

import java.util.Random;

public class Asteroid extends SpaceBody {
    public Asteroid(Context context) {
        Random random = new Random();

        bitmapId = R.drawable.asteroid;
        size = 2;
        y=0;
        x = random.nextInt(GameView.maxX);
        x = x < GameView.maxX - size ? x : x - size ;

        speed = minSpeed + (maxSpeed - minSpeed) * random.nextFloat();

        init(context);
    }

    @Override
    public void update() {
        y += speed;
    }

    public boolean isCollision(float shipX, float shipY, float shipSize) {
        return !(((x+size) < shipX) || (x > (shipX+shipSize)) || ((y+size) < shipY) || (y > (shipY+shipSize)));
    }

    public static void increaseSpeed(float value)
    {
        minSpeed += value;
        maxSpeed += value;
    }

    private static float minSpeed = 0.1f; // минимальная скорость
    private static float maxSpeed = 0.5f; // максимальная скорость
}
