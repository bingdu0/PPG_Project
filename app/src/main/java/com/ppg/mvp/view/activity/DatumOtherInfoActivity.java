package com.ppg.mvp.view.activity;

import android.content.Intent;
import android.widget.TextView;

import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BasePresenter;
import com.ppg.utils.StringUtils;

import butterknife.BindView;

/**
 * Created by lixu on 2018/3/9.
 * 现场问题总结分析
 * 施工建议及整改措施
 */

public class DatumOtherInfoActivity extends BaseActivity{
    private String mType;

    @BindView(R.id.tv_other_info)
    TextView tvTitleM;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_datum_other_info;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        Intent gIntent = getIntent();
        mType = gIntent.getExtras().getString("type","");
        if(!StringUtils.isEmpty(mType)){
            baseTitle.setText(mType);
            tvTitleM.setText(mType);
        }

    }

    @Override
    protected void initListener() {

    }
}
