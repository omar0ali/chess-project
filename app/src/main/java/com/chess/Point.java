package com.chess;

public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = Math.max(x, 0);
        this.y = Math.max(y, 0);
    }

    public int getX() {
        return Math.max(x, 0);
    }

    public int getY() {
        return Math.max(y, 0);
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
