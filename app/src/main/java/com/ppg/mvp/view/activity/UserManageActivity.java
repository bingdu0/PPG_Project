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
import com.ppg.mvp.view.adapter.UserManageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Create by Donny.
 * 2017/12/16
 * Description：
 * 用户管理
 */
public class UserManageActivity extends BaseActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private UserManageAdapter manageAdapter;
    private List<TestBean> testBeanList = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_manage;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        baseTitle.setText("用户管理");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BaseApplication.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        manageAdapter = new UserManageAdapter(R.layout.item_user_manage, testBeanList);
        recyclerView.setAdapter(manageAdapter);

        getData();
    }

    @Override
    protected void initListener() {
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(UserManageActivity.this,UserMsgActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        for (int i = 0; i < 19; i++) {
            testBeanList.add(new TestBean());
        }
        manageAdapter.notifyDataSetChanged();
    }

}
