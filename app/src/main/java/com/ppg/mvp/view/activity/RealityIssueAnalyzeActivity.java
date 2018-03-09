package com.ppg.mvp.view.activity;

import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BasePresenter;

/**
 * Created by lixu on 2018/3/9.
 */

public class RealityIssueAnalyzeActivity extends BaseActivity{
    @Override
    protected int getLayoutId() {
        return R.layout.activity_reality_analyze;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        baseTitle.setText("现场遇到问题分析");
    }

    @Override
    protected void initListener() {

    }
}
