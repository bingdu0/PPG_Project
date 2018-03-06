package com.ppg.mvp.view.activity;

import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BaseApplication;
import com.ppg.base.BasePresenter;

import butterknife.BindView;

/**
 * Create by Donny.
 * 2017/12/16
 * Description：
 * 用户信息
 */
public class UserMsgActivity extends BaseActivity {

    @BindView(R.id.change)
    TextView change;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_msg;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        change.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        baseTitle.setText("用户信息");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BaseApplication.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

    }

    @Override
    protected void initListener() {

    }

}
