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
import com.vivaxy.allaccounted.tool.ChipUtil;
import com.vivaxy.allaccounted.tool.DialogUtil;

/**
 * Author: vivaxy
 * Date: 2014/6/19 17:52
 * Project: AllAccounted
 * Package: com.vivaxy.allaccounted.main
 */
public class PromptDialog extends DialogFragment implements OnClickListener {

    ChipUtil cu = new ChipUtil();

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
        String title = bundle.getString("prompt_title");
        View view = inflater.inflate(R.layout.prompt_dialog, container);
        if (title.equals("transfer")) {
            view = inflater.inflate(R.layout.prompt_dialog_add, container);
            Button button_chip0 = (Button) view.findViewById(R.id.button_chip0);
            Button button_chip1 = (Button) view.findViewById(R.id.button_chip1);
            Button button_chip2 = (Button) view.findViewById(R.id.button_chip2);
            button_chip0.setText("+" + cu.getChip0());
            button_chip1.setText("+" + cu.getChip1());
            button_chip2.setText("+" + cu.getChip2());
            button_chip0.setOnClickListener(this);
            button_chip1.setOnClickListener(this);
            button_chip2.setOnClickListener(this);
        }
        TextView tv = (TextView) view.findViewById(R.id.prompt_title);
        tv.setText(title);
        Button ok_btn = (Button) view.findViewById(R.id.ok_btn);
        ok_btn.setOnClickListener(this);
        Button cancel_btn = (Button) view.findViewById(R.id.cancel_btn);
        cancel_btn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        DialogUtil du = new DialogUtil();
        Bundle bundle = getArguments();
        String title = bundle.getString("prompt_title");
        TextView tv = (TextView) this.getView().findViewById(R.id.number_input);
        String input = tv.getText().toString();
        if (input.equals("")) input = "0";
        switch (view.getId()) {
            case R.id.cancel_btn:
                dismiss();
                break;
            case R.id.ok_btn:
                if (title.equals("setNumber")) du.setNumber(input);
                else if (title.equals("transfer")) du.transfer(bundle, input);
                dismiss();
                break;
            case R.id.button_chip0:
                tv.setText(String.valueOf(cu.getChip0() + Integer.parseInt(input)));
                break;
            case R.id.button_chip1:
                tv.setText(String.valueOf(cu.getChip1() + Integer.parseInt(input)));
                break;
            case R.id.button_chip2:
                tv.setText(String.valueOf(cu.getChip2() + Integer.parseInt(input)));
                break;
            default:
                break;
        }
    }
}
