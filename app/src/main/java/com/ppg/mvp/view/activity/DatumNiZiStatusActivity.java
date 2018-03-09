package com.ppg.mvp.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BasePresenter;

import butterknife.BindView;

/**
 * Created by lixu on 2018/3/9.
 * 腻子施工情况
 */

public class DatumNiZiStatusActivity extends BaseActivity {

    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.ll_2)
    LinearLayout ll2;

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
        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gIntent = new Intent(DatumNiZiStatusActivity.this, DatumIssueInfoActivity.class);
                startActivity(gIntent);
            }
        });
        ll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gIntent = new Intent(DatumNiZiStatusActivity.this, DatumIssueFinishStatusActivity.class);
                startActivity(gIntent);
            }
        });

    }
}
