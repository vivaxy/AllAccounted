package com.vivaxy.allaccounted.android;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import cn.waps.AppConnect;
import com.vivaxy.allaccounted.R;

/**
 * Author : vivaxy
 * Date   : 2014/6/24 19:55
 * Project: AllAccounted
 * Package: com.vivaxy.allaccounted.android
 */
public class Feedback extends Activity {

    InputMethodManager imm = (InputMethodManager) HomeActivity.ha.getSystemService(Context.INPUT_METHOD_SERVICE);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        ActionBar actionBar = getActionBar();
        actionBar.setTitle(R.string.back);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setHomeButtonEnabled(true);
//        LinearLayout layout = (LinearLayout) this.findViewById(R.id.ad_layout);
//        AppConnect.getInstance(this).showBannerAd(this, layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem menuSubmit = menu.add(0, 0, 0, R.string.submit);
        menuSubmit.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                imm.hideSoftInputFromWindow(findViewById(android.R.id.content).getWindowToken(), 0);
                finish();
                break;
            case 0:
                Toast.makeText(this, R.string.submit_success, Toast.LENGTH_LONG).show();
//                @Todo send Feedback
                imm.hideSoftInputFromWindow(findViewById(android.R.id.content).getWindowToken(), 0);
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        AppConnect.getInstance(this).close();
        super.onDestroy();
    }
}