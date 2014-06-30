package com.vivaxy.allaccounted.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import cn.waps.AppConnect;
import com.vivaxy.allaccounted.R;
import com.vivaxy.allaccounted.tool.ChipUtil;
import com.vivaxy.allaccounted.tool.PlayerUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author : vivaxy
 * Date   : 2014/6/19 17:52
 * Project: AllAccounted
 * Package: com.vivaxy.allaccounted.android
 */

public class HomeActivity extends Activity {

    Activity activity = this;
    PlayerUtil pu = new PlayerUtil();
    ChipUtil cu = new ChipUtil();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            AppConnect.getInstance(this);
            AppConnect.getInstance(this).initPopAd(this);
            AppConnect.getInstance(this).initFunAd(this);
            AppConnect.getInstance(this).initAdInfo();
            AppConnect.getInstance(this).getConfig("showAd", "defaultValue");
            SharedPreferences setting = getSharedPreferences("AllAccounted", Context.MODE_PRIVATE);
            String installTime = setting.getString("install_time", "");
            if (installTime.equals("")) {
                setting.edit().putString("install_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).commit();
                pu.initPl(4);
                cu.initCl(0, 0, 0);
                Intent intent = new Intent(this, GuideActivity.class);
                this.startActivity(intent);
            } else {
                pu.initPl(setting.getInt("number", 4));
                cu.initCl(setting.getInt("chip_index_0", 0), setting.getInt("chip_index_1", 0), setting.getInt("chip_index_2", 0));
            }
            setContentView(new HomeView(activity));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.setNumber:
                SetNumberDialog snd = new SetNumberDialog(activity);
                setContentView(new HomeView(this));
                break;
            case R.id.setChip:
                SetChipDialog scd = new SetChipDialog(activity);
                break;
            case R.id.feedback:
                Intent feedBackIntent = new Intent(this, FeedbackActivity.class);
                this.startActivity(feedBackIntent);
                break;
            case R.id.about:
//                AboutDialog ad = new AboutDialog(activity);
//                ad.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
//                ad.show(HomeActivity.ha.getFragmentManager(), "");
                PackageManager pm = activity.getPackageManager();
                String version = getResources().getString(R.string.version) + ": ";
                try {
                    version = version + pm.getPackageInfo(activity.getPackageName(), 0).versionName;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                String about_title = getResources().getString(R.string.about);
                String about_content = getResources().getString(R.string.app_name) + "\n" +
                        getResources().getString(R.string.author) + ": vivaxy" + "\n" +
                        version;
                AboutAdDialog.getInstance().show(this, about_title, about_content);
                break;
            case R.id.moreAds:
                Intent appWallIntent = new Intent(this, AppWallActivity.class);
                this.startActivity(appWallIntent);
                break;
            case R.id.exit:
                QuitAdDialog.getInstance().show(this);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        QuitAdDialog.getInstance().show(this);
    }

    @Override
    public void onDestroy() {
        AppConnect.getInstance(this).close();
        SharedPreferences setting = getSharedPreferences("AllAccounted", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = setting.edit();
        edit.putInt("number", pu.getNumber());
        edit.putInt("chip_index_0", cu.getChipIndex(0));
        edit.putInt("chip_index_1", cu.getChipIndex(1));
        edit.putInt("chip_index_2", cu.getChipIndex(2));
        edit.commit();
        super.onDestroy();
    }
}