package com.ppg.mvp.view.activity;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BasePresenter;
import com.ppg.bean.ScreenDialogBean;
import com.ppg.mvp.view.adapter.CheckBoxSmallAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lixu on 2018/3/9.
 */

public class DatumZcmqStatusActivity extends BaseActivity{

    @BindView(R.id.ll_1)
    LinearLayout ll1;
    @BindView(R.id.ll_2)
    LinearLayout ll2;


    private List<ScreenDialogBean> cbList = new ArrayList<>();
    private CheckBoxSmallAdapter checkBoxSmallAdapter;
    private GridLayoutManager gridLayoutManager2;
    @BindView(R.id.rv_issue_info)
    RecyclerView rv1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_datum_zcmq_status;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {

        baseTitle.setText("中层或面漆施工情况");
        checkBoxSmallAdapter = new CheckBoxSmallAdapter(DatumZcmqStatusActivity.this,R.layout.item_small_checkbar,cbList);
        gridLayoutManager2 = new GridLayoutManager(DatumZcmqStatusActivity.this,3);
        rv1.setLayoutManager(gridLayoutManager2);
        rv1.setHasFixedSize(true);
        rv1.setItemAnimator(new DefaultItemAnimator());
        rv1.setAdapter(checkBoxSmallAdapter);
        initCbDataForTest();
    }

    @Override
    protected void initListener() {
        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gIntent = new Intent(DatumZcmqStatusActivity.this,DatumIssueInfoActivity.class);
                startActivity(gIntent);
            }
        });
        ll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gIntent = new Intent(DatumZcmqStatusActivity.this,DatumIssueFinishStatusActivity.class);
                startActivity(gIntent);
            }
        });

    }

    private void initCbDataForTest(){
        cbList.add(new ScreenDialogBean("均匀",true));
        cbList.add(new ScreenDialogBean("漏涂",false));
        cbList.add(new ScreenDialogBean("流坠",false));
        cbList.add(new ScreenDialogBean("掉粉",false));
        cbList.add(new ScreenDialogBean("光泽不一",false));
        cbList.add(new ScreenDialogBean("厚薄不一",false));
        cbList.add(new ScreenDialogBean("发花／透底",false));
        cbList.add(new ScreenDialogBean("反碱",false));
        cbList.add(new ScreenDialogBean("起皮",false));
        cbList.add(new ScreenDialogBean("开裂",false));
        cbList.add(new ScreenDialogBean("起泡",false));
        cbList.add(new ScreenDialogBean("针孔",false));
        cbList.add(new ScreenDialogBean("色差",false));
        cbList.add(new ScreenDialogBean("水白",false));
        cbList.add(new ScreenDialogBean("用量问题",false));
        cbList.add(new ScreenDialogBean("接痕",false));
        cbList.add(new ScreenDialogBean("干燥过快",false));
        cbList.add(new ScreenDialogBean("干燥过慢",false));
        cbList.add(new ScreenDialogBean("雨痕",false));
        checkBoxSmallAdapter.notifyDataSetChanged();
    }

}
