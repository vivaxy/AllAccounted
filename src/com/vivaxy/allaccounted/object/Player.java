package com.vivaxy.allaccounted.object;

/**
 * Created by vivaxy on 2014/6/19 17:52.
 */
public class Player {
    String name;
    int money;
    long color;
    double degree;
    float x;
    float y;
    int radius;
    boolean from;
    boolean to;

    public boolean isFrom() {
        return from;
    }

    public void setFrom(boolean from) {
        this.from = from;
    }

    public boolean isTo() {
        return to;
    }

    public void setTo(boolean to) {
        this.to = to;
    }

    public double getDegree() {
        return degree;
    }

    public void setDegree(double degree) {
        this.degree = degree;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        if (money < Integer.MAX_VALUE && money > Integer.MIN_VALUE) this.money = money;
    }

    public long getColor() {
        return color;
    }

    public void setColor(long color) {
        this.color = color;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
