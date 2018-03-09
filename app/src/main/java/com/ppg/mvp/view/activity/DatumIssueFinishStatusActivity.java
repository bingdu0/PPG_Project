package com.ppg.mvp.view.activity;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BasePresenter;
import com.ppg.bean.TestBean;
import com.ppg.mvp.view.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lixu on 2018/3/9.
 * 上次巡检问题落实情况
 */

public class DatumIssueFinishStatusActivity extends BaseActivity {
    private HomeAdapter homeAdapter;
    private LinearLayoutManager linearLayoutManager;

    private List<TestBean> testBeans = new ArrayList<>();

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_datum_issue_list;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        baseTitle.setText("上次巡检问题落实情况");
        baseTvRight.setText("新增");
        baseTvRight.setVisibility(View.VISIBLE);

        testBeans.add(new TestBean());
        testBeans.add(new TestBean());

        homeAdapter = new HomeAdapter(R.layout.item_datum_issue_finish_status, testBeans);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(homeAdapter);


    }

    @Override
    protected void initListener() {
        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent gIntent = new Intent(DatumIssueFinishStatusActivity.this, IssueInfoActivity.class);
                startActivity(gIntent);
            }
        });
    }
}
