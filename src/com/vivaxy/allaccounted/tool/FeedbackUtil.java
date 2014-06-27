package com.vivaxy.allaccounted.tool;

import android.app.Activity;
import android.util.Log;
import com.vivaxy.allaccounted.object.SystemInfo;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

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


    public Boolean sendFeedback(String content) throws Exception {

        String systemInfo = new SystemInfo(activity).getSysInfoString();

        String url = "https://api.github.com/repos/vivaxy/AllAccounted/issues";

        HttpPost httppost = new HttpPost(url);
        JSONObject para = new JSONObject();
        para.put("title", content);
        para.put("body", "Feedback from AllAccounted Android device.\n" + systemInfo);
        StringEntity se = new StringEntity(para.toString(), HTTP.UTF_8);
        httppost.setEntity(se);
        httppost.setHeader("Authorization", "token 9e86122a46472b572ae9fe0b401427ef4865bdff");
        httppost.setHeader("Accept", "application/json");
        httppost.setHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
        HttpResponse response = new DefaultHttpClient().execute(httppost);
        Log.v("-------", "getStatusCode: " + response.getStatusLine().getStatusCode());
        if (response.getStatusLine().getStatusCode() == 201) {
            Log.v("-------", "response: " + EntityUtils.toString(response.getEntity()));
            return true;
        } else return false;
    }
}
