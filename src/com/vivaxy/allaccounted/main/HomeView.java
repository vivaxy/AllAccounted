package com.vivaxy.allaccounted.main;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import com.vivaxy.allaccounted.tool.DialogUtil;
import com.vivaxy.allaccounted.tool.PlayerListUtil;

/**
 * Created by vivaxy on 2014/6/19 18:02.
 */
public class HomeView extends View {

    private Paint paint = new Paint();
    int action = MotionEvent.ACTION_UP;
    float x;
    float y;
    PlayerListUtil plu = new PlayerListUtil();
    DialogUtil du = new DialogUtil();

    public HomeView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (action == MotionEvent.ACTION_DOWN){
            plu.setFrom(x, y);
        }else if (action == MotionEvent.ACTION_MOVE){
            plu.drawPlayerList(canvas, paint, x, y);
        }else if (action == MotionEvent.ACTION_UP){
            plu.setTo(x, y);
            if (plu.toWhich() != -1) {
                du.showDialog("transfer");
            }
            plu.clearFrom();
            plu.drawPlayerList(canvas, paint, 0, 0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        action = event.getAction();
        x = event.getX();
        y = event.getY();
        invalidate();
        return true;
    }
}
