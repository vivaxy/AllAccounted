package com.vivaxy.allaccounted.android;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.waps.AppConnect;
import com.vivaxy.allaccounted.R;
import com.vivaxy.allaccounted.tool.FeedbackUtil;

/**
 * Author : vivaxy
 * Date   : 2014/6/24 19:55
 * Project: AllAccounted
 * Package: com.vivaxy.allaccounted.android
 */
public class Feedback extends Activity {

    InputMethodManager imm = (InputMethodManager) HomeActivity.ha.getSystemService(Context.INPUT_METHOD_SERVICE);
    FeedbackUtil fu = new FeedbackUtil();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            TextView tv = (TextView) findViewById(R.id.feedback_container);
            try {
                Boolean success = fu.sendFeedback(tv.getText().toString());
                Looper.prepare();
                if (success) Toast.makeText(HomeActivity.ha, R.string.feedback_success, Toast.LENGTH_LONG).show();
                else Toast.makeText(HomeActivity.ha, R.string.feedback_error, Toast.LENGTH_LONG).show();
                Looper.loop();
            } catch (Exception e) {
                Looper.prepare();
                Toast.makeText(HomeActivity.ha, R.string.feedback_error, Toast.LENGTH_LONG).show();
                Looper.loop();
                e.printStackTrace();
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        ActionBar actionBar = getActionBar();
        actionBar.setTitle(R.string.feedback);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        LinearLayout layout = (LinearLayout) this.findViewById(R.id.ad_layout);
        LinearLayout pop_layout = AppConnect.getInstance(this).getPopAdView(this);

        if (pop_layout == null) {
            return;
        }
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.RIGHT_OF, layout.getId());

        pop_layout.setBackgroundColor(Color.argb(200, 40, 40, 40));
        pop_layout.setId((int) (System.currentTimeMillis() + 1));
        pop_layout.setPadding(5, 0, 5, 0);
        layout.addView(pop_layout, params);
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
                TextView tv = (TextView) findViewById(R.id.feedback_container);
                String content = tv.getText().toString();
                if (content.equals("")) {
                    Toast.makeText(HomeActivity.ha, R.string.feedback_null, Toast.LENGTH_LONG).show();
                } else {
                    new Thread(runnable).start();
                    imm.hideSoftInputFromWindow(findViewById(android.R.id.content).getWindowToken(), 0);
                    finish();
                }
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