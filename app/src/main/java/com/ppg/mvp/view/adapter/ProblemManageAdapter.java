package com.ppg.mvp.view.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ppg.bean.TestBean;

import java.util.List;

/**
 * Created by LiuKai on 2018/3/7.
 */

public class ProblemManageAdapter extends BaseQuickAdapter<TestBean,BaseViewHolder> {

    public ProblemManageAdapter(int layoutResId, @Nullable List<TestBean> data) {
        super(layoutResId, data);
    }

    public ProblemManageAdapter(@Nullable List<TestBean> data) {
        super(data);
    }

    public ProblemManageAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestBean item) {

    }
}
