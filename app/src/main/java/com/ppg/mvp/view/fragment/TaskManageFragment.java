package com.ppg.mvp.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ppg.R;
import com.ppg.base.BaseApplication;
import com.ppg.base.BaseFragment;
import com.ppg.base.BasePresenter;
import com.ppg.bean.TestBean;
import com.ppg.mvp.view.adapter.TaskManageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by LiuKai on 2018/3/6.
 */

public class TaskManageFragment extends BaseFragment {
    private static TaskManageFragment taskManageFragment;
    private TaskManageAdapter mTaskManageAdapter;
    private List<TestBean> testBeanList = new ArrayList<>();

    @BindView(R.id.rv_task_manager)
    RecyclerView rvTaskManager;

    public static TaskManageFragment getInstance() {
        return taskManageFragment == null ? new TaskManageFragment() : taskManageFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_task_manager;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        baseTitle.setText("任务管理");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BaseApplication.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvTaskManager.setLayoutManager(linearLayoutManager);
        mTaskManageAdapter = new TaskManageAdapter(R.layout.item_task, testBeanList);
        rvTaskManager.setAdapter(mTaskManageAdapter);
        getData();

    }

    @Override
    protected void initListener() {

    }

    private void getData() {
        for (int i = 0; i < 19; i++) {
            testBeanList.add(new TestBean());
        }
        mTaskManageAdapter.notifyDataSetChanged();
    }
}
