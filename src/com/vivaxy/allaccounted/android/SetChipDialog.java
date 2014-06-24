package com.vivaxy.allaccounted.android;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import com.vivaxy.allaccounted.R;
import com.vivaxy.allaccounted.tool.DialogUtil;

/**
 * Author: vivaxy
 * Date: 2014/6/24 10:45
 * Project: AllAccounted
 * Package: com.vivaxy.allaccounted.android
 */
public class SetChipDialog extends DialogFragment implements OnClickListener {

    DialogUtil du = new DialogUtil();
    String[] chip0_value = du.chipDisplayedValues(1, 1, 10);
    String[] chip1_value = du.chipDisplayedValues(5, 5, 10);
    String[] chip2_value = du.chipDisplayedValues(10, 10, 10);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setCanceledOnTouchOutside(false);
        View view = inflater.inflate(R.layout.set_chip_dialog, container);
        TextView title = (TextView) view.findViewById(R.id.prompt_title);
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

        Button ok_btn = (Button) view.findViewById(R.id.ok_btn);
        ok_btn.setOnClickListener(this);
        Button cancel_btn = (Button) view.findViewById(R.id.cancel_btn);
        cancel_btn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
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
    }
}
