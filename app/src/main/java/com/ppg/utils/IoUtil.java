package com.ppg.utils;

import android.os.Environment;

import com.ppg.base.BaseApplication;
import com.ppg.constants.Config;

import java.io.File;


/**
 * Create by Donny.
 * 2017/4/9
 */
public class IoUtil {
    /**
     * @return String
     * @MethodName:getRootPath
     * @Description: 获取应用跟目录
     */
    public static String getRootPath() {
        String path = "";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            ///storage/emulated/0/com.example.aa.rxjava_retrofit20_okhttp/   getPackageName(context)
            path = Environment.getExternalStorageDirectory() + "/" + "gaoming" + "/";
        } else {
            // path = "/data/data/" + context.getPackageName() + "/";
            path = BaseApplication.getContext().getFilesDir().getPath();
        }
        return path;
    }

    /**
     * 初始化文件目录结构
     */
    public static void initFileDirs() {
        File appPath = new File(Config.SD_ROOT_PATH);
        if (!appPath.exists()) {
            appPath.mkdir();
        }
        File objDir = new File(Config.CACHE_PATH);
        if (!objDir.exists()) {
            objDir.mkdirs();
        }

    }
}
