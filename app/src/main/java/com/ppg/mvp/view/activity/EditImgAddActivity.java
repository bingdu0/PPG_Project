package com.ppg.mvp.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BasePresenter;
import com.ppg.constants.Constant;
import com.ppg.customview.PopupWin.PopupWinFromToBo;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Create by Donny.
 * 2017/12/18
 * Description：
 * 编辑提交的页面
 */
public class EditImgAddActivity extends BaseActivity {
    @BindView(R.id.btn_01)
    Button btn01;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.imageView4)
    ImageView imageView4;

    private String editTitle;
    private String editHint;

    @Override
    protected int getLayoutId() {
        return R.layout.avtivity_edit_img_add;
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


    @OnClick({R.id.imageView4, R.id.btn_01})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView4:
                PopupWinFromToBo popupWinFromToBo=new PopupWinFromToBo(this);
                popupWinFromToBo.showPopupWindow();
                break;
            case R.id.btn_01:
                break;
        }
    }
}
