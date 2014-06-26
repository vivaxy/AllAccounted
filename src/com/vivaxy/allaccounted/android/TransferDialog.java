package com.vivaxy.allaccounted.android;

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
import com.vivaxy.allaccounted.tool.ChipUtil;
import com.vivaxy.allaccounted.tool.DialogUtil;

/**
 * Author : vivaxy
 * Date   : 2014/6/24 10:46
 * Project: AllAccounted
 * Package: com.vivaxy.allaccounted.android
 */
public class TransferDialog extends DialogFragment implements OnClickListener {

    ChipUtil cu = new ChipUtil();
    DialogUtil du = new DialogUtil();
    InputMethodManager imm = (InputMethodManager) HomeActivity.ha.getSystemService(Context.INPUT_METHOD_SERVICE);
    private int from;
    private int to;

    public TransferDialog newInstance(int from, int to) {
        TransferDialog td = new TransferDialog();
        td.from = from;
        td.to = to;
        td.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        td.show(HomeActivity.ha.getFragmentManager(), "");
        return td;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setCanceledOnTouchOutside(false);
        View view = inflater.inflate(R.layout.transfer_dailog, container);
        TextView title = (TextView) view.findViewById(R.id.prompt_title);
        title.setText(R.string.transfer);

        Button button_chip0 = (Button) view.findViewById(R.id.button_chip0);
        Button button_chip1 = (Button) view.findViewById(R.id.button_chip1);
        Button button_chip2 = (Button) view.findViewById(R.id.button_chip2);
        button_chip0.setText("+" + cu.getChipValue(0));
        button_chip1.setText("+" + cu.getChipValue(1));
        button_chip2.setText("+" + cu.getChipValue(2));
        button_chip0.setOnClickListener(this);
        button_chip1.setOnClickListener(this);
        button_chip2.setOnClickListener(this);

        EditText inputView = (EditText) view.findViewById(R.id.number_input);
        inputView.setText("0");
        Button ok_btn = (Button) view.findViewById(R.id.ok_btn);
        ok_btn.setOnClickListener(this);
        Button cancel_btn = (Button) view.findViewById(R.id.cancel_btn);
        cancel_btn.setOnClickListener(this);
        inputView.selectAll();
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        return view;
    }

    @Override
    public void onClick(View view) {
        EditText inputView = (EditText) this.getView().findViewById(R.id.number_input);
        String input = inputView.getText().toString();
        switch (view.getId()) {
            case R.id.cancel_btn:
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                dismiss();
                break;
            case R.id.ok_btn:
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                du.transfer(from, to, input);
                dismiss();
                break;
            case R.id.button_chip0:
                inputView.setText(String.valueOf(Integer.parseInt(cu.getChipValue(0)) + Integer.parseInt(input)));
                break;
            case R.id.button_chip1:
                inputView.setText(String.valueOf(Integer.parseInt(cu.getChipValue(1)) + Integer.parseInt(input)));
                break;
            case R.id.button_chip2:
                inputView.setText(String.valueOf(Integer.parseInt(cu.getChipValue(2)) + Integer.parseInt(input)));
                break;
            default:
                break;
        }
    }
}
