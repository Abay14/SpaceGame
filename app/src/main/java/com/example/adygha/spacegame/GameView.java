package com.example.adygha.spacegame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class GameView extends SurfaceView implements Runnable{

    private final String TAG="SPACE_GAME";

    private ArrayList<Asteroid> asteroids = new ArrayList<>(); // тут будут харанится астероиды
    private final int ASTEROID_INTERVAL = 50; // время через которое появляются астероиды (в итерациях)
    private int currentTime = 0;

    public static int maxX = 20; // размер по горизонтали
    public static int maxY = 28; // размер по вертикали
    public static float unitW = 0; // пикселей в юните по горизонтали
    public static float unitH = 0; // пикселей в юните по вертикали
    private boolean firstTime = true;
    private boolean gameRunning = true;
    private Ship ship;
    private Thread gameThread = null;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    private AlertDialog gameoverDialog=null;

    public GameView(Context context) {
        super(context);

        //инициализируем обьекты для рисования
        surfaceHolder = getHolder();
        paint = new Paint();

        // инициализируем поток
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run()
    {
        while (gameRunning)
        {
            update();
            draw();
            checkCollision();
            checkIfNewAsteroid();
            control();
        }

        firstTime=true;

        Activity activity = (Activity) getContext();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(gameoverDialog==null)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("GAME OVER!");
                    builder.setPositiveButton(R.string.play_again, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.d(TAG, "play again clicked");
                            //clear asteroids
                            asteroids.clear();
                        }
                    });
                    builder.setNegativeButton(R.string.return_to_main, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.d(TAG, "cancel clicked");
                            //return to main activity
                            ((Activity) getContext()).finish();
                        }
                    });
                    gameoverDialog = builder.create();
                }

                gameoverDialog.show();
            }
        });

    }

    private void update() {
        if(!firstTime) {
            ship.update();
            for (Asteroid asteroid : asteroids) {
                asteroid.update();
            }
        }
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {  //проверяем валидный ли surface

            if(firstTime){ // инициализация при первом запуске
                firstTime = false;
                unitW = surfaceHolder.getSurfaceFrame().width()/maxX; // вычисляем число пикселей в юните
                unitH = surfaceHolder.getSurfaceFrame().height()/maxY;

                ship = new Ship(getContext()); // добавляем корабль
            }

            canvas = surfaceHolder.lockCanvas(); // закрываем canvas
            canvas.drawColor(Color.YELLOW); // заполняем фон чёрным

            ship.drow(paint, canvas); // рисуем корабль

            for(Asteroid asteroid: asteroids){ // рисуем астероиды
                asteroid.drow(paint, canvas);
            }

            surfaceHolder.unlockCanvasAndPost(canvas); // открываем canvas
        }
    }

    private void control() { // пауза на 17 миллисекунд
        try {
            gameThread.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkCollision(){ // перебираем все астероиды и проверяем не касается ли один из них корабля
        for (Asteroid asteroid : asteroids) {
            if(asteroid.isCollision(ship.x, ship.y, ship.size)){
                // игрок проиграл
                gameRunning = false; // останавливаем игру
                // TODO добавить анимацию взрыва
            }
        }
    }

    private void checkIfNewAsteroid(){ // каждые 50 итераций добавляем новый астероид
        if(currentTime >= ASTEROID_INTERVAL){
            Asteroid asteroid = new Asteroid(getContext());
            asteroids.add(asteroid);
            currentTime = 0;
        }else{
            currentTime ++;
        }
    }
}
