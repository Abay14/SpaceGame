package com.example.adygha.spacegame;

import android.content.Context;

public class Ship extends SpaceBody {
    public Ship(Context context) {
        bitmapId = R.drawable.starship; // определяем начальные параметры

        size = 5;
        x=10;
        y=GameView.maxY - size - 1;
        speed = (float) 0.2;

        init(context); // инициализируем корабль
    }

    @Override
    public void update() { // перемещаем корабль в зависимости от нажатой кнопки
        if(GameActivity.isLeftPressed && x >= 0){
            x -= speed;
        }
        if(GameActivity.isRightPressed && x <= GameView.maxX - size){
            x += speed;
        }
        if(GameActivity.isUpPressed && y>=0) //проверяем, была ли нажата кнопка вверх и перемещаем корабль вверх только если он не достиг верхнего края
        {
            y -= speed;
        }
        if(GameActivity.isDownPressed && y<= GameView.maxY - size) //точно так же, спускаем корабль, если есть куда его еще спустить
        {
            y += speed;
        }
    }
}
