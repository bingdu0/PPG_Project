package com.ppg.network;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Create by Donny.
 * 2017/10/30
 * Description：
 */
public class HostType {
    /**
     * 多少种Host类型
     */
    public static final int TYPE_COUNT = 3;

    /**
     * 网易新闻视频的host
     */
    @HostTypeChecker
    public static final int REQUEST_01 = 0;

    /**
     * 新浪图片的host
     */
    @HostTypeChecker
    public static final int REQUEST_02 = 1;

    /**
     * 天气查询的host
     */
    @HostTypeChecker
    public static final int REQUEST_03 = 2;

    /**
     * 替代枚举的方案，使用IntDef保证类型安全
     */
    @IntDef({REQUEST_01, REQUEST_02, REQUEST_03})
    @Retention(RetentionPolicy.SOURCE)
    public @interface HostTypeChecker {

    }

    /**
     * 获取对应的host
     *
     * @param hostType host类型
     * @return host
     */
    public static String getHost(int hostType) {
        switch (hostType) {
            case HostType.REQUEST_01:
                return Api.TEST_01;
            case HostType.REQUEST_02:
                return Api.HOST;
            case HostType.REQUEST_03:
                return Api.SERVER_PRODUCT;
        }
        return "";
    }
}
