package com.vivaxy.allaccounted.android;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.vivaxy.allaccounted.R;
import com.vivaxy.allaccounted.tool.ChipUtil;
import com.vivaxy.allaccounted.tool.PlayerUtil;

/**
 * Author: vivaxy
 * Date: 2014/6/19 17:52
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
        this.ha = this;
        pu.initPl(4);
        cu.initCl();
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
                break;
            case R.id.setChip:
                SetChipDialog scd = new SetChipDialog();
                scd.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
                scd.show(HomeActivity.ha.getFragmentManager(), "");
                break;
            case R.id.about:
                AboutDialog ad = new AboutDialog();
                ad.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
                ad.show(HomeActivity.ha.getFragmentManager(), "");
                break;
            case R.id.exit:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}