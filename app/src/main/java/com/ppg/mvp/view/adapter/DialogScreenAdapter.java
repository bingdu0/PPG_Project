package com.ppg.mvp.view.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ppg.R;
import com.ppg.bean.ScreenDialogBean;
import com.ppg.utils.StringUtils;

import java.util.List;

/**
 * Created by lixu on 2018/3/7.
 */

public class DialogScreenAdapter extends BaseItemDraggableAdapter<ScreenDialogBean,BaseViewHolder> {


    private Context mContext;
    private boolean isBc;//是否设置背景
    /**
     *
     */
    public DialogScreenAdapter(Context context, int layoutResId, @Nullable List<ScreenDialogBean> data,boolean isBc1) {
        super(layoutResId, data);
        this.mContext = context;
        this.isBc = isBc1;
    }

    @Override
    protected void convert(BaseViewHolder helper, ScreenDialogBean item) {
        if(!StringUtils.isEmpty(item.getText())) {
            helper.setText(R.id.tv_item_dialog_tag, item.getText());
        }
        if(isBc) {
            if (item.isSelect()) {
                helper.setBackgroundRes(R.id.tv_item_dialog_tag, R.drawable.bg_frame_tv_p);
            } else {
                helper.setBackgroundRes(R.id.tv_item_dialog_tag, R.drawable.bg_frame_tv);
            }
        }


    }
}
