package com.example.mysnake;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

public class Snake {
    private Bitmap bm, bm_headUp, bm_headDown, bm_headLeft, bm_headRight, bm_bodyVertical, bm_bodyHorizonal,
    bm_bodyTopRight,bm_bodyTopLeft, bm_bodyBottRight, bm_bodyBottLeft, bm_tailLeft, bm_tailRight,
    bm_tailUp, bm_tailDown;
    private int x, y, length;
    ArrayList<PartSnake> arrPartSnake = new ArrayList<>();

    private boolean moveLeft, moveRight, moveUp, moveDown;
    public Snake(Bitmap bm, int x, int y, int length) {

        this.bm = bm;
        this.x = x;
        this.y = y;
        this.length = length;
        bm_bodyBottLeft = Bitmap.createBitmap(bm,0,0,GameView.sizeOfMap,GameView.sizeOfMap);
        bm_bodyBottRight = Bitmap.createBitmap(bm,GameView.sizeOfMap,0,GameView.sizeOfMap,GameView.sizeOfMap);
        bm_bodyHorizonal = Bitmap.createBitmap(bm,2*GameView.sizeOfMap,0,GameView.sizeOfMap,GameView.sizeOfMap);
        bm_bodyTopLeft = Bitmap.createBitmap(bm,3*GameView.sizeOfMap,0,GameView.sizeOfMap,GameView.sizeOfMap);
        bm_bodyTopRight = Bitmap.createBitmap(bm,4*GameView.sizeOfMap,0,GameView.sizeOfMap,GameView.sizeOfMap);
        bm_bodyVertical = Bitmap.createBitmap(bm,5*GameView.sizeOfMap,0,GameView.sizeOfMap,GameView.sizeOfMap);
        bm_headDown = Bitmap.createBitmap(bm,6*GameView.sizeOfMap,0,GameView.sizeOfMap,GameView.sizeOfMap);
        bm_headLeft = Bitmap.createBitmap(bm,7*GameView.sizeOfMap,0,GameView.sizeOfMap,GameView.sizeOfMap);
        bm_headRight = Bitmap.createBitmap(bm,8*GameView.sizeOfMap,0,GameView.sizeOfMap,GameView.sizeOfMap);
        bm_headUp = Bitmap.createBitmap(bm,9*GameView.sizeOfMap,0,GameView.sizeOfMap,GameView.sizeOfMap);
        bm_tailUp = Bitmap.createBitmap(bm,10*GameView.sizeOfMap,0,GameView.sizeOfMap,GameView.sizeOfMap);
        bm_tailRight = Bitmap.createBitmap(bm,11*GameView.sizeOfMap,0,GameView.sizeOfMap,GameView.sizeOfMap);
        bm_tailLeft = Bitmap.createBitmap(bm,12*GameView.sizeOfMap,0,GameView.sizeOfMap,GameView.sizeOfMap);
        bm_tailDown  = Bitmap.createBitmap(bm,13*GameView.sizeOfMap,0,GameView.sizeOfMap,GameView.sizeOfMap);
        arrPartSnake.add(new PartSnake(bm_headRight,x,y));
        for (int i = 1; i < length - 1; i++) {
            arrPartSnake.add(new PartSnake(bm_bodyHorizonal,arrPartSnake.get(i-1).getX() - GameView.sizeOfMap,y));
        }
        arrPartSnake.add(new PartSnake(bm_tailRight,arrPartSnake.get(length-2).getX() - GameView.sizeOfMap,y));
        setMoveRight(true);
    }

