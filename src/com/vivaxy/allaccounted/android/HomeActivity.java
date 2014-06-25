package com.vivaxy.allaccounted.android;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import cn.waps.AppConnect;
import com.vivaxy.allaccounted.R;
import com.vivaxy.allaccounted.tool.ChipUtil;
import com.vivaxy.allaccounted.tool.PlayerUtil;

/**
 * Author : vivaxy
 * Date   : 2014/6/19 17:52
 * Project: AllAccounted
 * Package: com.vivaxy.allaccounted.android
 */

public class HomeActivity extends Activity {

    public static HomeActivity ha;
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
            this.ha = this;
            pu.initPl(4);
            cu.initCl();
            setContentView(new HomeView(this));
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
                SetNumberDialog snd = new SetNumberDialog();
                snd.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
                snd.show(HomeActivity.ha.getFragmentManager(), "");
                setContentView(new HomeView(this));
                break;
            case R.id.setChip:
                SetChipDialog scd = new SetChipDialog();
                scd.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
                scd.show(HomeActivity.ha.getFragmentManager(), "");
                break;
            case R.id.feedback:
                Intent feedBackIntent = new Intent(this, Feedback.class);
                this.startActivity(feedBackIntent);
                break;
            case R.id.about:
//                AboutDialog ad = new AboutDialog();
//                ad.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
//                ad.show(HomeActivity.ha.getFragmentManager(), "");
                PackageManager pm = HomeActivity.ha.getPackageManager();
                String version = getResources().getString(R.string.version) + ": ";
                try {
                    version = version + pm.getPackageInfo(HomeActivity.ha.getPackageName(), 0).versionName;
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
                Intent appWallIntent = new Intent(this, AppWall.class);
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
        super.onDestroy();
    }
}