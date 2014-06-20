package com.vivaxy.allaccounted.tool;
/**
 * Created by vivaxy on 2014/6/19 18:14.
 */
public class MathUtil {
    public float getX(int width, int radius, double degree){
        double x = (width / 2 - radius) * (1 + Math.sin(degree)) + radius;
        return (float) x;
    }

    public float getY(int height, int radius, double degree){
        double y = (height / 2 - radius) * (1 - Math.cos(degree)) + radius;
        return (float) y;
    }

    boolean isIn(float x0, float y0, float x, float y, int radius){
        return ((x0-x)*(x0-x))+((y0-y)*(y0-y)) <= radius*radius;
    }

    boolean isOn(float x, float y, float mx, float my, int radius){
        return ((mx - x)*(mx - x)) + ((my - y)*(my - y)) <= 4*radius*radius;
    }
}
