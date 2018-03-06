package com.ppg.mvp.view.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ppg.bean.TestBean;

import java.util.List;

/**
 * Create by Donny.
 * 2017/12/16
 * Description：
 * 技术交底记录
 */
public class TeDisAdapter extends BaseQuickAdapter<TestBean,BaseViewHolder>{

    public TeDisAdapter(int layoutResId, @Nullable List<TestBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestBean item) {

    }
}
