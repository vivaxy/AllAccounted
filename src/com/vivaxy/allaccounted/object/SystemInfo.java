package com.vivaxy.allaccounted.object;

import android.os.Build;
import cn.waps.SDKUtils;
import com.vivaxy.allaccounted.android.HomeActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Author : vivaxy
 * Date   : 2014/6/27 8:37
 * Project: AllAccounted
 * Package: com.vivaxy.allaccounted.tool
 */
public class SystemInfo {

    private String os_version = System.getProperty("os.version");
    private int SDK_INT = Build.VERSION.SDK_INT;
    private String BOARD = Build.BOARD;
    private String BOOTLOADER = Build.BOOTLOADER;
    private String BRAND = Build.BRAND;
    private String CPU_ABI = Build.CPU_ABI;
    private String CPU_ABI2 = Build.CPU_ABI2;
    private String DEVICE = Build.DEVICE;
    private String DISPLAY = Build.DISPLAY;
    private String FINGERPRINT = Build.FINGERPRINT;
    private String HARDWARE = Build.HARDWARE;
    private String HOST = Build.HOST;
    private String ID = Build.ID;
    private String MANUFACTURER = Build.MANUFACTURER;
    private String MODEL = Build.MODEL;
    private String PRODUCT = Build.PRODUCT;
    private String SERIAL = Build.SERIAL;
    private String TAGS = Build.TAGS;
    private Long TIME = Build.TIME;
    private String TYPE = Build.TYPE;
    private String USER = Build.USER;
    private int displaySize = SDKUtils.getDisplaySize(HomeActivity.ha);

    public String getSysInfoString() {
        String info = "";
        Map<String, String> map = getSysInfoMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            info = info + entry.getKey() + ": " + entry.getValue() + "\n";
        }
        return info;
    }

    public Map<String, String> getSysInfoMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("os_version", os_version);
        map.put("SDK_INT", String.valueOf(SDK_INT));
        map.put("BOARD", BOARD);
        map.put("BOOTLOADER", BOOTLOADER);
        map.put("BRAND", BRAND);
        map.put("CPU_ABI", CPU_ABI);
        map.put("CPU_ABI2", CPU_ABI2);
        map.put("DEVICE", DEVICE);
        map.put("DISPLAY", DISPLAY);
        map.put("FINGERPRINT", FINGERPRINT);
        map.put("HARDWARE", HARDWARE);
        map.put("HOST", HOST);
        map.put("ID", ID);
        map.put("MANUFACTURER", MANUFACTURER);
        map.put("MODEL", MODEL);
        map.put("PRODUCT", PRODUCT);
        map.put("SERIAL", SERIAL);
        map.put("TAGS", TAGS);
        map.put("TIME", String.valueOf(TIME));
        map.put("TYPE", TYPE);
        map.put("USER", USER);
        map.put("displaySize", String.valueOf(displaySize));
        return map;
    }
}
