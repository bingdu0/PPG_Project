package com.ppg.mvp.view.activity;


import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BasePresenter;

/**
 * 施工条件
 */
public class ConstructConditionActivity extends BaseActivity {



    @Override
    protected int getLayoutId() {
        return R.layout.activity_construct_condition;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        baseTitle.setText("施工条件");
    }

    @Override
    protected void initListener() {

    }
}
