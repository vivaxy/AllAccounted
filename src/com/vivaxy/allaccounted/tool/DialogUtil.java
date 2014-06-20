package com.vivaxy.allaccounted.tool;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.vivaxy.allaccounted.R;
import com.vivaxy.allaccounted.main.HomeActivity;
import com.vivaxy.allaccounted.object.Player;
import com.vivaxy.allaccounted.main.PromptDialog;

/**
 * Created by vivaxy on 2014/6/19 18:45.
 */
public class DialogUtil {

    PlayerListUtil plu = new PlayerListUtil();

    public void showDialog(String tag) {
        if (tag.equals("setNumber")) {
            PromptDialog pd = PromptDialog.newInstance("setNumber", 0, 0);
            pd.show(HomeActivity.ha.getFragmentManager(), "tag");
        } else if (tag.equals("transfer")) {
            PromptDialog pd = PromptDialog.newInstance("transfer", plu.fromWhich(), plu.toWhich());
            pd.show(HomeActivity.ha.getFragmentManager(), "tag");
            plu.clearFrom();
            plu.clearTo();
        }

    }

    public void setNumber(PromptDialog pd) {
        TextView tv = (TextView) pd.getView().findViewById(R.id.number_input);
        int n = Integer.parseInt(tv.getText().toString());

        if (n > 9 || n < 2) {
            Toast.makeText(HomeActivity.ha, "2~9", Toast.LENGTH_LONG).show();
        } else {
            new PlayerListUtil().initPl(n);
        }
    }

    public void transfer(PromptDialog pd) {
        Bundle bundle = pd.getArguments();
        int from = bundle.getInt("from");
        int to = bundle.getInt("to");
        TextView tv = (TextView) pd.getView().findViewById(R.id.number_input);
        int n = Integer.parseInt(tv.getText().toString());
        Toast.makeText(HomeActivity.ha, "transfer from " + from + " to " + to + " money " + n, Toast.LENGTH_LONG).show();
        Player fromP = PlayerListUtil.pl.get(from);
        Player toP = PlayerListUtil.pl.get(to);
        fromP.setMoney(fromP.getMoney() - n);
        toP.setMoney(toP.getMoney() + n);
    }
}
