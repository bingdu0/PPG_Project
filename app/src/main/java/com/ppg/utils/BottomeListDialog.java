package com.ppg.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ppg.R;
import com.ppg.bean.ScreenDialogBean;
import com.ppg.mvp.view.adapter.DialogScreenAdapter;
import com.ppg.mvp.view.adapter.MyImgAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixu on 2018/3/12.
 */

public  class BottomeListDialog {

    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }



    //popupwindow相关组件
    public static LinearLayoutManager gridLayoutManagerStatus;
    public static DialogScreenAdapter dialogScreenAdapterStatus;
    public static List<ScreenDialogBean> mScreenStatusList = new ArrayList<>();
    //popupwindow相关
    public static PopupWindow mcontactsBottompopupStatus;
    public static View contactBottomPopulayoutStatus;
    public static Context mContext;
    public static RecyclerView rvDialogPeople;

    public BottomeListDialog(){}



    public static OnItemClickListener onItemClickListener;



    public static void showTopDialog(Context context, final Activity activity,View view,int viewId, List<ScreenDialogBean> screenStatusList,OnItemClickListener monItemClickListener){
        mContext = context;
        mScreenStatusList = screenStatusList;
        onItemClickListener = monItemClickListener;
        initContactsBottmoPopuStatus(mContext);
        if (mcontactsBottompopupStatus.isShowing()) {
            mcontactsBottompopupStatus.dismiss();// 关闭
        } else {

            mcontactsBottompopupStatus.showAsDropDown(view.findViewById(viewId));
            //mcontactsBottompopupStatus.showAtLocation(rootview, Gravity.BOTTOM,0,0);

            // 动画效果
            WindowManager.LayoutParams params = activity.getWindow()
                    .getAttributes();
            params.alpha = 0.7f;    //背景
            activity.getWindow().setAttributes(params);

            ScaleAnimation animation = new ScaleAnimation(1f, 1f, 0f, 1f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                    0f);
            animation.setDuration(150);
            animation.setFillAfter(true);
            contactBottomPopulayoutStatus.startAnimation(animation);
        }



        mcontactsBottompopupStatus
                .setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams params = activity.getWindow()
                                .getAttributes();
                        params.alpha = 1.0f;
                        activity.getWindow().setAttributes(params);

                        ScaleAnimation animation = new ScaleAnimation(1f, 1f, 0f,
                                1f, Animation.RELATIVE_TO_SELF, 0.5f,
                                Animation.RELATIVE_TO_SELF, 0f);

                        animation.setDuration(150);
                        animation.setFillAfter(true);
                        contactBottomPopulayoutStatus.startAnimation(animation);
                    }
                });


        dialogScreenAdapterStatus.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(mcontactsBottompopupStatus!=null){
                    mcontactsBottompopupStatus.dismiss();
                }
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(view, position);
                }
            }
        });



    }



    public static void showBottomeDialog(Context context, final Activity activity,View rootview, List<ScreenDialogBean> screenStatusList,OnItemClickListener monItemClickListener){
        mContext = context;
        mScreenStatusList = screenStatusList;
        onItemClickListener = monItemClickListener;
        initContactsBottmoPopuStatus(mContext);
        if (mcontactsBottompopupStatus.isShowing()) {
            mcontactsBottompopupStatus.dismiss();// 关闭
        } else {
            //mcontactsBottompopupPeople.showAsDropDown(findViewById(R.id.cl_filtrate));
            mcontactsBottompopupStatus.showAtLocation(rootview, Gravity.BOTTOM,0,0);

            // 动画效果
            WindowManager.LayoutParams params = activity.getWindow()
                    .getAttributes();
            params.alpha = 0.7f;    //背景
            activity.getWindow().setAttributes(params);

            ScaleAnimation animation = new ScaleAnimation(1f, 1f, 0f, 1f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                    1f);
            animation.setDuration(150);
            animation.setFillAfter(true);
            contactBottomPopulayoutStatus.startAnimation(animation);
        }



        mcontactsBottompopupStatus
                .setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams params = activity.getWindow()
                                .getAttributes();
                        params.alpha = 1.0f;
                        activity.getWindow().setAttributes(params);

                        ScaleAnimation animation = new ScaleAnimation(1f, 1f, 0f,
                                1f, Animation.RELATIVE_TO_SELF, 0.5f,
                                Animation.RELATIVE_TO_SELF, 0f);

                        animation.setDuration(150);
                        animation.setFillAfter(true);
                        contactBottomPopulayoutStatus.startAnimation(animation);
                    }
                });


        dialogScreenAdapterStatus.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(mcontactsBottompopupStatus!=null){
                    mcontactsBottompopupStatus.dismiss();
                }
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(view, position);
                }
            }
        });



    }

    private static void initContactsBottmoPopuStatus(Context context) {

        contactBottomPopulayoutStatus = View.inflate(context,
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
        dialogScreenAdapterStatus = new DialogScreenAdapter(mContext,R.layout.item_bottome_list,mScreenStatusList,false);
        gridLayoutManagerStatus = new LinearLayoutManager(mContext);
        rvDialogPeople.setLayoutManager(gridLayoutManagerStatus);
        rvDialogPeople.setHasFixedSize(true);
        rvDialogPeople.setItemAnimator(new DefaultItemAnimator());
        rvDialogPeople.setAdapter(dialogScreenAdapterStatus);

        //dialogScreenAdapterStatus.notifyDataSetChanged();

    }
}
