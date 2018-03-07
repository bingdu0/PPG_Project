package com.ppg.mvp.view.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ppg.R;
import com.ppg.base.BaseApplication;
import com.ppg.base.BaseFragment;
import com.ppg.base.BasePresenter;
import com.ppg.bean.TestBean;
import com.ppg.mvp.view.activity.IssueInfoActivity;
import com.ppg.mvp.view.adapter.ProblemManageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by LiuKai on 2018/3/7.
 */

public class ProblemManageFragment extends BaseFragment {
    private static ProblemManageFragment mProblemManageFragment;
    private ProblemManageAdapter mProblemManageAdapter;    //问题管理的适配器
    private List<TestBean> testBeanList=new ArrayList<>();


    @BindView(R.id.rv_problem_manager)
    RecyclerView rvProblemManager;

    public static ProblemManageFragment getInstance() {
        return mProblemManageFragment == null ? new ProblemManageFragment() : mProblemManageFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_problem;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {

        baseTitle.setText("问题管理");
        mProblemManageAdapter = new ProblemManageAdapter(R.layout.item_problem,testBeanList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BaseApplication.getContext());

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvProblemManager.setLayoutManager(linearLayoutManager);
        rvProblemManager.setAdapter(mProblemManageAdapter);
        getData();

    }

    @Override
    protected void initListener() {
        mProblemManageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent iIntent = new Intent(getActivity(), IssueInfoActivity.class);
                startActivity(iIntent);
            }
        });
    }

    private void getData() {
        for (int i = 0; i < 19; i++) {
            testBeanList.add(new TestBean());
        }
        mProblemManageAdapter.notifyDataSetChanged();
    }
}
