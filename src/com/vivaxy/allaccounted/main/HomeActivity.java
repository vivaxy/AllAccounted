package com.vivaxy.allaccounted.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.vivaxy.allaccounted.R;
import com.vivaxy.allaccounted.tool.DialogUtil;
import com.vivaxy.allaccounted.tool.PlayerListUtil;

public class HomeActivity extends Activity {

    PlayerListUtil plu = new PlayerListUtil();
    DialogUtil du = new DialogUtil();
    public static HomeActivity ha;

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