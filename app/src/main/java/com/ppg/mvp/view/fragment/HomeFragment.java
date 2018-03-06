package com.ppg.mvp.view.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ppg.R;
import com.ppg.base.BaseApplication;
import com.ppg.base.BaseFragment;
import com.ppg.base.BasePresenter;
import com.ppg.bean.TestBean;
import com.ppg.mvp.view.activity.ProjectDetailActivity;
import com.ppg.mvp.view.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Create by Donny.
 * 2017/12/14
 * Description：
 */
public class HomeFragment extends BaseFragment {
    private static HomeFragment honeFragment;
    private HomeAdapter homeAdapter;
    private List<TestBean> testBeanList = new ArrayList<>();
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    public static HomeFragment getInstance() {
        return honeFragment == null ? new HomeFragment() : honeFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        baseTitle.setText("当前项目");
        btnMore.setVisibility(View.VISIBLE);
        btnBaseBarBack.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BaseApplication.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        homeAdapter = new HomeAdapter(R.layout.item_home, testBeanList);
        recyclerView.setAdapter(homeAdapter);
        getData();


    }

    @Override
    protected void initListener() {
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(getActivity(),ProjectDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        for (int i = 0; i < 19; i++) {
            testBeanList.add(new TestBean());
        }
        homeAdapter.notifyDataSetChanged();
    }

}
