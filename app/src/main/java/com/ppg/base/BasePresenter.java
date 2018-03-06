package com.ppg.base;

import java.lang.ref.WeakReference;

/**
 * Created by GaoSheng on 2016/11/26.
 * 17:21
 *
 * @VERSION V1.4
 * com.example.gs.mvpdemo.base
 */

public abstract class BasePresenter<V extends IView> implements IPresenter {
    private WeakReference actReference;

    @Override
    public void attachView(IView iView) {
        actReference = new WeakReference(iView);
    }

    @Override
    public void detachView() {
        if (actReference != null) {
            actReference.clear();
            actReference = null;
        }
    }

    @Override
    public V getIView() {
        return (V) actReference.get();
    }


    /**
     * @param
     * @return
     * 添加多个model,如有需要
     */
//    public abstract HashMap<String, IModel> loadModelMap(IModel... models);
//    public abstract HashMap<String, IModel> getiModelMap();


}
