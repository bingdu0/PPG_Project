package com.ppg.mvp.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BasePresenter;

/**
 * 基面情况
 */
public class BaseAreaActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_area;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        baseTitle.setText("基面情况");
    }

    @Override
    protected void initListener() {

    }
}
