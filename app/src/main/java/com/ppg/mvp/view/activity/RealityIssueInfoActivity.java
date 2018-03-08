package com.ppg.mvp.view.activity;

import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BasePresenter;

/**
 * Created by lixu on 2018/3/8.
 */

public class RealityIssueInfoActivity extends BaseActivity{


    @Override
    protected int getLayoutId() {
        return R.layout.activity_reality_issue_info;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        baseTitle.setText("现场施工问题隐患");
    }

    @Override
    protected void initListener() {

    }
}
