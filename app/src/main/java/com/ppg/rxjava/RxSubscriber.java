package com.ppg.rxjava;

import com.ppg.network.HttpResult;
import com.ppg.utils.LogUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Create by Donny.
 * 2017/7/1
 * Description：封装的Subscriber
 */
public abstract class RxSubscriber<T> implements Observer<HttpResult<T>> {
    private final int SUCCESS_CODE = 1;

    @Override
    public void onSubscribe(Disposable d) {
        mOnStart();
    }

    @Override
    public void onNext(HttpResult<T> tHttpResult) {
        if (tHttpResult.getCode()==SUCCESS_CODE) {
            T t=tHttpResult.getData();
            mOnNext(t);
        }else {
            //TODO 需要完善返回码信息
            mOnError("返回码有误");
        }
    }

    @Override
    public void onError(Throwable e) {
        LogUtils.e("YWQ",e.toString());
        //TODO 需要判断具体网络异常
        mOnError("网络异常，请稍后再试");
    }
    @Override
    public void onComplete() {
        mOnComplete();
    }

    public  void mOnStart(){}

    public abstract void mOnNext(T t);

    public abstract void mOnError(String errorMsg);

    public  void mOnComplete(){}

}
