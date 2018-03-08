package com.ppg.mvp.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BasePresenter;
import com.ppg.constants.Constant;
import com.ppg.utils.PopupWindowUtil;
import com.ppg.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.editText2)
    EditText editText2;

    private String editTitle;
    private String editHint;
    @BindView(R.id.tv_sp)
    TextView tv_sp;
    @BindView(R.id.tv_1)
    TextView tv_1;
    @BindView(R.id.tv_2)
    TextView tv_2;



    @BindView(R.id.ll_spinner)
    LinearLayout ll_spinner;

    private String mType;


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

        mType = editTitle;
        baseTitle.setText(editTitle);
        editText.setHint(editHint);

        if(!StringUtils.isEmpty(mType) && mType.equals("施工建议&配合措施")){
            ll_spinner.setVisibility(View.GONE);
            editText2.setVisibility(View.VISIBLE);
            tv_1.setVisibility(View.VISIBLE);
            tv_2.setVisibility(View.VISIBLE);
            editText.setHint("请输入如何配置控制内容");
        }
    }

    @Override
    protected void initListener() {
        ll_spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPopupWindow(view);
            }
        });
    }


    @OnClick({R.id.btn_01})
    public void onViewClicked() {
        finish();
    }

    public void setPopupWindow(View mView) {
        final List<String> items = new ArrayList<>();
        items.add("基面处理");
        items.add("施工工序");
        items.add("其他");

        // 点击控件后显示popup窗口
        final PopupWindowUtil popupWindow = new PopupWindowUtil(EditActivity.this, items);

        popupWindow.setItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupWindow.dismiss();
                tv_sp.setText(items.get(position));

            }
        });
        //根据后面的数字 手动调节窗口的宽度
        popupWindow.show(mView, 1);
    }
}
