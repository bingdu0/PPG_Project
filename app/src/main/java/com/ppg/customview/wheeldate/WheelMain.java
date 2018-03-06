package com.ppg.customview.wheeldate;

import android.view.View;

import com.ppg.R;

import java.util.Arrays;
import java.util.List;

public class WheelMain {

    private View view;
    private WheelView wv_year;
    private WheelView wv_month;
    private WheelView wv_day;
    private WheelView wv_hours;
    private WheelView wv_mins;
    private WheelView wv_Second;
    public int screenheight;

    private int iTime;

    private static int START_YEAR = 1990, END_YEAR = 2100;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public static int getSTART_YEAR() {
        return START_YEAR;
    }

    public static void setSTART_YEAR(int sTART_YEAR) {
        START_YEAR = sTART_YEAR;
    }

    public static int getEND_YEAR() {
        return END_YEAR;
    }

    public static void setEND_YEAR(int eND_YEAR) {
        END_YEAR = eND_YEAR;
    }

    public WheelMain(View view) {
        super();
        this.view = view;
        iTime = 0;
        setView(view);
    }

    public WheelMain(View view, int iTime) {
        super();
        this.view = view;
        this.iTime = iTime;
        setView(view);
    }

    public void initDateTimePicker(int year, int month, int day) {
        this.initDateTimePicker(year, month, day, 0, 0, 0);
    }


