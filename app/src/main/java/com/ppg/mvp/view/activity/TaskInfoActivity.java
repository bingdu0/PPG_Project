package com.ppg.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BasePresenter;
import com.ppg.bean.ScreenDialogBean;
import com.ppg.mvp.view.adapter.DialogScreenAdapter;
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
    @BindView(R.id.tv_progress)
    TextView tv_progress;

    @BindView(R.id.ll_progress)
    LinearLayout ll_progress;


    //popupwindow相关组件
    RecyclerView rvDialogPeople;

    private LinearLayoutManager gridLayoutManagerPeople;

    private DialogScreenAdapter dialogScreenAdapterPeople;
    private List<ScreenDialogBean> screenPeopleList = new ArrayList<>();

    //popupwindow相关
    private PopupWindow mcontactsBottompopupPeople;
    private View contactBottomPopulayoutPeople;
    private Context mContext;
    private View rootview ;
    
    //==============================================================================================
    
    //popupwindow相关组件
    private LinearLayoutManager gridLayoutManagerStatus;
    private DialogScreenAdapter dialogScreenAdapterStatus;
    private List<ScreenDialogBean> screenStatusList = new ArrayList<>();
    //popupwindow相关
    private PopupWindow mcontactsBottompopupStatus;
    private View contactBottomPopulayoutStatus;
    
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
        this.mContext = TaskInfoActivity.this;
        rootview = LayoutInflater.from(mContext).inflate(R.layout.activity_task_info, null);

        tvTitle.setText("任务详情");
        tv_rest.setVisibility(View.VISIBLE);
        initContactsBottmoPopu();
        initContactsBottmoPopuStatus();

    }

    @Override
    protected void initListener() {

        //负责人
        dialogScreenAdapterPeople.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(mcontactsBottompopupPeople!=null){
                    mcontactsBottompopupPeople.dismiss();
                }
                tv_person.setText(screenPeopleList.get(position).getText());

            }
        });
        mcontactsBottompopupPeople
                .setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams params = getWindow()
                                .getAttributes();
                        params.alpha = 1.0f;
                        getWindow().setAttributes(params);

                        ScaleAnimation animation = new ScaleAnimation(1f, 1f, 0f,
                                1f, Animation.RELATIVE_TO_SELF, 0.5f,
                                Animation.RELATIVE_TO_SELF, 0f);

                        animation.setDuration(150);
                        animation.setFillAfter(true);
                        contactBottomPopulayoutPeople.startAnimation(animation);
                    }
                });

        //进度
        dialogScreenAdapterStatus.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(mcontactsBottompopupStatus!=null){
                    mcontactsBottompopupStatus.dismiss();
                }
                tv_progress.setText(screenStatusList.get(position).getText());

            }
        });
        mcontactsBottompopupStatus
                .setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams params = getWindow()
                                .getAttributes();
                        params.alpha = 1.0f;
                        getWindow().setAttributes(params);

                        ScaleAnimation animation = new ScaleAnimation(1f, 1f, 0f,
                                1f, Animation.RELATIVE_TO_SELF, 0.5f,
                                Animation.RELATIVE_TO_SELF, 0f);

                        animation.setDuration(150);
                        animation.setFillAfter(true);
                        contactBottomPopulayoutStatus.startAnimation(animation);
                    }
                });

    }


    @OnClick({R.id.ll_issue_info, R.id.ll_cause_analyze, R.id.ll_idea, R.id.ll_ps,R.id.tv_person,R.id.tv_person2,R.id.ll_progress})
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
                if (mcontactsBottompopupPeople.isShowing()) {
                    mcontactsBottompopupPeople.dismiss();// 关闭
                } else {
                    //mcontactsBottompopupPeople.showAsDropDown(findViewById(R.id.cl_filtrate));
                    mcontactsBottompopupPeople.showAtLocation(rootview, Gravity.BOTTOM,0,0);
                    // 动画效果
                    WindowManager.LayoutParams params = getWindow()
                            .getAttributes();
                    params.alpha = 0.7f;    //背景
                    getWindow().setAttributes(params);

                    ScaleAnimation animation = new ScaleAnimation(1f, 1f, 0f, 1f,
                            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                            1f);
                    animation.setDuration(150);
                    animation.setFillAfter(true);
                    contactBottomPopulayoutPeople.startAnimation(animation);
                }
                break;
            case R.id.ll_progress:
                if (mcontactsBottompopupStatus.isShowing()) {
                    mcontactsBottompopupStatus.dismiss();// 关闭
                } else {
                    //mcontactsBottompopupPeople.showAsDropDown(findViewById(R.id.cl_filtrate));
                    mcontactsBottompopupStatus.showAtLocation(rootview, Gravity.BOTTOM,0,0);
                    // 动画效果
                    WindowManager.LayoutParams params = getWindow()
                            .getAttributes();
                    params.alpha = 0.7f;    //背景
                    getWindow().setAttributes(params);

                    ScaleAnimation animation = new ScaleAnimation(1f, 1f, 0f, 1f,
                            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                            1f);
                    animation.setDuration(150);
                    animation.setFillAfter(true);
                    contactBottomPopulayoutStatus.startAnimation(animation);
                }
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


    private void initContactsBottmoPopu() {

        contactBottomPopulayoutPeople = View.inflate(this,
                R.layout.dialog_bottome_list, null);

        rvDialogPeople = contactBottomPopulayoutPeople.findViewById(R.id.rv_dialog_status);

        mcontactsBottompopupPeople = new PopupWindow(contactBottomPopulayoutPeople);
        mcontactsBottompopupPeople.setContentView(contactBottomPopulayoutPeople);

        // 加上这个popupwindow中的ListView才可以接收点击事件
        mcontactsBottompopupPeople.setWidth(LinearLayout.LayoutParams.FILL_PARENT);
        mcontactsBottompopupPeople.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mcontactsBottompopupPeople.setFocusable(true);
        // ColorDrawable dw = new ColorDrawable(0xb0000000);

        mcontactsBottompopupPeople.setBackgroundDrawable(new BitmapDrawable());
        mcontactsBottompopupPeople
                .setAnimationStyle(R.style.contactPopuAnimationExit);
        mcontactsBottompopupPeople.setOutsideTouchable(true);


        //项目状态初始化
        dialogScreenAdapterPeople = new DialogScreenAdapter(mContext,R.layout.item_bottome_list,screenPeopleList,false);
        gridLayoutManagerPeople = new LinearLayoutManager(mContext);
        rvDialogPeople.setLayoutManager(gridLayoutManagerPeople);
        rvDialogPeople.setHasFixedSize(true);
        rvDialogPeople.setItemAnimator(new DefaultItemAnimator());
        rvDialogPeople.setAdapter(dialogScreenAdapterPeople);


        screenPeopleList.add(new ScreenDialogBean("张三",false));
        screenPeopleList.add(new ScreenDialogBean("王老虎",false));
        screenPeopleList.add(new ScreenDialogBean("李鬼",false));
        screenPeopleList.add(new ScreenDialogBean("孙悟空",false));
        screenPeopleList.add(new ScreenDialogBean("杨宝老",false));
        dialogScreenAdapterPeople.notifyDataSetChanged();

    }

    private void initContactsBottmoPopuStatus() {

        contactBottomPopulayoutStatus = View.inflate(this,
                R.layout.dialog_bottome_list, null);

        rvDialogPeople = contactBottomPopulayoutStatus.findViewById(R.id.rv_dialog_status);

        mcontactsBottompopupStatus = new PopupWindow(contactBottomPopulayoutStatus);
        mcontactsBottompopupStatus.setContentView(contactBottomPopulayoutStatus);

        // 加上这个popupwindow中的ListView才可以接收点击事件
        mcontactsBottompopupStatus.setWidth(LinearLayout.LayoutParams.FILL_PARENT);
        mcontactsBottompopupStatus.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mcontactsBottompopupStatus.setFocusable(true);
        // ColorDrawable dw = new ColorDrawable(0xb0000000);

        mcontactsBottompopupStatus.setBackgroundDrawable(new BitmapDrawable());
        mcontactsBottompopupStatus
                .setAnimationStyle(R.style.contactPopuAnimationExit);
        mcontactsBottompopupStatus.setOutsideTouchable(true);


        //项目状态初始化
        dialogScreenAdapterStatus = new DialogScreenAdapter(mContext,R.layout.item_bottome_list,screenStatusList,false);
        gridLayoutManagerStatus = new LinearLayoutManager(mContext);
        rvDialogPeople.setLayoutManager(gridLayoutManagerStatus);
        rvDialogPeople.setHasFixedSize(true);
        rvDialogPeople.setItemAnimator(new DefaultItemAnimator());
        rvDialogPeople.setAdapter(dialogScreenAdapterStatus);


        screenStatusList.add(new ScreenDialogBean("取消",false));
        screenStatusList.add(new ScreenDialogBean("完成",false));
        screenStatusList.add(new ScreenDialogBean("结束",false));
        screenStatusList.add(new ScreenDialogBean("关闭",false));
        dialogScreenAdapterStatus.notifyDataSetChanged();

    }



}
