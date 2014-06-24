package com.vivaxy.allaccounted.tool;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import com.vivaxy.allaccounted.android.HomeActivity;
import com.vivaxy.allaccounted.android.HomeView;
import com.vivaxy.allaccounted.object.Player;

import java.util.ArrayList;

/**
 * Author: vivaxy
 * Date: 2014/6/19 17:52
 * Project: AllAccounted
 * Package: com.vivaxy.allaccounted.tool
 */

public class PlayerUtil {

    public static ArrayList<Player> pl = new ArrayList<Player>();
    MathUtil mu = new MathUtil();

    public void addPlayer(Player p) {
        pl.add(p);
        resetDegree(pl);
    }

    public void clearPlayerList() {
        pl.clear();
        resetDegree(pl);
    }

    public void resetDegree(ArrayList<Player> pl) {
        for (int i = 0; i < pl.size(); i++) {
            Player p = pl.get(i);
            double deg = 2 * Math.PI / pl.size();
            p.setDegree(deg * i + deg * 0.5);
        }
    }

    public void initPl(int count) {
        clearPlayerList();
        for (int i = 0; i < count; i++) {
            Player p = new Player();
//            p.setColor((int) (Math.random() * 16777215) + 4278190080L);
            p.setFillColor(0xFF858585);
            p.setStrokeColor(0xFF383838);
            p.setName("vivaxy");
            p.setMoney(0);
            p.setRadius(64);
            p.setFrom(false);
            p.setTo(false);
            p.setFontColor(0xFFEEEEEE);
            p.setFontSize(48);
            addPlayer(p);
        }
        HomeActivity.ha.setContentView(new HomeView(HomeActivity.ha));
    }

    public void drawPlayerList(Canvas canvas, Paint paint, float x, float y) {
        for (Player p : pl) {
            p.setX(mu.getX(canvas.getWidth(), p.getRadius(), p.getDegree()));
            p.setY(mu.getY(canvas.getHeight(), p.getRadius(), p.getDegree()));
            if (p.isFrom()) {
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(p.getFillColor());
                canvas.drawCircle(x, y, p.getRadius(), paint);

                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(p.getStrokeColor());
                canvas.drawCircle(x, y, p.getRadius(), paint);

                String text = String.valueOf(p.getMoney());
                paint.setTextAlign(Paint.Align.CENTER);
                Rect bounds = new Rect();
                paint.getTextBounds(text, 0, text.length(), bounds);
                paint.setColor(p.getFontColor());
                paint.setTextSize(p.getFontSize());
                canvas.drawText(text, x, y + (bounds.bottom - bounds.top) / 2, paint);
            } else {
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(p.getFillColor());
                canvas.drawCircle(p.getX(), p.getY(), p.getRadius(), paint);

                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(p.getStrokeColor());
                canvas.drawCircle(p.getX(), p.getY(), p.getRadius(), paint);

                String text = String.valueOf(p.getMoney());
                paint.setTextAlign(Paint.Align.CENTER);
                Rect bounds = new Rect();
                paint.getTextBounds(text, 0, text.length(), bounds);
                paint.setColor(p.getFontColor());
                paint.setTextSize(p.getFontSize());
                canvas.drawText(text, p.getX(), p.getY() + (bounds.bottom - bounds.top) / 2, paint);
            }
        }
    }

    public void setFrom(float x, float y) {
        for (Player p : pl) {
            if (mu.isIn(p.getX(), p.getY(), x, y, p.getRadius())) {
                p.setFrom(true);
            } else {
                p.setFrom(false);
            }
        }
    }

    public void setTo(float x, float y) {
        if (fromWhich() != -1) {
            for (int i = 0; i < pl.size(); i++) {
                Player p = pl.get(i);
                if (fromWhich() != i && mu.isOn(p.getX(), p.getY(), x, y, p.getRadius())) {
                    p.setTo(true);
                } else {
                    p.setTo(false);
                }
            }
        }
    }

    public int toWhich() {
        for (int i = 0; i < pl.size(); i++) {
            if (pl.get(i).isTo()) return i;
        }
        return -1;
    }

    public int fromWhich() {
        for (int i = 0; i < pl.size(); i++) {
            if (pl.get(i).isFrom()) return i;
        }
        return -1;
    }

    public void clearFrom() {
        for (Player aPl : pl) {
            aPl.setFrom(false);
        }
    }

    public void clearTo() {
        for (Player aPl : pl) {
            aPl.setTo(false);
        }
    }

    public int getNumber() {
        return pl.size();
    }
}
