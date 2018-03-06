package com.ppg.mvp.view.activity;

import android.Manifest;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BasePresenter;
import com.ppg.utils.ToastUtil;

import butterknife.BindView;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * Create by Donny.
 * 2017/12/15
 * Description：
 */
@RuntimePermissions
public class LoginActivity extends BaseActivity {

    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    @NeedsPermission(Manifest.permission.CAMERA)
    void openSDCardPermission() {

    }

    //如果用户取消权限,每次启动本类都会提示。(此方法非必须有)
    @OnShowRationale(Manifest.permission.CAMERA)
    void showWhy(final PermissionRequest request) {
    }


    //一旦用户拒绝了SDcard卡(此方法非必须有)
    @OnPermissionDenied(Manifest.permission.CAMERA)
    void repulseSDcard() {
        ToastUtil.showShort("没有相机权限");
    }

    //用户选择的不再询问SDcard权限(此方法非必须有)
    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void noSDcard() {
        ToastUtil.showShort("没有相机权限");
    }


}
