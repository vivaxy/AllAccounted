package com.vivaxy.allaccounted.tool;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.vivaxy.allaccounted.R;

import java.util.List;

/**
 * @{# ViewPagerAdapter.java Create on 2013-5-2 下午11:03:39
 * <p/>
 * class desc: 引导页面适配器
 * <p/>
 * <p>
 * Copyright: Copyright(c) 2013
 * </p>
 * @Version 1.0
 * @Author <a href="mailto:gaolei_xj@163.com">Leo</a>
 */
public class ViewPagerAdapter extends PagerAdapter {

    private static final String SHAREDPREFERENCES_NAME = "first_pref";
    // 界面列表
    private List<View> views;
    private Activity activity;

    public ViewPagerAdapter(List<View> views, Activity activity) {
        this.views = views;
        this.activity = activity;
    }

    // 销毁arg1位置的界面
    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView(views.get(arg1));
    }

    @Override
    public void finishUpdate(View arg0) {
    }

    // 获得当前界面数
    @Override
    public int getCount() {
        if (views != null) {
            return views.size();
        }
        return 0;
    }

    // 初始化arg1位置的界面
    @Override
    public Object instantiateItem(View arg0, int arg1) {
        ((ViewPager) arg0).addView(views.get(arg1), 0);
        if (arg1 == views.size() - 1) {
            Button mStartWeiboImageButton = (Button) arg0.findViewById(R.id.iv_start_weibo);
            mStartWeiboImageButton.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // 设置已经引导
                    SharedPreferences setting = activity.getSharedPreferences("AllAccounted", Context.MODE_PRIVATE);
                    setting.edit().putBoolean("first_launch", false).commit();
                    activity.finish();
                }

            });
        }
        return views.get(arg1);
    }

    // 判断是否由对象生成界面
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return (arg0 == arg1);
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void startUpdate(View arg0) {
    }

}
