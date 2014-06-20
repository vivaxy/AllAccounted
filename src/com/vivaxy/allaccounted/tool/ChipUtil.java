package com.vivaxy.allaccounted.tool;

import com.vivaxy.allaccounted.object.Chip;

import java.util.ArrayList;

/**
 * Author: vivaxy
 * Date: 2014/6/20 14:04
 * Project: AllAccounted
 * Package: com.vivaxy.allaccounted.tool
 */
public class ChipUtil {
    public static ArrayList<Chip> cl = new ArrayList<Chip>();

    public void initCl() {
        Chip c0 = new Chip();
        c0.setMoney(1);
        Chip c1 = new Chip();
        c1.setMoney(5);
        Chip c2 = new Chip();
        c2.setMoney(10);
        cl.add(c0);
        cl.add(c1);
        cl.add(c2);
    }

    public int getChip0() {
        return cl.get(0).getMoney();
    }

    public void setChip0(int money) {
        cl.get(0).setMoney(money);
    }

    public int getChip1() {
        return cl.get(1).getMoney();
    }

    public void setChip1(int money) {
        cl.get(1).setMoney(money);
    }

    public int getChip2() {
        return cl.get(2).getMoney();
    }

    public void setChip2(int money) {
        cl.get(2).setMoney(money);
    }
}