    public void update(){
        for (int i = length - 1; i > 0 ; i--) {
            arrPartSnake.get(i).setX(arrPartSnake.get(i-1).getX());
            arrPartSnake.get(i).setY(arrPartSnake.get(i-1).getY());
        }
        if(moveRight){
            arrPartSnake.get(0).setX(arrPartSnake.get(0).getX()+GameView.sizeOfMap);
            arrPartSnake.get(0).setBm(bm_headRight);
        }
        else if(moveLeft){
            arrPartSnake.get(0).setX(arrPartSnake.get(0).getX()-GameView.sizeOfMap);
            arrPartSnake.get(0).setBm(bm_headLeft);
        }
        else if(moveUp){
            arrPartSnake.get(0).setY(arrPartSnake.get(0).getY()-GameView.sizeOfMap);
            arrPartSnake.get(0).setBm(bm_headUp);
        }
        else if(moveDown){
            arrPartSnake.get(0).setY(arrPartSnake.get(0).getY()+GameView.sizeOfMap);
            arrPartSnake.get(0).setBm(bm_headDown);
        }
        for (int i = 1; i < length - 1; i++) {
            //Bot - Left
            if(arrPartSnake.get(i).getRectLeft().intersect(arrPartSnake.get(i+1).getRectBody())
                && arrPartSnake.get(i).getRectBottom().intersect(arrPartSnake.get(i-1).getRectBody())
            ||arrPartSnake.get(i).getRectLeft().intersect(arrPartSnake.get(i-1).getRectBody())
                && arrPartSnake.get(i).getRectBottom().intersect(arrPartSnake.get(i+1).getRectBody())){
                arrPartSnake.get(i).setBm(bm_bodyBottLeft);
            }
            //Bot - Right
            else if(arrPartSnake.get(i).getRectRight().intersect(arrPartSnake.get(i+1).getRectBody())
                    && arrPartSnake.get(i).getRectBottom().intersect(arrPartSnake.get(i-1).getRectBody())
                    ||arrPartSnake.get(i).getRectRight().intersect(arrPartSnake.get(i-1).getRectBody())
                    && arrPartSnake.get(i).getRectBottom().intersect(arrPartSnake.get(i+1).getRectBody())){
                arrPartSnake.get(i).setBm(bm_bodyBottRight);
            }
            //Top - Left
            else if(arrPartSnake.get(i).getRectLeft().intersect(arrPartSnake.get(i+1).getRectBody())
                    && arrPartSnake.get(i).getRectTop().intersect(arrPartSnake.get(i-1).getRectBody())
                    ||arrPartSnake.get(i).getRectLeft().intersect(arrPartSnake.get(i-1).getRectBody())
                    && arrPartSnake.get(i).getRectTop().intersect(arrPartSnake.get(i+1).getRectBody())){
                arrPartSnake.get(i).setBm(bm_bodyTopLeft);
            }
            //Top - Right
            else if(arrPartSnake.get(i).getRectRight().intersect(arrPartSnake.get(i+1).getRectBody())
                    && arrPartSnake.get(i).getRectTop().intersect(arrPartSnake.get(i-1).getRectBody())
                    ||arrPartSnake.get(i).getRectRight().intersect(arrPartSnake.get(i-1).getRectBody())
                    && arrPartSnake.get(i).getRectTop().intersect(arrPartSnake.get(i+1).getRectBody())){
                arrPartSnake.get(i).setBm(bm_bodyTopRight);
            }
            //Top - Bot -> vertical
            else if(arrPartSnake.get(i).getRectTop().intersect(arrPartSnake.get(i+1).getRectBody())
                    && arrPartSnake.get(i).getRectBottom().intersect(arrPartSnake.get(i-1).getRectBody())
                    ||arrPartSnake.get(i).getRectTop().intersect(arrPartSnake.get(i-1).getRectBody())
                    && arrPartSnake.get(i).getRectBottom().intersect(arrPartSnake.get(i+1).getRectBody())){
                arrPartSnake.get(i).setBm(bm_bodyVertical);
            }
            //Left - Right -> horizontal
            else if(arrPartSnake.get(i).getRectLeft().intersect(arrPartSnake.get(i+1).getRectBody())
                    && arrPartSnake.get(i).getRectRight().intersect(arrPartSnake.get(i-1).getRectBody())
                    ||arrPartSnake.get(i).getRectLeft().intersect(arrPartSnake.get(i-1).getRectBody())
                    && arrPartSnake.get(i).getRectRight().intersect(arrPartSnake.get(i+1).getRectBody())){
                arrPartSnake.get(i).setBm(bm_bodyHorizonal);
            }
        }
        if (arrPartSnake.get(length-1).getRectRight().intersect(arrPartSnake.get(length-2).getRectBody())){
            arrPartSnake.get(length-1).setBm(bm_tailRight);
        }
        else if (arrPartSnake.get(length-1).getRectLeft().intersect(arrPartSnake.get(length-2).getRectBody())){
            arrPartSnake.get(length-1).setBm(bm_tailLeft);
        }
        else if (arrPartSnake.get(length-1).getRectTop().intersect(arrPartSnake.get(length-2).getRectBody())){
            arrPartSnake.get(length-1).setBm(bm_tailUp);
        }
        else if (arrPartSnake.get(length-1).getRectBottom().intersect(arrPartSnake.get(length-2).getRectBody())){
            arrPartSnake.get(length-1).setBm(bm_tailDown);
        }
    }

