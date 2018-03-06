package com.ppg.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ppg.R;
import com.ppg.base.BaseApplication;


public class ToastUtil {
    private static Context context = BaseApplication.getContext();
    private static Toast mToast = null;

    public static void showShort(CharSequence text) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.toast_layout, null);
        TextView textV = (TextView) layout.findViewById(R.id.tv_toast);
        //可自定义字体
//		Typeface typeFace =Typeface.createFromAsset(context.getAssets(),"fonts/droidkufi_regular.ttf");
//		textV.setTypeface(typeFace);
        textV.setText(text);
        if (mToast == null) {
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            mToast.setView(layout);
        } else {
            mToast.cancel();
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            mToast.setView(layout);
        }
        mToast.show();
    }

    public static void showLong(CharSequence text) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.toast_layout, null);
        TextView textV = (TextView) layout.findViewById(R.id.tv_toast);
//		Typeface typeFace =Typeface.createFromAsset(context.getAssets(),"fonts/droidkufi_regular.ttf");
//		textV.setTypeface(typeFace);
        textV.setText(text);
        if (mToast == null) {
            mToast = Toast.makeText(context, text, Toast.LENGTH_LONG);
            mToast.setView(layout);
        } else {
            mToast.cancel();
            mToast = Toast.makeText(context, text, Toast.LENGTH_LONG);
            mToast.setView(layout);
        }
        mToast.show();
    }
}
