package com.vivaxy.allaccounted.android;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.vivaxy.allaccounted.R;
import com.vivaxy.allaccounted.tool.DialogUtil;
import com.vivaxy.allaccounted.tool.PlayerUtil;

/**
 * Author : vivaxy
 * Date   : 2014/6/24 10:45
 * Project: AllAccounted
 * Package: com.vivaxy.allaccounted.android
 */
public class SetNumberDialog extends DialogFragment implements OnClickListener {

    Activity activity;
    PlayerUtil pu = new PlayerUtil();
    DialogUtil du;

    SetNumberDialog(Activity activity) {
        this.activity = activity;
        this.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        this.show(activity.getFragmentManager(), "");
        du = new DialogUtil(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setCanceledOnTouchOutside(false);
        View view = inflater.inflate(R.layout.set_number_dialog, container);
        TextView title = (TextView) view.findViewById(R.id.prompt_title);
        title.setText(R.string.setNumber);
        EditText inputView = (EditText) view.findViewById(R.id.number_input);
        inputView.setText(String.valueOf(pu.getNumber()));
        Button ok_btn = (Button) view.findViewById(R.id.ok_btn);
        ok_btn.setOnClickListener(this);
        Button cancel_btn = (Button) view.findViewById(R.id.cancel_btn);
        cancel_btn.setOnClickListener(this);
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputView.selectAll();
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        return view;
    }

    @Override
    public void onClick(View view) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        EditText inputView;
        String input;
        switch (view.getId()) {
            case R.id.cancel_btn:
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                dismiss();
                break;
            case R.id.ok_btn:
                inputView = (EditText) this.getView().findViewById(R.id.number_input);
                input = inputView.getText().toString();
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                du.setNumber(input);
                dismiss();
                break;
            default:
                break;
        }
    }
}