    public void draw(Canvas canvas){
        for (int i = 0; i < length; i++) {
            canvas.drawBitmap(arrPartSnake.get(i).getBm(),arrPartSnake.get(i).getX(),arrPartSnake.get(i).getY(),null);
        }

    }

    public Bitmap getBm() {
        return bm;
    }

    public void setBm(Bitmap bm) {
        this.bm = bm;
    }

    public Bitmap getBm_headUp() {
        return bm_headUp;
    }

    public void setBm_headUp(Bitmap bm_headUp) {
        this.bm_headUp = bm_headUp;
    }

    public Bitmap getBm_headDown() {
        return bm_headDown;
    }

    public void setBm_headDown(Bitmap bm_headDown) {
        this.bm_headDown = bm_headDown;
    }

    public Bitmap getBm_headLeft() {
        return bm_headLeft;
    }

    public void setBm_headLeft(Bitmap bm_headLeft) {
        this.bm_headLeft = bm_headLeft;
    }

    public Bitmap getBm_headRight() {
        return bm_headRight;
    }

    public void setBm_headRight(Bitmap bm_headRight) {
        this.bm_headRight = bm_headRight;
    }

    public Bitmap getBm_bodyVertical() {
        return bm_bodyVertical;
    }

    public void setBm_bodyVertical(Bitmap bm_bodyVertical) {
        this.bm_bodyVertical = bm_bodyVertical;
    }

    public Bitmap getBm_bodyHorizonal() {
        return bm_bodyHorizonal;
    }

    public void setBm_bodyHorizonal(Bitmap bm_bodyHorizonal) {
        this.bm_bodyHorizonal = bm_bodyHorizonal;
    }

    public Bitmap getBm_bodyTopRight() {
        return bm_bodyTopRight;
    }

    public void setBm_bodyTopRight(Bitmap bm_bodyTopRight) {
        this.bm_bodyTopRight = bm_bodyTopRight;
    }

    public Bitmap getBm_bodyTopLeft() {
        return bm_bodyTopLeft;
    }

    public void setBm_bodyTopLeft(Bitmap bm_bodyTopLeft) {
        this.bm_bodyTopLeft = bm_bodyTopLeft;
    }

    public Bitmap getBm_bodyBottRight() {
        return bm_bodyBottRight;
    }

    public void setBm_bodyBottRight(Bitmap bm_bodyBottRight) {
        this.bm_bodyBottRight = bm_bodyBottRight;
    }

    public Bitmap getBm_bodyBottLeft() {
        return bm_bodyBottLeft;
    }

    public void setBm_bodyBottLeft(Bitmap bm_bodyBottLeft) {
        this.bm_bodyBottLeft = bm_bodyBottLeft;
    }

    public Bitmap getBm_tailLeft() {
        return bm_tailLeft;
    }

    public void setBm_tailLeft(Bitmap bm_tailLeft) {
        this.bm_tailLeft = bm_tailLeft;
    }

    public Bitmap getBm_tailRight() {
        return bm_tailRight;
    }

    public void setBm_tailRight(Bitmap bm_tailRight) {
        this.bm_tailRight = bm_tailRight;
    }

    public Bitmap getBm_tailUp() {
        return bm_tailUp;
    }

    public void setBm_tailUp(Bitmap bm_tailUp) {
        this.bm_tailUp = bm_tailUp;
    }

    public Bitmap getBm_tailDown() {
        return bm_tailDown;
    }

    public void setBm_tailDown(Bitmap bm_tailDown) {
        this.bm_tailDown = bm_tailDown;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public ArrayList<PartSnake> getArrPartSnake() {
        return arrPartSnake;
    }

    public void setArrPartSnake(ArrayList<PartSnake> arrPartSnake) {
        this.arrPartSnake = arrPartSnake;
    }

    public boolean isMoveLeft() {
        return moveLeft;
    }

    public void setMoveLeft(boolean moveLeft) {
        stop();
        this.moveLeft = moveLeft;
    }

    public boolean isMoveRight() {
        return moveRight;
    }

    public void setMoveRight(boolean moveRight) {
        stop();
        this.moveRight = moveRight;
    }

    public boolean isMoveUp() {
        return moveUp;
    }

    public void setMoveUp(boolean moveUp) {
        stop();
        this.moveUp = moveUp;
    }

    public boolean isMoveDown() {
        return moveDown;
    }

    public void setMoveDown(boolean moveDown) {
        stop();
        this.moveDown = moveDown;
    }
    public void stop(){
        this.moveLeft = false;
        this.moveRight = false;
        this.moveDown = false;
        this.moveUp = false;
    }
}
