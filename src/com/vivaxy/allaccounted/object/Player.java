package com.vivaxy.allaccounted.object;

/**
 * Author: vivaxy
 * Date: 2014/6/19 17:52
 * Project: AllAccounted
 * Package: com.vivaxy.allaccounted.object
 */
public class Player {
    private String name;
    private int money;
    private long color;
    private double degree;
    private float x;
    private float y;
    private int radius;
    private boolean from;
    private boolean to;

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
        this.money = money;
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
