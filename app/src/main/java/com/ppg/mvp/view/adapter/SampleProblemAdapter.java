package com.ppg.mvp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ppg.R;
import com.ppg.bean.TestBean;
import com.ppg.constants.Constant;

import java.util.List;

/**
 * Created by LiuKai on 2018/3/9.
 */

public class SampleProblemAdapter extends BaseMultiItemQuickAdapter<TestBean, BaseViewHolder> {
    private Context mContext;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public SampleProblemAdapter(Context mContext, List data) {
        super(data);
        this.mContext = mContext;
        addItemType(Constant.ITEM_TYPE_TEXT_WARNING, R.layout.item_text_warning);

        addItemType(Constant.ITEM_TYPE_CB_TV, R.layout.item_cb_tv);
        addItemType(Constant.ITEM_TYPE_CB_TV_TV, R.layout.item_cb_tv_tv);
    }


    @Override
    protected void convert(BaseViewHolder helper, TestBean item) {
        switch (helper.getItemViewType()) {
            case Constant.ITEM_TYPE_TEXT_WARNING:
                helper.setText(R.id.tv_01, item.getTitle());
                break;
            case Constant.ITEM_TYPE_CB_TV:
                helper.setText(R.id.tv_01, item.getTitle());
                break;
            case Constant.ITEM_TYPE_CB_TV_TV:
                helper.setText(R.id.cb, item.getTitle());
                helper.setText(R.id.cb_tv, item.getContent());
                break;
        }
    }
}
