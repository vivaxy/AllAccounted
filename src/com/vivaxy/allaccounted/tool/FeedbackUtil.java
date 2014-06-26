package com.vivaxy.allaccounted.tool;

import android.util.Log;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

/**
 * Author : vivaxy
 * Date   : 2014/6/26 17:17
 * Project: AllAccounted
 * Package: com.vivaxy.allaccounted.tool
 */
public class FeedbackUtil {

    public void sendFeedback(String content) throws Exception {
        String url = "https://api.github.com/repos/vivaxy/AllAccounted/issue";
//        String url = "http://10.15.92.130:8080/appTest/test";
        DefaultHttpClient httpclient = new DefaultHttpClient();

        HttpPost httppost = new HttpPost(url);
        JSONObject para = new JSONObject();
        para.put("title", "Feedback");
        para.put("body", content);
        Log.v("-------", "content: " + content);
        StringEntity se = new StringEntity(para.toString(), HTTP.UTF_8);
        httppost.setEntity(se);
        httppost.setHeader("Accept", "application/json");
        httppost.setHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String responseBody = httpclient.execute(httppost, responseHandler);
        Log.v("-------", "response: " + responseBody);

    }
}
