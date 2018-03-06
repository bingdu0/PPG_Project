package com.ppg.mvp.view.adapter;


import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.Button;

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
public class ReportTeDisRAdapter extends BaseMultiItemQuickAdapter<TestBean, BaseViewHolder> {
    private Context mContext;

    public ReportTeDisRAdapter(Context context, List<TestBean> data) {
        super(data);
        this.mContext = context;
        addItemType(Constant.ITEM_TYPE_TV_TV_BTN_R, R.layout.item_tv_tv_btn_r);
        addItemType(Constant.ITEM_TYPE_TV_TV_BTN_D, R.layout.item_tv_tv_btn_d);
        addItemType(Constant.ITEM_TYPE_TV_EV, R.layout.item_tv_et);
        addItemType(Constant.ITEM_TYPE_TV_TV_R_BTN_R, R.layout.item_tv_tv_r_btn_r);
        addItemType(Constant.ITEM_TYPE_BTN, R.layout.item_btn);
    }


    @Override
    protected void convert(BaseViewHolder helper, TestBean item) {
        switch (helper.getItemViewType()) {
            case Constant.ITEM_TYPE_TV_TV_BTN_R:
                helper.setText(R.id.tv_01, item.getTitle());
                helper.setText(R.id.tv_02, item.getContent());
                break;
            case Constant.ITEM_TYPE_TV_TV_BTN_D:
                helper.setText(R.id.tv_01, item.getTitle());
                helper.setText(R.id.tv_02, item.getContent());
                helper.addOnClickListener(R.id.btn_back_d);
                break;
            case Constant.ITEM_TYPE_TV_EV:
                helper.setText(R.id.tv_01, item.getTitle());
                ClearEditText clearEditText = helper.getView(R.id.et_02);
                clearEditText.setHint(item.getContent());
                break;
            case Constant.ITEM_TYPE_TV_TV_R_BTN_R:
                helper.setText(R.id.tv_01, item.getTitle());
                helper.setText(R.id.tv_02, item.getContent());
                break;
            case Constant.ITEM_TYPE_BTN:
                Button button = helper.getView(R.id.btn_01);
                button.setText(item.getTitle());
                break;

            default:
                break;
        }

        switch (helper.getAdapterPosition()) {
            case 9:
                helper.setVisible(R.id.view_top, true);
                break;
            case 12:
                helper.setVisible(R.id.view_top, true);
                break;
            default:
                break;
        }
    }

    @Nullable
    @Override
    public TestBean getItem(int position) {
        return super.getItem(position);
    }
}