    public void initDateTimePicker(int year, int month, int day, int h, int m, int s) {
        // 添加大小月月份并将其转换为list,方便之后的判断
        String[] months_big = {"1", "3", "5", "7", "8", "10", "12"};
        String[] months_little = {"4", "6", "9", "11"};

        final List<String> list_big = Arrays.asList(months_big);
        final List<String> list_little = Arrays.asList(months_little);

        wv_year = (WheelView) view.findViewById(R.id.year);
        wv_month = (WheelView) view.findViewById(R.id.month);
        wv_day = (WheelView) view.findViewById(R.id.day);
        wv_hours = (WheelView) view.findViewById(R.id.hour);
        wv_mins = (WheelView) view.findViewById(R.id.min);
        wv_Second = (WheelView) view.findViewById(R.id.sec);


        // 添加"年"监听
        OnWheelChangedListener wheelListener_year = new OnWheelChangedListener() {
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                int year_num = newValue + START_YEAR;
                // 判断大小月及是否闰年,用来确定"日"的数据
                if (list_big.contains(String.valueOf(wv_month.getCurrentItem() + 1))) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 31));
                } else if (list_little.contains(String.valueOf(wv_month.getCurrentItem() + 1))) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 30));
                } else {
                    if ((year_num % 4 == 0 && year_num % 100 != 0) || year_num % 400 == 0)
                        wv_day.setAdapter(new NumericWheelAdapter(1, 29));
                    else
                        wv_day.setAdapter(new NumericWheelAdapter(1, 28));
                }
            }
        };

        // 添加"月"监听
        OnWheelChangedListener wheelListener_month = new OnWheelChangedListener() {
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                int month_num = newValue + 1;
                // 判断大小月及是否闰年,用来确定"日"的数据
                if (list_big.contains(String.valueOf(month_num))) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 31));
                } else if (list_little.contains(String.valueOf(month_num))) {
                    wv_day.setAdapter(new NumericWheelAdapter(1, 30));
                } else {
                    if (((wv_year.getCurrentItem() + START_YEAR) % 4 == 0 && (wv_year.getCurrentItem() + START_YEAR) % 100 != 0) || (wv_year.getCurrentItem() + START_YEAR) % 400 == 0)
                        wv_day.setAdapter(new NumericWheelAdapter(1, 29));
                    else
                        wv_day.setAdapter(new NumericWheelAdapter(1, 28));
                }
            }
        };

        if (iTime == 3) {
            wv_day.setVisibility(View.GONE);
            wv_month.setVisibility(View.GONE);
            wv_hours.setVisibility(View.GONE);
            wv_mins.setVisibility(View.GONE);
            wv_Second.setVisibility(View.GONE);

            // 年
            wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));// 设置"年"的显示数据
            wv_year.setCyclic(true);// 可循环滚动
            wv_year.setLabel("年");// 添加文字
            wv_year.setCurrentItem(year - START_YEAR);// 初始化时显示的数据
        } else if (iTime == 2) {
            wv_hours.setVisibility(View.GONE);
            wv_mins.setVisibility(View.GONE);
            wv_day.setVisibility(View.GONE);
            wv_Second.setVisibility(View.GONE);

            // 年
            wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));// 设置"年"的显示数据
            wv_year.setCyclic(true);// 可循环滚动
            wv_year.setLabel("年");// 添加文字
            wv_year.setCurrentItem(year - START_YEAR);// 初始化时显示的数据

            // 月
            wv_month.setAdapter(new NumericWheelAdapter(1, 12));
            wv_month.setCyclic(true);
            wv_month.setLabel("月");
            wv_month.setCurrentItem(month);

        } else if (iTime == 4) {
            wv_day.setVisibility(View.GONE);
            wv_month.setVisibility(View.GONE);
            wv_year.setVisibility(View.GONE);

            wv_hours.setAdapter(new NumericWheelAdapter(0, 23));
            wv_hours.setCyclic(true);// 可循环滚动
            wv_hours.setLabel("时");// 添加文字
            wv_hours.setCurrentItem(h);

            wv_mins.setAdapter(new NumericWheelAdapter(0, 59));
            wv_mins.setCyclic(true);// 可循环滚动
            wv_mins.setLabel("分");// 添加文字
            wv_mins.setCurrentItem(m);

            wv_Second.setAdapter(new NumericWheelAdapter(0, 59));
            wv_Second.setCyclic(true);// 可循环滚动
            wv_Second.setLabel("秒");// 添加文字
            wv_Second.setCurrentItem(s);

        } else if (iTime == 5) {
            wv_year.setVisibility(View.GONE);
            wv_hours.setVisibility(View.GONE);
            wv_mins.setVisibility(View.GONE);
            wv_Second.setVisibility(View.GONE);

            // 月
            wv_month.setAdapter(new NumericWheelAdapter(1, 12));
            wv_month.setCyclic(true);
            wv_month.setLabel("月");
            wv_month.setCurrentItem(month);

            // 日
            wv_day.setCyclic(true);
            // 判断大小月及是否闰年,用来确定"日"的数据
            if (list_big.contains(String.valueOf(month + 1))) {
                wv_day.setAdapter(new NumericWheelAdapter(1, 31));
            } else if (list_little.contains(String.valueOf(month + 1))) {
                wv_day.setAdapter(new NumericWheelAdapter(1, 30));
            } else {
                // 闰年
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
                    wv_day.setAdapter(new NumericWheelAdapter(1, 29));
                else
                    wv_day.setAdapter(new NumericWheelAdapter(1, 28));
            }
            wv_day.setLabel("日");
            wv_day.setCurrentItem(day - 1);

        } else if (iTime == 0) {
            wv_Second.setVisibility(View.GONE);
            // 年
            wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));// 设置"年"的显示数据
            wv_year.setCyclic(true);// 可循环滚动
            wv_year.setLabel("年");// 添加文字
            wv_year.setCurrentItem(year - START_YEAR);// 初始化时显示的数据

            // 月
            wv_month.setAdapter(new NumericWheelAdapter(1, 12));
            wv_month.setCyclic(true);
            wv_month.setLabel("月");
            wv_month.setCurrentItem(month);

            // 日
            wv_day.setCyclic(true);
            // 判断大小月及是否闰年,用来确定"日"的数据
            if (list_big.contains(String.valueOf(month + 1))) {
                wv_day.setAdapter(new NumericWheelAdapter(1, 31));
            } else if (list_little.contains(String.valueOf(month + 1))) {
                wv_day.setAdapter(new NumericWheelAdapter(1, 30));
            } else {
                // 闰年
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
                    wv_day.setAdapter(new NumericWheelAdapter(1, 29));
                else
                    wv_day.setAdapter(new NumericWheelAdapter(1, 28));
            }
            wv_day.setLabel("日");
            wv_day.setCurrentItem(day - 1);

            wv_hours.setAdapter(new NumericWheelAdapter(0, 23));
            wv_hours.setCyclic(true);// 可循环滚动
            wv_hours.setLabel("时");// 添加文字
            wv_hours.setCurrentItem(h);

            wv_mins.setAdapter(new NumericWheelAdapter(0, 59));
            wv_mins.setCyclic(true);// 可循环滚动
            wv_mins.setLabel("分");// 添加文字
            wv_mins.setCurrentItem(m);

            wv_year.addChangingListener(wheelListener_year);
            wv_month.addChangingListener(wheelListener_month);
        } else if (iTime == 1) {
            wv_hours.setVisibility(View.GONE);
            wv_mins.setVisibility(View.GONE);
            wv_Second.setVisibility(View.GONE);

            // 年
            wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));// 设置"年"的显示数据
            wv_year.setCyclic(true);// 可循环滚动
            wv_year.setLabel("年");// 添加文字
            wv_year.setCurrentItem(year - START_YEAR);// 初始化时显示的数据

            // 月
            wv_month.setAdapter(new NumericWheelAdapter(1, 12));
            wv_month.setCyclic(true);
            wv_month.setLabel("月");
            wv_month.setCurrentItem(month);

            // 日
            wv_day.setCyclic(true);
            wv_day.setAdapter(new NumericWheelAdapter(10, 17));
            // 判断大小月及是否闰年,用来确定"日"的数据
            if (list_big.contains(String.valueOf(month + 1))) {
                wv_day.setAdapter(new NumericWheelAdapter(1, 31));
            } else if (list_little.contains(String.valueOf(month + 1))) {
                wv_day.setAdapter(new NumericWheelAdapter(1, 30));
            } else {
                // 闰年
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
                    wv_day.setAdapter(new NumericWheelAdapter(1, 29));
                else
                    wv_day.setAdapter(new NumericWheelAdapter(1, 28));
            }
            wv_day.setLabel("日");
            wv_day.setCurrentItem(day - 1);

            wv_year.addChangingListener(wheelListener_year);
            wv_month.addChangingListener(wheelListener_month);
        }

        // 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
        int textSize = 0;
        if (iTime == 0)
            textSize = (screenheight / 100) * 3;
        else if (iTime == 1)
            textSize = (screenheight / 100) * 4;
        else if (iTime == 2)
            textSize = (screenheight / 100) * 4;
        else if (iTime == 3)
            textSize = (screenheight / 100) * 4;
        else if (iTime == 4)
            textSize = (screenheight / 100) * 4;
        else if (iTime == 5)
            textSize = (screenheight / 100) * 4;

        wv_day.TEXT_SIZE = textSize;
        wv_month.TEXT_SIZE = textSize;
        wv_year.TEXT_SIZE = textSize;
        wv_hours.TEXT_SIZE = textSize;
        wv_mins.TEXT_SIZE = textSize;
        wv_Second.TEXT_SIZE = textSize;
    }

    public String getTime() {
        StringBuffer sb = new StringBuffer();
        String str = null;

        if (iTime == 1) {
            str = getAddZero((wv_year.getCurrentItem() + START_YEAR)) + "-" + getAddZero((wv_month.getCurrentItem() + 1)) + "-" + (wv_day.getCurrentItem() + 1);
        } else if (iTime == 0) {
            str = getAddZero((wv_year.getCurrentItem() + START_YEAR)) + "-" + getAddZero((wv_month.getCurrentItem() + 1)) + "-" + (wv_day.getCurrentItem() + 1) + " " + getAddZero(wv_hours.getCurrentItem()) + ":" + getAddZero(wv_mins.getCurrentItem());
        } else if (iTime == 2) {
            str = getAddZero((wv_year.getCurrentItem() + START_YEAR)) + "-" + getAddZero((wv_month.getCurrentItem() + 1));
        } else if (iTime == 3) {
            str = getAddZero((wv_year.getCurrentItem() + START_YEAR));
        } else if (iTime == 4) {
            str = getAddZero(wv_hours.getCurrentItem()) + ":" + getAddZero(wv_mins.getCurrentItem()) + ":" + getAddZero(wv_Second.getCurrentItem());
        } else if (iTime == 5) {
            str=getAddZero((wv_month.getCurrentItem() + 1))+"-"+getAddZero((wv_day.getCurrentItem() + 1));
//            str = getAddZero((wv_year.getCurrentItem() + START_YEAR)) + "-" + getAddZero((wv_month.getCurrentItem() + 1)) + "-" + (wv_day.getCurrentItem() + 1) + " " + getAddZero(wv_hours.getCurrentItem()) + ":" + getAddZero(wv_mins.getCurrentItem()) + ":" + getAddZero(wv_Second.getCurrentItem());

        }

        return str;
    }

    public static String getAddZero(int num) {
        String str;
        if (num <= 9) {
            str = "0" + num;
            return str;
        } else {
            str = num + "";
            return str;
        }

    }
}
