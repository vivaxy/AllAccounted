package com.vivaxy.allaccounted.tool;

import android.widget.Toast;
import com.vivaxy.allaccounted.R;
import com.vivaxy.allaccounted.android.HomeActivity;
import com.vivaxy.allaccounted.object.Player;

/**
 * Author: vivaxy
 * Date: 2014/6/19 18:54
 * Project: AllAccounted
 * Package: com.vivaxy.allaccounted.tool
 */
public class DialogUtil {

    public void setNumber(String input) {
        int n = 0;
        try {
            n = Integer.parseInt(input);
        } catch (java.lang.NumberFormatException e) {
            e.printStackTrace();
            Toast.makeText(HomeActivity.ha, R.string.NumberFormatException, Toast.LENGTH_LONG).show();
        }
        if (n > 9 || n < 2) {
            Toast.makeText(HomeActivity.ha, R.string.two_to_nine, Toast.LENGTH_LONG).show();
        } else {
            new PlayerUtil().initPl(n);
        }
    }

    public void transfer(int from, int to, String input) {
        if (input.equals("")) {
            Toast.makeText(HomeActivity.ha, R.string.larger_than_zero, Toast.LENGTH_LONG).show();
        } else {
            int n;
            try {
                n = Integer.parseInt(input);
                Player fromP = PlayerUtil.pl.get(from);
                Player toP = PlayerUtil.pl.get(to);
                fromP.setMoney(fromP.getMoney() - n);
                toP.setMoney(toP.getMoney() + n);
            } catch (java.lang.NumberFormatException e) {
                Toast.makeText(HomeActivity.ha, R.string.NumberFormatException, Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }

    public String[] chipDisplayedValues(int min, int interval, int number) {
        String[] returnValue = new String[number];
        for (int i = 0; i < number; i++) {
            returnValue[i] = String.valueOf(interval * i + min);
        }
        return returnValue;
    }

    public void setChip(String chip0, String chip1, String chip2) {
        ChipUtil cu = new ChipUtil();
        cu.setChip0(Integer.parseInt(chip0));
        cu.setChip1(Integer.parseInt(chip1));
        cu.setChip2(Integer.parseInt(chip2));
    }
}
