package com.vivaxy.allaccounted.tool;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import com.vivaxy.allaccounted.main.HomeActivity;
import com.vivaxy.allaccounted.main.HomeView;
import com.vivaxy.allaccounted.object.Player;
import com.vivaxy.allaccounted.object.PlayerList;

import java.util.ArrayList;

/**
 * Created by vivaxy on 2014/6/19 17:59.
 */
public class PlayerListUtil {

    public static ArrayList<Player> pl = PlayerList.pl;
    MathUtil mu = new MathUtil();

    public void addPlayer(Player p){
        pl.add(p);
        resetDegree(pl);
    }

    public void clearPlayerList(){
        pl.clear();
        resetDegree(pl);
    }

    public void resetDegree(ArrayList<Player> pl){
        for (int i=0;i<pl.size();i++){
            Player p = pl.get(i);
            double deg = 2 * Math.PI / pl.size();
            p.setDegree(deg * i + deg * 0.5);
        }
    }

    public void initPl(int count){
        clearPlayerList();
        for (int i=0;i<count;i++){
            Player p = new Player();
            p.setColor((int) (Math.random() * 16777215) + 4278190080L);
            p.setName("vivaxy");
            p.setMoney((int) Math.random() * 10);
            p.setRadius(64);
            p.setFrom(false);
            p.setTo(false);
            addPlayer(p);
        }
        HomeActivity.ha.setContentView(new HomeView(HomeActivity.ha));
    }

    public void drawPlayerList(Canvas canvas, Paint paint, float x, float y){
        for (int i=0;i<pl.size();i++){
            Player p = pl.get(i);
            p.setX(mu.getX(canvas.getWidth(), p.getRadius(), p.getDegree()));
            p.setY(mu.getY(canvas.getHeight(), p.getRadius(), p.getDegree()));
            paint.setColor((int) p.getColor());
            if (p.isFrom()){
                canvas.drawCircle(x, y, p.getRadius(), paint);
                canvas.drawText(String.valueOf(p.getMoney()), x, y, paint);
            }
            else{
                canvas.drawCircle(p.getX(), p.getY(), p.getRadius(), paint);
                canvas.drawText(String.valueOf(p.getMoney()), p.getX(), p.getY(), paint);
            }
            paint.setColor(Color.WHITE);
            paint.setTextSize(64);

        }
    }

    public void setFrom(float x, float y) {
        for (int i=0;i<pl.size();i++){
            Player p = pl.get(i);
            if (mu.isIn(p.getX(), p.getY(), x, y, p.getRadius())){
                p.setFrom(true);
            }
            else{
                p.setFrom(false);
            }
        }
    }

    public void setTo(float x, float y) {
        if (fromWhich() != -1){
            for (int i=0;i<pl.size();i++){
                Player p = pl.get(i);
                if (fromWhich() != i && mu.isOn(p.getX(), p.getY(), x, y, p.getRadius())){
                    p.setTo(true);
                }
                else{
                    p.setTo(false);
                }
            }
        }
    }

    public int toWhich() {
        for (int i=0;i<pl.size();i++){
            if (pl.get(i).isTo()) return i;
        }
        return -1;
    }

    public int fromWhich(){
        for (int i=0;i<pl.size();i++){
            if (pl.get(i).isFrom()) return i;
        }
        return -1;
    }

    public void clearFrom(){
        for (int i=0;i<pl.size();i++){
            pl.get(i).setFrom(false);
        }
    }

    public void clearTo() {
        for (int i=0;i<pl.size();i++){
            pl.get(i).setTo(false);
        }
    }
}
