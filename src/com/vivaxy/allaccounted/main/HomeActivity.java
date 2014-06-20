package com.vivaxy.allaccounted.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.vivaxy.allaccounted.R;
import com.vivaxy.allaccounted.tool.DialogUtil;
import com.vivaxy.allaccounted.tool.PlayerListUtil;

/**
 * Author: vivaxy
 * Date: 2014/6/19 17:52
 * Project: AllAccounted
 * Package: com.vivaxy.allaccounted.object
 */

public class HomeActivity extends Activity {

    public static HomeActivity ha;
    PlayerListUtil plu = new PlayerListUtil();
    DialogUtil du = new DialogUtil();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.ha = this;
        plu.initPl(4);
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
                du.showDialog("setNumber");
                break;
            case R.id.exit:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}