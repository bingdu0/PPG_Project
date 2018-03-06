package com.ppg.mvp.view.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BaseApplication;
import com.ppg.base.BasePresenter;
import com.ppg.bean.TestBean;
import com.ppg.customview.PopupWin.PopupScreenDialog;
import com.ppg.mvp.view.adapter.MyProjectAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Create by Donny.
 * 2017/12/16
 * Description：
 * 我的项目
 */
public class MyProjectActivity extends BaseActivity{
    private MyProjectAdapter projectAdapter;
    private List<TestBean> testBeanList = new ArrayList<>();
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.tv_filtrate)
    TextView tv_filtrate;


    //popupwindow相关
    private PopupWindow mcontactsBottompopup;
    private View contactBottomPopulayout;





    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_project;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        baseTitle.setText("我的项目");
        initContactsBottmoPopu();
        mcontactsBottompopup
                .setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams params = getWindow()
                                .getAttributes();
                        params.alpha = 1.0f;
                        getWindow().setAttributes(params);

                        ScaleAnimation animation = new ScaleAnimation(1f, 1f,
                                0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f,
                                Animation.RELATIVE_TO_SELF, 1.0f);
                        animation.setDuration(150);
                        animation.setFillAfter(true);
                        contactBottomPopulayout.startAnimation(animation);
                    }
                });


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(BaseApplication.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        projectAdapter=new MyProjectAdapter(R.layout.item_home,testBeanList);
        recyclerView.setAdapter(projectAdapter);


        getData();


        tv_filtrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                PopupScreenDialog popup = new PopupScreenDialog(MyProjectActivity.this);
//                popup.setDismissWhenTouchOutside(true);
//                popup.showPopupWindow();

                if (mcontactsBottompopup.isShowing()) {
                    mcontactsBottompopup.dismiss();// 关闭

                } else {
                    mcontactsBottompopup.showAtLocation(MyProjectActivity.this
                            .findViewById(R.id.tv_filtrate), Gravity.BOTTOM
                            | Gravity.CENTER_HORIZONTAL, 0, 0);

                    // 动画效果

                    WindowManager.LayoutParams params = getWindow()
                            .getAttributes();
                    params.alpha = 0.7f;
                    getWindow().setAttributes(params);

                    ScaleAnimation animation = new ScaleAnimation(1f, 1f, 0f,
                            1f, Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 1.0f);
                    animation.setDuration(150);
                    animation.setFillAfter(true);
                    contactBottomPopulayout.startAnimation(animation);
                }
            }

        });
    }

    @Override
    protected void initListener() {
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(MyProjectActivity.this,ProjectDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getData(){
        for (int i = 0; i < 19; i++) {
            testBeanList.add(new TestBean());
        }
        projectAdapter.notifyDataSetChanged();
    }



//    @OnClick({R.id.tv_filtrate,R.id.cl_time})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.cl_time:
//                break;
//          case R.id.tv_filtrate:
//              PopupScreenDialog popup = new PopupScreenDialog(MyProjectActivity.this);
//              popup.showPopupWindow();
//                break;
//
//
//
//        }
//    }



    private void initContactsBottmoPopu() {

        contactBottomPopulayout = View.inflate(this,
                R.layout.dialog_screen, null);
        mcontactsBottompopup = new PopupWindow(contactBottomPopulayout);
        mcontactsBottompopup.setContentView(contactBottomPopulayout);

        // 加上这个popupwindow中的ListView才可以接收点击事件
        mcontactsBottompopup.setWidth(LinearLayout.LayoutParams.FILL_PARENT);
        mcontactsBottompopup.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mcontactsBottompopup.setFocusable(true);
        // ColorDrawable dw = new ColorDrawable(0xb0000000);

        mcontactsBottompopup.setBackgroundDrawable(new BitmapDrawable());
        mcontactsBottompopup
                .setAnimationStyle(R.style.contactPopuAnimationExit);
        mcontactsBottompopup.setOutsideTouchable(true);

//        rl_contact_copy = (RelativeLayout) contactBottomPopulayout
//                .findViewById(R.id.rl_contact_copy);
//        rl_contact_send = (RelativeLayout) contactBottomPopulayout
//                .findViewById(R.id.rl_contact_send);
//        rl_contact_cancle = (RelativeLayout) contactBottomPopulayout
//                .findViewById(R.id.rl_contact_cancle);
//
//        rl_contact_copy.setOnClickListener(this);
//        rl_contact_send.setOnClickListener(this);
//        rl_contact_cancle.setOnClickListener(this);

    }

    private void contactBottomPopuExit() {

        ScaleAnimation animation = new ScaleAnimation(1f, 1f, 1f, 0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                1.0f);
        animation.setDuration(150);
        animation.setFillAfter(true);
        contactBottomPopulayout.startAnimation(animation);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 1.0f;
        getWindow().setAttributes(params);

        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mcontactsBottompopup.dismiss();// 关闭
            }
        });


    }

}
