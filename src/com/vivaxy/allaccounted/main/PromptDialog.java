package com.vivaxy.allaccounted.main;

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
import android.widget.NumberPicker;
import android.widget.TextView;
import com.vivaxy.allaccounted.R;
import com.vivaxy.allaccounted.tool.ChipUtil;
import com.vivaxy.allaccounted.tool.DialogUtil;
import com.vivaxy.allaccounted.tool.PlayerUtil;

/**
 * Author: vivaxy
 * Date: 2014/6/19 17:52
 * Project: AllAccounted
 * Package: com.vivaxy.allaccounted.main
 */
public class PromptDialog extends DialogFragment implements OnClickListener {

    ChipUtil cu = new ChipUtil();
    PlayerUtil pu = new PlayerUtil();
    DialogUtil du = new DialogUtil();
    InputMethodManager imm = (InputMethodManager) HomeActivity.ha.getSystemService(Context.INPUT_METHOD_SERVICE);
    String[] chip0_value = du.chipDisplayedValues(1, 1, 10);
    String[] chip1_value = du.chipDisplayedValues(5, 5, 10);
    String[] chip2_value = du.chipDisplayedValues(10, 10, 10);

    public static PromptDialog newInstance(int tag, int from, int to) {
        PromptDialog pd = new PromptDialog();
        Bundle bundle = new Bundle();
        bundle.putInt("title_tag", tag);
        bundle.putInt("from", from);
        bundle.putInt("to", to);
        pd.setArguments(bundle);
        pd.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        return pd;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        int title_tag = bundle.getInt("title_tag");
        getDialog().setCanceledOnTouchOutside(false);
        View view = null;
        TextView title;
        EditText inputView;
        Button ok_btn;
        Button cancel_btn;

        switch (title_tag) {
            case 0:
                view = inflater.inflate(R.layout.prompt_dialog, container);
                title = (TextView) view.findViewById(R.id.prompt_title);
                title.setText(R.string.setNumber);
                inputView = (EditText) view.findViewById(R.id.number_input);
                inputView.setText(String.valueOf(pu.getNumber()));
                ok_btn = (Button) view.findViewById(R.id.ok_btn);
                ok_btn.setOnClickListener(this);
                cancel_btn = (Button) view.findViewById(R.id.cancel_btn);
                cancel_btn.setOnClickListener(this);
                inputView.selectAll();
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                break;
            case 1:
                view = inflater.inflate(R.layout.set_chip, container);
                title = (TextView) view.findViewById(R.id.prompt_title);
                title.setText(R.string.setChip);

                NumberPicker chip0 = (NumberPicker) view.findViewById(R.id.chip0);
                chip0.setMinValue(0);
                chip0.setMaxValue(chip0_value.length - 1);
                chip0.setDisplayedValues(chip0_value);
                chip0.setValue(0);

                NumberPicker chip1 = (NumberPicker) view.findViewById(R.id.chip1);
                chip1.setMinValue(0);
                chip1.setMaxValue(chip1_value.length - 1);
                chip1.setDisplayedValues(chip1_value);
                chip1.setValue(0);

                NumberPicker chip2 = (NumberPicker) view.findViewById(R.id.chip2);
                chip2.setMinValue(0);
                chip2.setMaxValue(chip2_value.length - 1);
                chip2.setDisplayedValues(chip2_value);
                chip2.setValue(0);

                ok_btn = (Button) view.findViewById(R.id.ok_btn);
                ok_btn.setOnClickListener(this);
                cancel_btn = (Button) view.findViewById(R.id.cancel_btn);
                cancel_btn.setOnClickListener(this);
                break;
            case 10:
                view = inflater.inflate(R.layout.prompt_dialog_add, container);
                title = (TextView) view.findViewById(R.id.prompt_title);
                title.setText(R.string.transfer);

                Button button_chip0 = (Button) view.findViewById(R.id.button_chip0);
                Button button_chip1 = (Button) view.findViewById(R.id.button_chip1);
                Button button_chip2 = (Button) view.findViewById(R.id.button_chip2);
                button_chip0.setText("+" + cu.getChip0());
                button_chip1.setText("+" + cu.getChip1());
                button_chip2.setText("+" + cu.getChip2());
                button_chip0.setOnClickListener(this);
                button_chip1.setOnClickListener(this);
                button_chip2.setOnClickListener(this);
                inputView = (EditText) view.findViewById(R.id.number_input);
                inputView.setText("0");
                ok_btn = (Button) view.findViewById(R.id.ok_btn);
                ok_btn.setOnClickListener(this);
                cancel_btn = (Button) view.findViewById(R.id.cancel_btn);
                cancel_btn.setOnClickListener(this);
                inputView.selectAll();
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                break;
            default:
                break;
        }
        return view;
    }

    @Override
    public void onClick(View view) {
        DialogUtil du = new DialogUtil();
        Bundle bundle = getArguments();
        int title_tag = bundle.getInt("title_tag");
        EditText inputView;
        String input;
        switch (title_tag) {
            case 0:
                switch (view.getId()) {
                    case R.id.cancel_btn:
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        dismiss();
                        break;
                    case R.id.ok_btn:
                        inputView = (EditText) this.getView().findViewById(R.id.number_input);
                        input = inputView.getText().toString();
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        if (title_tag == 0) du.setNumber(input);
                        else if (title_tag == 10) du.transfer(bundle, input);
                        dismiss();
                        break;
                    default:
                        break;
                }
                break;
            case 1:
                switch (view.getId()) {
                    case R.id.cancel_btn:
                        dismiss();
                        break;
                    case R.id.ok_btn:
                        NumberPicker chip0 = (NumberPicker) this.getView().findViewById(R.id.chip0);
                        String _chip0 = chip0_value[chip0.getValue()];
                        NumberPicker chip1 = (NumberPicker) this.getView().findViewById(R.id.chip1);
                        String _chip1 = chip1_value[chip1.getValue()];
                        NumberPicker chip2 = (NumberPicker) this.getView().findViewById(R.id.chip2);
                        String _chip2 = chip2_value[chip2.getValue()];
                        du.setChip(_chip0, _chip1, _chip2);
                        dismiss();
                        break;
                    default:
                        break;
                }
                break;
            case 10:
                inputView = (EditText) this.getView().findViewById(R.id.number_input);
                input = inputView.getText().toString();
                switch (view.getId()) {
                    case R.id.cancel_btn:
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        dismiss();
                        break;
                    case R.id.ok_btn:
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        if (title_tag == 0) du.setNumber(input);
                        else if (title_tag == 10) du.transfer(bundle, input);
                        dismiss();
                        break;
                    case R.id.button_chip0:
                        inputView.setText(String.valueOf(cu.getChip0() + Integer.parseInt(input)));
                        break;
                    case R.id.button_chip1:
                        inputView.setText(String.valueOf(cu.getChip1() + Integer.parseInt(input)));
                        break;
                    case R.id.button_chip2:
                        inputView.setText(String.valueOf(cu.getChip2() + Integer.parseInt(input)));
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }
}
