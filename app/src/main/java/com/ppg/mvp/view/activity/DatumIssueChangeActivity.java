package com.ppg.mvp.view.activity;

import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BasePresenter;

/**
 * Created by lixu on 2018/3/9.
 */

public class DatumIssueChangeActivity extends BaseActivity{
    @Override
    protected int getLayoutId() {
        return R.layout.activity_datum_issue_change;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        baseTitle.setText("基面问题及整改情况");
    }

    @Override
    protected void initListener() {

    }
}
