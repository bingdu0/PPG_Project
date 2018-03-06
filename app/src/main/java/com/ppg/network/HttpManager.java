package com.ppg.network;


import com.google.gson.Gson;
import com.ppg.base.BaseApplication;
import com.ppg.utils.LogUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by GaoSheng on 2016/9/14.
 */

public class HttpManager {

    private static OkHttpClient mOkHttpClient;
    private static ApiService apiService;
    private static Retrofit retrofit;

    /**
     * 获取单例
     *
     * @param hostType host类型
     * @return 实例
     */
    public static HttpManager getInstance(int hostType) {
        //本来先用SparseArray来做单例,但是老出问题,暂时这样。
        return new HttpManager(hostType);
    }

    private HttpManager(@HostType.HostTypeChecker int hostType) {
        retrofit = new Retrofit.Builder()
                .baseUrl(HostType.getHost(hostType))
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(new Gson())) //
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();


        apiService = retrofit.create(ApiService.class);
    }

    /**
     * @return retrofit的底层利用反射的方式, 获取所有的api接口的类
     */
    public static ApiService getHttpService() {
        if (apiService == null) {
            apiService = retrofit.create(ApiService.class);
        }
        return apiService;
    }

    private OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (HttpManager.class) {
                if (mOkHttpClient == null) {
                    // OkHttpClient配置是一样的,静态创建一次即可
                    // 指定缓存路径,缓存大小100Mb
                    Cache cache = new Cache(new File(BaseApplication.getContext().getCacheDir(), "HttpCache"), 1024 * 1024 * 100);
                    mOkHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
                            .addInterceptor(mLogInterceptor)            //设置日志打印
                            .addInterceptor(addQueryParameterInterceptor) //设置公共参数
                            .connectTimeout(60, TimeUnit.SECONDS)       //设置连接超时
                            .readTimeout(60, TimeUnit.SECONDS)          //设置读超时
                            .writeTimeout(60, TimeUnit.SECONDS)          //设置写超时
                            .retryOnConnectionFailure(true)             //是否自动重连
                            .build();
                }
            }
        }
        return mOkHttpClient;
    }

    /**
     * 设置公共参数
     */
    Interceptor addQueryParameterInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request request;
            HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                    //TODO  提供您的自定义参数
//                        .addQueryParameter("phoneSystem", "")
//                        .addQueryParameter("phoneModel", "")
                    .build();
            request = originalRequest.newBuilder().url(modifiedUrl).build();
            return chain.proceed(request);
        }
    };

    // 打印返回的json数据拦截器
    private Interceptor mLogInterceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            //设置请求头
            Request.Builder requestBuilder = request.newBuilder();
            requestBuilder.addHeader("platform", "11");
            requestBuilder.addHeader("device_imei", "000000000000000");
            request = requestBuilder.build();

            final Response response = chain.proceed(request);
            LogUtils.i("<请求网址信息>: \n" + request.tag() + " \n " + "<请求头部信息>：\n" + request.headers() + " \n " + "<响应头部信息>：\n" + response.headers());

            final ResponseBody responseBody = response.body();
            final long contentLength = responseBody.contentLength();
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(charset);
                } catch (UnsupportedCharsetException e) {
                    LogUtils.e("不能解码,字符集可能有问题");
                    return response;
                }
            }
            if (contentLength != 0) {
                LogUtils.v("--------------------------------------------开始打印返回数据----------------------------------------------------");
                LogUtils.i(buffer.clone().readString(charset));
                LogUtils.v("--------------------------------------------结束打印返回数据----------------------------------------------------");
            }
            return response;
        }
    };
}
