package com.vivaxy.allaccounted.android;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import com.vivaxy.allaccounted.tool.PlayerUtil;

/**
 * Author : vivaxy
 * Date   : 2014/6/19 17:52
 * Project: AllAccounted
 * Package: com.vivaxy.allaccounted.android
 */
public class HomeView extends View {

    Activity activity;
    int action = MotionEvent.ACTION_UP;
    float x;
    float y;
    PlayerUtil pu = new PlayerUtil();
    private Paint paint = new Paint();

    public HomeView(Context context) {
        super(context);
        this.activity = (Activity) context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (action == MotionEvent.ACTION_DOWN) {
            pu.setFrom(x, y);
        } else if (action == MotionEvent.ACTION_MOVE) {
        } else if (action == MotionEvent.ACTION_UP) {
            pu.setTo(x, y);
            if (pu.toWhich() != -1) {
                new TransferDialog(activity, pu.fromWhich(), pu.toWhich());
            }
            pu.clearFrom();
            pu.clearTo();
        }
        pu.drawPlayerList(canvas, paint, x, y);
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
