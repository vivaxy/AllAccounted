package com.vivaxy.allaccounted.tool;

import com.vivaxy.allaccounted.object.Chip;

import java.util.ArrayList;

/**
 * Author : vivaxy
 * Date   : 2014/6/20 14:04
 * Project: AllAccounted
 * Package: com.vivaxy.allaccounted.tool
 */
public class ChipUtil {

    public static ArrayList<Chip> cl = new ArrayList<Chip>();
    public String[] chipValueList0 = chipDisplayedValues(1, 1, 10);
    public String[] chipValueList1 = chipDisplayedValues(5, 5, 10);
    public String[] chipValueList2 = chipDisplayedValues(10, 10, 10);

    public void initCl(int i0, int i1, int i2) {
        Chip c0 = new Chip();
        c0.setIndex(i0);
        Chip c1 = new Chip();
        c1.setIndex(i1);
        Chip c2 = new Chip();
        c2.setIndex(i2);
        cl.add(c0);
        cl.add(c1);
        cl.add(c2);
    }

    public String getChipValue(int i) {
        String returnValue = null;
        switch (i) {
            case 0:
                returnValue = chipValueList0[getChipIndex(i)];
                break;
            case 1:
                returnValue = chipValueList1[getChipIndex(i)];
                break;
            case 2:
                returnValue = chipValueList2[getChipIndex(i)];
                break;
            default:
                break;
        }
        return returnValue;
    }

    public int getChipIndex(int i) {
        return cl.get(i).getIndex();
    }

    public void setChipIndex(int i, int index) {
        cl.get(i).setIndex(index);
    }

    private String[] chipDisplayedValues(int min, int interval, int number) {
        String[] returnValue = new String[number];
        for (int i = 0; i < number; i++) {
            returnValue[i] = String.valueOf(interval * i + min);
        }
        return returnValue;
    }
}
