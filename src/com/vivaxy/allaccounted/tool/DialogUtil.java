package com.vivaxy.allaccounted.tool;

import android.os.Bundle;
import android.widget.Toast;
import com.vivaxy.allaccounted.main.HomeActivity;
import com.vivaxy.allaccounted.main.PromptDialog;
import com.vivaxy.allaccounted.object.Player;

/**
 * Author: vivaxy
 * Date: 2014/6/19 18:54
 * Project: AllAccounted
 * Package: com.vivaxy.allaccounted.tool
 */
public class DialogUtil {

    PlayerUtil pu = new PlayerUtil();

    public void showDialog(String tag) {
        if (tag.equals("setNumber")) {
            PromptDialog pd = PromptDialog.newInstance("setNumber", 0, 0);
            pd.show(HomeActivity.ha.getFragmentManager(), "tag");
        } else if (tag.equals("transfer")) {
            PromptDialog pd = PromptDialog.newInstance("transfer", pu.fromWhich(), pu.toWhich());
            pd.show(HomeActivity.ha.getFragmentManager(), "tag");
            pu.clearFrom();
            pu.clearTo();
        }
    }

    public void setNumber(String input) {
        int n = Integer.parseInt(input);
        if (n > 9 || n < 2) {
            Toast.makeText(HomeActivity.ha, "2~9", Toast.LENGTH_LONG).show();
        } else {
            new PlayerUtil().initPl(n);
        }
    }

    public void transfer(Bundle bundle,String input) {
        int from = bundle.getInt("from");
        int to = bundle.getInt("to");
        if (input.equals("")) {
            Toast.makeText(HomeActivity.ha, ">0", Toast.LENGTH_LONG).show();
        } else {
            int n = Integer.parseInt(input);
            Player fromP = PlayerUtil.pl.get(from);
            Player toP = PlayerUtil.pl.get(to);
            if (fromP.getMoney() - n < Integer.MAX_VALUE && fromP.getMoney() - n > Integer.MIN_VALUE)
                fromP.setMoney(fromP.getMoney() - n);
            if (toP.getMoney() + n < Integer.MAX_VALUE && toP.getMoney() + n > Integer.MIN_VALUE)
                toP.setMoney(toP.getMoney() + n);
        }
    }
}
