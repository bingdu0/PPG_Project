package com.ppg.mvp.view.adapter;


import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ppg.R;
import com.ppg.bean.TestBean;
import com.ppg.constants.Constant;
import com.ppg.customview.ClearEditText;

import java.util.List;

/**
 * Create by Donny.
 * 2017/12/16
 * Description：
 */
public class AddAdapter extends BaseMultiItemQuickAdapter<TestBean, BaseViewHolder> {
    private Context mContext;

    public AddAdapter(Context context, List<TestBean> data) {
        super(data);
        this.mContext = context;
        addItemType(Constant.ITEM_TYPE_TV_EV, R.layout.item_tv_et);
        addItemType(Constant.ITEM_TYPE_TV_TV_BTN_D, R.layout.item_tv_tv_btn_d);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestBean item) {
        switch (helper.getItemViewType()) {
            case Constant.ITEM_TYPE_TV_EV:
                helper.setText(R.id.tv_01,item.getTitle());
                ClearEditText clearEditText=helper.getView(R.id.et_02);
                clearEditText.setHint(item.getContent());
                helper.addOnClickListener(R.id.tv_01);
                helper.addOnClickListener(R.id.et_02);
                break;
            case Constant.ITEM_TYPE_TV_TV_BTN_D:
                helper.setText(R.id.tv_01,item.getTitle());
                helper.setText(R.id.tv_02,item.getContent());
                helper.addOnClickListener(R.id.tv_01);
                helper.addOnClickListener(R.id.btn_back_d);
                break;

            default:
                break;
        }
    }

}
