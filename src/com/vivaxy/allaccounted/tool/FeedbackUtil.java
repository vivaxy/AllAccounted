package com.vivaxy.allaccounted.tool;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.util.Log;
import com.vivaxy.allaccounted.object.SystemInfo;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author : vivaxy
 * Date   : 2014/6/26 17:17
 * Project: AllAccounted
 * Package: com.vivaxy.allaccounted.tool
 */
public class FeedbackUtil {

    Activity activity;

    public FeedbackUtil(Activity activity) {
        this.activity = activity;
    }


    public Boolean sendFeedback(String content) {

        Boolean success = false;
        String url = "https://api.github.com/repos/vivaxy/AllAccounted/issues";
        try {
            HttpPost httppost = new HttpPost(url);
            JSONObject para = new JSONObject();
            para.put("title", content);
            para.put("body", feedBackBody());
            StringEntity se = new StringEntity(para.toString(), HTTP.UTF_8);
            httppost.setEntity(se);
            httppost.setHeader("Authorization", "token 9e86122a46472b572ae9fe0b401427ef4865bdff");
            httppost.setHeader("Accept", "application/json");
            httppost.setHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
            HttpResponse response = new DefaultHttpClient().execute(httppost);
            Log.v("-------", "getStatusCode: " + response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == 201) {
                Log.v("-------", "response: " + EntityUtils.toString(response.getEntity()));
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    public String feedBackBody() {
        String systemInfo = new SystemInfo(activity).getSysInfoString();
        String submitTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        SharedPreferences setting = activity.getSharedPreferences("AllAccounted", Context.MODE_PRIVATE);
        String installTime = setting.getString("install_time", "");
        PackageManager pm = activity.getPackageManager();
        String appVersion = "";
        try {
            appVersion = pm.getPackageInfo(activity.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "submitTime: " + submitTime + "\n" +
                "\n" +
                "installTime: " + installTime + "\n" +
                "appVersion: " + appVersion + "\n" +
                "\n" +
                systemInfo;
    }
}
