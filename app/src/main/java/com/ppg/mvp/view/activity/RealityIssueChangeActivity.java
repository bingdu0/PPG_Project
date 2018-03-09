package com.ppg.mvp.view.activity;

import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BasePresenter;

/**
 * Created by lixu on 2018/3/9.
 */

public class RealityIssueChangeActivity extends BaseActivity{
    @Override
    protected int getLayoutId() {
        return R.layout.activity_reality_issue_change;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        baseTitle.setText("施工建议及整改措施");
    }

    @Override
    protected void initListener() {

    }
}
