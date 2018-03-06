package com.ppg.constants;


import com.ppg.utils.IoUtil;

import java.io.File;


/**
 * Create by Donny.
 * 2017/4/9
 */
public class Config {
    /**
     * Debug开关
     */
    public static boolean IS_DEBUG = true;

    public static String LOG_TAG = "LOG_TAG";

    public static final String SP_HOST_TYPE="sp_host_type";

    /**
     * SharedPreferences 默认名
     */
    public static final String SHARE_NAME_DEF = "share_name_def";


    /**
     * 应用程序SD卡根目录
     */
    public static String SD_ROOT_PATH= IoUtil.getRootPath();


    /**
     * 应用程序SD卡根目录
     */
    public static String ROOT_PATH= IoUtil.getRootPath();


    /**
     * 缓存文件夹目录
     */
    public static String CACHE_DIR="cache"+ File.separator;

    /**
     * 缓存文件路径
     */
    public static String CACHE_PATH=ROOT_PATH+CACHE_DIR;

    /**
     * 空字符
     */
    public static String EMPTY_CHARACTER = "";





    /**
     * 网络请求参数:平台(11、安卓; 12、IOS)
     */
    public static String PLATFORM = "11";

    /**
     * 网络请求参数:设备识别码
     */
    public static String DEVICE_IMEI = EMPTY_CHARACTER;
    /**
     * 网络请求参数:设备类型
     */
    public static String DEVICE_TYPE = EMPTY_CHARACTER;
    /**
     * 网络请求参数:设备型号
     */
    public static String DEVICE_MODEL = EMPTY_CHARACTER;

    /**
     * 网络请求参数:设备系统版本
     */
    public static String SYSTEM_VERSION = EMPTY_CHARACTER;

    /**
     * 网络请求参数:屏幕尺寸
     */
    public static String SCREEN_SIZE = EMPTY_CHARACTER;

    /**
     * 网络请求参数:网络类型
     */
    public static String NETWORK_TYPE = EMPTY_CHARACTER;

    /**
     * 网络请求参数:软件版本号
     */
    public static int APP_VERSION = 0;

    /**
     * 设备信息
     */
    public static final String ERROR_IMEI = "000000000000000";
    public static final String ERROR_MODEL = "sdk";
    public static final String GENYMOTION_MODEL = "Genymotion";
    public static final String DEVICETYPE_VM = "VM";
    public static final String DEVICETYPE_DEVICE = "Device";
    public static final String NETWORKTYPE_WIFI = "Wifi";
    public static final String NETWORKTYPE_4G = "4G";
    public static final String NETWORKTYPE_3G = "3G";
    public static final String NETWORKTYPE_2G = "2G";
    public static final String NETWORKTYPE_MOBILE = "Mobile";
    public static final String NETWORKTYPE_UNKNOWN = "Unknown";

    /**
     * UUID
     */
    public static final String BUG_UUID = "9774d56d682e549c";
    public static final String PREFS_FILE = "device_id.xml";
    public static final String PREFS_DEVICE_ID = "device_id";

    /**
     * 编码
     */
    public static final String ENCODEING_UTF8 = "utf8";

}
