package com.example.mysnake;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class PartSnake {
    private Bitmap bm;
    private int x, y;
    private Rect rectBody, rectTop, rectBottom, rectLeft, rectRight;

    public PartSnake(Bitmap bm, int x, int y) {
        this.bm = bm;
        this.x = x;
        this.y = y;
    }

    public Bitmap getBm() {
        return bm;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rect getRectBody() {
        return new Rect(this.x, this.y, this.x + GameView.sizeOfMap, this.y + GameView.sizeOfMap);
    }

    public Rect getRectTop() {
        return new Rect(this.x, this.y - 10*Constants.SCREEN_HEIGHT/1920, this.x + GameView.sizeOfMap, this.y);
    }

    public Rect getRectBottom() {
        return new Rect(this.x, this.y + GameView.sizeOfMap, this.x + GameView.sizeOfMap, this.y + GameView.sizeOfMap+10*Constants.SCREEN_HEIGHT/1920);
    }

    public Rect getRectLeft() {
        return new Rect(this.x - 10*Constants.SCREEN_WIDTH/1080, this.y, this.x, this.y + GameView.sizeOfMap);
    }

    public Rect getRectRight() {
        return new Rect(this.x + GameView.sizeOfMap, this.y, this.x + GameView.sizeOfMap + 10*Constants.SCREEN_WIDTH/1080, this.y + GameView.sizeOfMap);
    }

    public void setBm(Bitmap bm) {
        this.bm = bm;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRectBody(Rect rectBody) {
        this.rectBody = rectBody;
    }

    public void setRectTop(Rect rectTop) {
        this.rectTop = rectTop;
    }

    public void setRectBottom(Rect rectBottom) {
        this.rectBottom = rectBottom;
    }

    public void setRectLeft(Rect rectLeft) {
        this.rectLeft = rectLeft;
    }

    public void setRectRight(Rect rectRight) {
        this.rectRight = rectRight;
    }
}
