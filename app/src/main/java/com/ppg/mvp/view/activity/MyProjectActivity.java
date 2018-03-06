package com.ppg.mvp.view.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BaseApplication;
import com.ppg.base.BasePresenter;
import com.ppg.bean.TestBean;
import com.ppg.mvp.view.adapter.MyProjectAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Create by Donny.
 * 2017/12/16
 * Description：
 * 我的项目
 */
public class MyProjectActivity extends BaseActivity{
    private MyProjectAdapter projectAdapter;
    private List<TestBean> testBeanList = new ArrayList<>();
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_project;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        baseTitle.setText("我的项目");
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(BaseApplication.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        projectAdapter=new MyProjectAdapter(R.layout.item_home,testBeanList);
        recyclerView.setAdapter(projectAdapter);
        getData();
    }

    @Override
    protected void initListener() {
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(MyProjectActivity.this,ProjectDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getData(){
        for (int i = 0; i < 19; i++) {
            testBeanList.add(new TestBean());
        }
        projectAdapter.notifyDataSetChanged();
    }
}
