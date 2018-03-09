package com.ppg.mvp.view.activity;

import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BasePresenter;

/**
 * Created by lixu on 2018/3/9.
 * 腻子施工情况
 */

public class DatumNiZiStatusActivity extends BaseActivity{
    @Override
    protected int getLayoutId() {
        return R.layout.activity_datum_nizi_status;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        baseTitle.setText("腻子施工情况");
    }

    @Override
    protected void initListener() {

    }
}
