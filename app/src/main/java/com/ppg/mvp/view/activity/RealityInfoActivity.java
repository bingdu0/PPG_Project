package com.ppg.mvp.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BasePresenter;
import com.ppg.constants.Constant;
import com.ppg.utils.StringUtils;

import butterknife.BindView;

/**
 * Created by lixu on 2018/3/8.
 * 现场实操情况
 */

public class RealityInfoActivity extends BaseActivity {

    @BindView(R.id.base_title)
    TextView tvTitle;
    @BindView(R.id.ll_1)
    LinearLayout ll_1;
    @BindView(R.id.ll_2)
    LinearLayout ll_2;

    private String mType;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reality_info;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {

        Intent gIntent = getIntent();
        mType = gIntent.getExtras().getString(Constant.EDIT_TITLE, "");
        tvTitle.setText(mType);
        if (!StringUtils.isEmpty(mType) && mType.equals("现场工程情况")) {
            ll_1.setVisibility(View.GONE);
            ll_2.setVisibility(View.VISIBLE);
        }


    }

    @Override
    protected void initListener() {
        tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
