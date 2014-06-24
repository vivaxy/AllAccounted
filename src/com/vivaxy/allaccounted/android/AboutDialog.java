package com.vivaxy.allaccounted.android;

import android.app.DialogFragment;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.vivaxy.allaccounted.R;

/**
 * Author: vivaxy
 * Date: 2014/6/24 9:56
 * Project: AllAccounted
 * Package: com.vivaxy.allaccounted.main
 */
public class AboutDialog extends DialogFragment implements OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_dialog, container);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(R.string.about);
        TextView aboutContent = (TextView) view.findViewById(R.id.about);
        PackageManager pm = HomeActivity.ha.getPackageManager();
        String version = getResources().getString(R.string.version) + ": ";
        try {
            version = version + pm.getPackageInfo(HomeActivity.ha.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        aboutContent.setText(getResources().getString(R.string.app_name) + "\n" +
                getResources().getString(R.string.author) + ": vivaxy" + "\n" +
                version + "\n");
        Button ok_btn = (Button) view.findViewById(R.id.ok_btn);
        ok_btn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ok_btn:
                dismiss();
                break;
            default:
                break;
        }
    }
}
