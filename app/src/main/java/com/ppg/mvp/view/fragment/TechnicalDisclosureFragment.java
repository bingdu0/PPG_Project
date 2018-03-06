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
import com.ppg.mvp.view.activity.ReportTeDisRActivity;
import com.ppg.mvp.view.adapter.TeDisAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Create by Donny.
 * 2017/12/14
 * Description：
 * 技术交底记录
 */
public class TechnicalDisclosureFragment extends BaseFragment {
    private static TechnicalDisclosureFragment fragment;

    private List<TestBean> testBeanList4 = new ArrayList<>();
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private TeDisAdapter mAdapter;

    public static TechnicalDisclosureFragment getInstance() {
        return fragment == null ? new TechnicalDisclosureFragment() : fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_technical_disclosure;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BaseApplication.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new TeDisAdapter(R.layout.item_te_dis, testBeanList4);
        recyclerView.setAdapter(mAdapter);
        getData();
    }

    @Override
    protected void initListener() {
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(getActivity(), ReportTeDisRActivity.class);
                startActivity(intent);
            }
        });

    }
    private void getData() {
        testBeanList4.clear();
        for (int i = 0; i < 3; i++) {
            testBeanList4.add(new TestBean());
        }
        mAdapter.notifyDataSetChanged();
    }
}
