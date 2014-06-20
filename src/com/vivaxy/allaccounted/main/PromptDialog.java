package com.vivaxy.allaccounted.main;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.vivaxy.allaccounted.R;
import com.vivaxy.allaccounted.tool.DialogUtil;

public class PromptDialog extends DialogFragment implements OnClickListener {

    String title;

    public static PromptDialog newInstance(String title, int from, int to){
        PromptDialog pd = new PromptDialog();
        Bundle bundle = new Bundle();
        bundle.putString("prompt_title", title);
        bundle.putInt("from", from);
        bundle.putInt("to", to);
        pd.setArguments(bundle);
        pd.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        return pd;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        View v = inflater.inflate(R.layout.prompt_dialog, container);
        TextView tv = (TextView)v.findViewById(R.id.prompt_title);
        title = bundle.getString("prompt_title");
        tv.setText(title);
        Button ok_btn = (Button) v.findViewById(R.id.ok_btn);
        ok_btn.setOnClickListener(this);
        Button cancel_btn = (Button) v.findViewById(R.id.cancel_btn);
        cancel_btn.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        DialogUtil du = new DialogUtil();
        switch (v.getId()) {
            case R.id.cancel_btn:
                dismiss();
                break;
            case R.id.ok_btn:
                if (title.equals("setNumber")) du.setNumber(this);
                else if (title.equals("transfer")) du.transfer(this);
                dismiss();
                break;
            default:
                break;
        }
    }
}
