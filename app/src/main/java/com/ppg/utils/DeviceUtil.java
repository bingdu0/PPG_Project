package com.ppg.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import com.ppg.constants.Config;


/**
 * Create by Donny.
 * 2017/4/9
 */
public class DeviceUtil {


    private static String imei="000000000";

    /**
     * 获取设备版本
     */
    public static String getDeviceVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取设备类型
     */
    public static String getDeviceModel() {
        String model = android.os.Build.MODEL;
        return model == null ? Config.EMPTY_CHARACTER : model;
    }

    @SuppressLint("MissingPermission")
    public static String getIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            imei = tm.getDeviceId();
            return imei == null ? "" : imei;
        }else {
            return imei ;
        }

    }
//    /**
//     * 获取设备IMEI
//     */
//    public static String getIMEI() {
//        TelephonyManager tm = (TelephonyManager) BaseApplication.getAppContext().getSystemService(Context.TELEPHONY_SERVICE);
//        String imei = tm.getDeviceId();
//        return imei == null ? EMPTY_CHARACTER : imei;
//    }

//    /**
//     * 获取设备类型
//     */
//    public static String getDeviceType() {
//        String imei = getIMEI();
//        String model = getDeviceModel();
//        if (imei.equals(EMPTY_CHARACTER) || imei.equals(ERROR_IMEI) || model.equals(EMPTY_CHARACTER)
//                || model.equals(ERROR_MODEL) || model.contains(GENYMOTION_MODEL)) {
//            return DEVICETYPE_VM;
//        } else {
//            return DEVICETYPE_DEVICE;
//        }
//    }

    /**
     * 获取设备品牌
     */
    public static String getDeviceBrand() {
        String brand = android.os.Build.BRAND;
        return brand == null ? Config.EMPTY_CHARACTER : brand;
    }

    /**
     * 获取堆栈信息
     */
    public static String getTraceInfo() {
        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stacks = new Throwable().getStackTrace();
        String className = stacks[2].getClassName();
        int index = className.lastIndexOf('.');
        if (index >= 0) {
            className = className.substring(index + 1, className.length());
        }
        String methodName = stacks[2].getMethodName();
        int lineNumber = stacks[2].getLineNumber();
        sb.append(className).append("->").append(methodName).append("()->").append(lineNumber);
        return sb.toString();
    }

}
