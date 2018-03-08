package com.ppg.mvp.view.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ppg.R;
import com.ppg.bean.ScreenDialogBean;
import com.ppg.utils.StringUtils;

import java.util.List;

/**
 * Created by lixu on 2018/3/7.
 */

public class CheckBoxSmallAdapter extends BaseItemDraggableAdapter<ScreenDialogBean,BaseViewHolder> {


    private Context mContext;

    /**
     */
    public CheckBoxSmallAdapter(Context context, int layoutResId, @Nullable List<ScreenDialogBean> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ScreenDialogBean item) {
        if(!StringUtils.isEmpty(item.getText())) {
            helper.setText(R.id.item_cb, item.getText());
        }
            helper.setChecked(R.id.item_cb,item.isSelect());


    }
}
