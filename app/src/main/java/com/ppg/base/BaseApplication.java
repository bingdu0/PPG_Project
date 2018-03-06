package com.ppg.base;

import android.app.Application;
import android.content.Context;


/**
 * Create by Donny.
 * 2017/4/9
 */
public class BaseApplication extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        init();
    }

    private void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    GreenDaoHelper.initDatabase();
//                    IoUtil.initFileDirs();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static Context getContext() {
        return context;
    }
}
