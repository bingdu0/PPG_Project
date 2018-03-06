package com.ppg.mvp.view.activity;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BasePresenter;
import com.ppg.constants.Constant;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Create by Donny.
 * 2017/12/18
 * Description：
 * 编辑提交的页面
 */
public class EditActivity extends BaseActivity {
    @BindView(R.id.btn_01)
    Button btn01;
    @BindView(R.id.editText)
    EditText editText;

    private String editTitle;
    private String editHint;

    @Override
    protected int getLayoutId() {
        return R.layout.avtivity_edit;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        editTitle = intent.getStringExtra(Constant.EDIT_TITLE);
        editHint = intent.getStringExtra(Constant.EDIT_HINT);

        baseTitle.setText(editTitle);
        editText.setHint(editHint);
    }

    @Override
    protected void initListener() {

    }


    @OnClick(R.id.btn_01)
    public void onViewClicked() {
        finish();
    }
}
