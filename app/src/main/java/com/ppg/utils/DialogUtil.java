package com.ppg.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ppg.R;
import com.ppg.customview.wheeldate.ScreenInfo;
import com.ppg.customview.wheeldate.WheelMain;

import java.util.Calendar;


/**
 * 作者：yangwenquan on 2017/4/6
 * 类描述：
 */
public class DialogUtil {
    public static Context contexts;
    public static boolean isShow = true;


    public static DialogUtil dialogUtil;

    public static DialogUtil getInstens(Context context) {
        contexts = context;
        if (dialogUtil == null) {
            isShow = false;
            dialogUtil = new DialogUtil();
        }
        return dialogUtil;
    }

    public void showPrompt(String title, String Content, String btnCancelT, String btnSureT, final DialogListener listener) {
        View view = LayoutInflater.from(contexts).inflate(R.layout.dialog_layout, null);//这里的
        TextView tvTitle = (TextView) view.findViewById(R.id.dg_title);
        TextView tvContent = (TextView) view.findViewById(R.id.dg_content);
        Button btnCancel = (Button) view.findViewById(R.id.btn_cancel);
        Button btnSure = (Button) view.findViewById(R.id.btn_sure);
        LinearLayout dg_room = (LinearLayout) view.findViewById(R.id.dg_room);
        tvTitle.setText(title);
        if (TextUtils.isEmpty(Content)) {
            tvContent.setVisibility(View.GONE);
        } else {
            tvContent.setText(Content);
        }
        btnCancel.setText(btnCancelT);
        btnSure.setText(btnSureT);

        final AlertDialog.Builder builder = new AlertDialog.Builder(contexts);
        final AlertDialog dialog = builder.create();
        dialog.setView(view);
        dialog.setCancelable(false);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCancel();
                if (dialog != null) {
                    dialog.cancel();
                    isShow = false;
                }
            }
        });
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSure();
                if (dialog != null) {
                    dialog.cancel();
                    isShow = false;
                }
            }
        });
        if (!isShow) {
            dialog.show();
            isShow = true;
        }
    }

    public interface DialogListener {
        void onCancel();

        void onSure();
    }

    public void showNoBtn(String Content) {
        View view = LayoutInflater.from(contexts).inflate(R.layout.dialog_hint, null);
        TextView tvHint = (TextView) view.findViewById(R.id.tv_hint);
        LinearLayout dg_room = (LinearLayout) view.findViewById(R.id.dg_room);
        tvHint.setText(Content);
        final AlertDialog.Builder builder = new AlertDialog.Builder(contexts);
        AlertDialog dialog = builder.create();
        dialog.setView(view);
        dialog.show();
    }

    public void showPrompt(String title, String Content, String btnSureT, final DialogListener listener) {
        View view = LayoutInflater.from(contexts).inflate(R.layout.dialog_layout, null);//这里的
        TextView tvTitle = (TextView) view.findViewById(R.id.dg_title);
        TextView tvContent = (TextView) view.findViewById(R.id.dg_content);
        Button btnCancel = (Button) view.findViewById(R.id.btn_cancel);
        Button btnSure = (Button) view.findViewById(R.id.btn_sure);
        LinearLayout dg_room = (LinearLayout) view.findViewById(R.id.dg_room);
        tvTitle.setText(title);
        if (TextUtils.isEmpty(Content)) {
            tvContent.setVisibility(View.GONE);
        } else {
            tvContent.setText(Content);
        }
        btnCancel.setVisibility(View.GONE);
        btnSure.setText(btnSureT);

        final AlertDialog.Builder builder = new AlertDialog.Builder(contexts);
        final AlertDialog dialog = builder.create();
        dialog.setView(view);
        dialog.setCancelable(false);

        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSure();
                if (dialog != null) {
                    dialog.cancel();
                    isShow = false;
                }
            }
        });
        if (!isShow) {
            dialog.show();
            isShow = true;
        }
    }


    /**
     * @param activity
     * @param
     * @param dateDtyle 0：年，月，日，时，分；
     *                  1；年，月，日；
     *                  2；年，月；
     *                  3；年；
     *                  4；时，分，秒；
     *                  5；月，日
     * @param
     * @MethodName:showDatePickerDialog
     * @Description: 弹出选择日期对话框
     * @author mingshuo
     * created at 16/1/23 12:32
     */

    public static void showDatePickerDialog(Activity activity, String title, int dateDtyle,
                                        final OnDatePickedListener dataListener, final DialogListener listener) {


        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        int curSecond = calendar.get(Calendar.SECOND);

        View timepickerview = LayoutInflater.from(activity).inflate(R.layout.timepicker, null);
        ScreenInfo screenInfo = new ScreenInfo(activity);
        Button btnCancel = (Button) timepickerview.findViewById(R.id.btn_cancel);
        Button btnSure = (Button) timepickerview.findViewById(R.id.btn_sure);

        final WheelMain wheelMain = new WheelMain(timepickerview, dateDtyle);
        wheelMain.screenheight = screenInfo.getHeight();
        wheelMain.initDateTimePicker(year, month, day, hour, min, curSecond);

        final AlertDialog.Builder builder = new AlertDialog.Builder(contexts);
        final AlertDialog dialog = builder.create();
        dialog.setView(timepickerview);
        dialog.setCancelable(false);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCancel();
                if (dialog != null) {
                    dialog.cancel();
                    isShow = false;
                }
            }
        });
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSure();
                dataListener.pick(wheelMain.getTime());
                if (dialog != null) {
                    dialog.cancel();
                    isShow = false;
                }
            }
        });
        if (!isShow) {
            dialog.show();
            isShow = true;
        }
    }

    public interface OnDatePickedListener {
        void pick(String pickedDate);
    }


}
