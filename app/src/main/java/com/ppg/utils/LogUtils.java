package com.ppg.utils;

import android.util.Log;

import com.ppg.constants.Config;


/**
 * Create by Donny.
 * 2017/4/9
 * Description：日志打印的辅助类
 * IS_DEBUG==true 为调试模式
 * IS_DEBUG==false 为上线模式
 */
public class LogUtils {


	public static void i(String content) {
		if (Config.IS_DEBUG) {
			String log = DeviceUtil.getTraceInfo() + "  :\n  " + content;
			log.replaceAll(",","\n");
			Log.i(Config.LOG_TAG, log);
		}
	}


	public static void i(String tag, String content) {
		if (Config.IS_DEBUG) {
			String log = DeviceUtil.getTraceInfo() + "  :\n  " + content;
			log.replaceAll(",","\n");
			Log.i(tag, log);
		}
	}

	public static void e(String content) {
		if (Config.IS_DEBUG) {
			String log = DeviceUtil.getTraceInfo() + "  :\n  " + content;
			log.replaceAll(",","\n");
			Log.e(Config.LOG_TAG, log);
		}
	}

	public static void e(String tag, String content) {
		if (Config.IS_DEBUG) {
			String log = DeviceUtil.getTraceInfo() + "  :\n  " + content;
			log.replaceAll(",","\n");
			Log.e(tag, log);
		}
	}

	public static void d(String content) {
		if (Config.IS_DEBUG) {
			String log = DeviceUtil.getTraceInfo() + "  :\n  " + content;
			log.replaceAll(",","\n");
			Log.d(Config.LOG_TAG, log);
		}
	}

	public static void d(String tag, String content) {
		if (Config.IS_DEBUG) {
			String log = DeviceUtil.getTraceInfo() + "  :\n  " + content;
			log.replaceAll(",","\n");
			Log.d(tag, log);
		}
	}

	public static void v(String content) {
		if (Config.IS_DEBUG) {
			String log = DeviceUtil.getTraceInfo() + "  :\n  " + content;
			log.replaceAll(",","\n");
			Log.v(Config.LOG_TAG, log);
		}
	}

	public static void v(String tag, String content) {
		if (Config.IS_DEBUG) {
			String log = DeviceUtil.getTraceInfo() + "  :\n  " + content;
			log.replaceAll(",","\n");
			Log.v(tag, log);
		}
	}

	public static void print(String content) {
		if (Config.IS_DEBUG) {
			String log = DeviceUtil.getTraceInfo() + "  :\n  " + content;
			log.replaceAll(",","\n");
			System.out.println(log);
		}
	}
}
