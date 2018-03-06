package com.ppg.mvp.view.fragment;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.ppg.R;
import com.ppg.base.BaseFragment;
import com.ppg.base.BasePresenter;
import com.ppg.mvp.view.activity.LoginActivity;
import com.ppg.mvp.view.activity.MyProjectActivity;
import com.ppg.mvp.view.activity.UserManageActivity;
import com.ppg.mvp.view.activity.UserMsgActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Create by Donny.
 * 2017/12/14
 * Description：
 */
public class UserFragment extends BaseFragment {
    private static UserFragment userFragment;
    @BindView(R.id.cl_user_msg)
    ConstraintLayout clUserMsg;
    @BindView(R.id.cl_pj)
    ConstraintLayout clPj;
    @BindView(R.id.cl_user_manage)
    ConstraintLayout clUserManage;
    @BindView(R.id.exit_login)
    Button ivLogin;



    public static UserFragment getInstance() {
        return userFragment == null ? new UserFragment() : userFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        baseTitle.setText("个人中心");

    }

    @Override
    protected void initListener() {

    }

    @OnClick({R.id.cl_user_msg, R.id.cl_pj, R.id.cl_user_manage,R.id.btn_add,R.id.exit_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cl_user_msg:
                Intent intent2=new Intent(getActivity(), UserMsgActivity.class);
                startActivity(intent2);
                break;
            case R.id.cl_pj:
                Intent intent1=new Intent(getActivity(), MyProjectActivity.class);
                startActivity(intent1);
                break;
            case R.id.cl_user_manage:
                Intent intent=new Intent(getActivity(), UserManageActivity.class);
                startActivity(intent);
                break;
            case R.id.exit_login:
                Intent intent3=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent3);
                getActivity().finish();
                break;

        }
    }
}
