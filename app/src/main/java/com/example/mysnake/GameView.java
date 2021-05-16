package com.example.mysnake;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View {
    public static int sizeOfMap = 75 * Constants.SCREEN_WIDTH/1080;
    private ArrayList<Grass> arrGrass = new ArrayList<>();
    private Snake snake;
    private Bitmap bmGrass1, bmGrass2, bmSnake, bmApple;
    private int h = 21, w = 18;
    private boolean move = false;
    private float mx, my;
    private Handler handler;
    private Runnable runnable;
    private Apple apple;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //Where grass 1 locate
        bmGrass1 = BitmapFactory.decodeResource(this.getResources(),R.drawable.grass);
        bmGrass1 = Bitmap.createScaledBitmap(bmGrass1,sizeOfMap,sizeOfMap,true);

        //Where grass 2 locate
        bmGrass2 = BitmapFactory.decodeResource(this.getResources(),R.drawable.grass03);
        bmGrass2 = Bitmap.createScaledBitmap(bmGrass2,sizeOfMap,sizeOfMap,true);
        //Where Snake locate
        bmSnake = BitmapFactory.decodeResource(this.getResources(),R.drawable.snake1);
        bmSnake = Bitmap.createScaledBitmap(bmSnake,14*sizeOfMap,sizeOfMap,true);
        //Where Apple?
        bmApple = BitmapFactory.decodeResource(this.getResources(),R.drawable.apple);
        bmApple = Bitmap.createScaledBitmap(bmApple,14*sizeOfMap,sizeOfMap,true);

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if((i+j)%2 == 0){
                    arrGrass.add(new Grass(bmGrass1,j*sizeOfMap + Constants.SCREEN_WIDTH/2 - (w/2)*sizeOfMap,
                            i*sizeOfMap+100* Constants.SCREEN_HEIGHT/1920,sizeOfMap,sizeOfMap));
                }
                else{
                    arrGrass.add(new Grass(bmGrass2,j*sizeOfMap + Constants.SCREEN_WIDTH/2 - (w/2)*sizeOfMap,
                            i*sizeOfMap+100* Constants.SCREEN_HEIGHT/1920,sizeOfMap,sizeOfMap));
                }
            }
        }
        snake = new Snake(bmSnake,arrGrass.get(126).getX(),arrGrass.get(126).getY(),4);
        apple = new Apple(bmApple,arrGrass.get(randApple()[0]).getX(),arrGrass.get(randApple()[1]).getY());
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int a = event.getActionMasked();
        switch (a){
            case MotionEvent.ACTION_MOVE:{
                if(move == false){
                    mx = event.getX();
                    my = event.getY();
                    move = true;
                }
                else{
                    //Snake can't turn back -> !snake.isMove....();
                    if(mx - event.getX() > 100*Constants.SCREEN_WIDTH/1080 && !snake.isMoveRight()){
                        mx = event.getX();
                        my = event.getY();
                        snake.setMoveLeft(true);
                    }
                    else if(event.getX() - mx > 100*Constants.SCREEN_WIDTH/1080 && !snake.isMoveLeft()){
                        mx = event.getX();
                        my = event.getY();
                        snake.setMoveRight(true);
                    }
                    else if(my - event.getY() > 100*Constants.SCREEN_WIDTH/1080 && !snake.isMoveDown()){
                        mx = event.getX();
                        my = event.getY();
                        snake.setMoveUp(true);
                    }
                    else if(event.getY() - my > 100*Constants.SCREEN_WIDTH/1080 && !snake.isMoveUp()){
                        mx = event.getX();
                        my = event.getY();
                        snake.setMoveDown(true);
                    }
                }
                break;
            }
            case MotionEvent.ACTION_UP:{
                mx = 0;
                my = 0;
                move = false;
                break;
            }
        }
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(0xFF1A_6100);
        for (int i = 0; i < arrGrass.size(); i++) {
            canvas.drawBitmap(arrGrass.get(i).getBm(),arrGrass.get(i).getX(),arrGrass.get(i).getY(),null);
        }
        snake.update();
        snake.draw(canvas);
        apple.draw(canvas);
        handler.postDelayed(runnable,50);
    }
    public int[] randApple(){
        int[]xy = new int[2];
        Random r = new Random();
        xy[0] = r.nextInt(arrGrass.size()-1);
        xy[1] = r.nextInt(arrGrass.size()-1);
        Rect rect = new Rect(arrGrass.get(xy[0]).getX(),arrGrass.get(xy[1]).getY(),arrGrass.get(xy[0]).getX()+sizeOfMap,arrGrass.get(xy[1]).getY()+sizeOfMap);
        boolean check = true;
        while (check){
            check = false;
            for(int i = 0; i < snake.getArrPartSnake().size();i++){
                if(rect.intersect(snake.getArrPartSnake().get(i).getRectBody())){
                    check = true;
                    xy[0] = r.nextInt(arrGrass.size()-1);
                    xy[1] = r.nextInt(arrGrass.size()-1);
                    rect = new Rect(arrGrass.get(xy[0]).getX(),arrGrass.get(xy[1]).getY(),arrGrass.get(xy[0]).getX()+sizeOfMap,arrGrass.get(xy[1]).getY()+sizeOfMap);
                }
            }
        }
        return xy;
    }
}
