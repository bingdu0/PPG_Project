package com.ppg.mvp.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BasePresenter;
import com.ppg.utils.PopupWindowUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lixu on 2018/3/7.
 */

public class TaskInfoActivity extends BaseActivity {

    @BindView(R.id.ll_issue_info)
    LinearLayout ll_issue_info;

    @BindView(R.id.ll_cause_analyze)
    LinearLayout ll_cause_analyze;

    @BindView(R.id.ll_idea)
    LinearLayout ll_idea;

    @BindView(R.id.ll_ps)
    LinearLayout ll_ps;

    @BindView(R.id.tv_person)
    TextView tv_person;
    @BindView(R.id.tv_person2)
    TextView tv_person2;



    @BindView(R.id.base_title)
    TextView tvTitle;
@BindView(R.id.tv_rest)
    TextView tv_rest;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_task_info;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        tvTitle.setText("任务详情");
        tv_rest.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initListener() {

    }


    @OnClick({R.id.ll_issue_info, R.id.ll_cause_analyze, R.id.ll_idea, R.id.ll_ps,R.id.tv_person,R.id.tv_person2})
    public void onViewClicked(View view) {
        Intent gIntent = new Intent(TaskInfoActivity.this,OtherInfoActivity.class);
        switch (view.getId()) {
            case R.id.ll_issue_info:
                gIntent.putExtra("type","问题描述");
                startActivity(gIntent);
                break;
            case R.id.ll_cause_analyze:
                gIntent.putExtra("type","执行过程描述");
                startActivity(gIntent);
                break;
            case R.id.ll_idea:
                gIntent.putExtra("type","行动方案");
                startActivity(gIntent);
                break;
            case R.id.ll_ps:
                gIntent.putExtra("type","验收结果");
                startActivity(gIntent);
                break;
            case R.id.tv_person:
                setPopupWindow(view);
                break;


        }



    }
    public void setPopupWindow(View mView){
        final List<String> items = new ArrayList<>();
        items.add("张三");
        items.add("王老虎");
        items.add("李鬼");
        items.add("孙悟空");
        items.add("杨宝老");

        // 点击控件后显示popup窗口
        final PopupWindowUtil popupWindow = new PopupWindowUtil(TaskInfoActivity.this, items);

        popupWindow.setItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupWindow.dismiss();
                tv_person.setText(items.get(position));

            }
        });
        //根据后面的数字 手动调节窗口的宽度
        popupWindow.show(mView, 1);
    }
}
