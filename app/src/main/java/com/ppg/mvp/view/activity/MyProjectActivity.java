package com.ppg.mvp.view.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ppg.R;
import com.ppg.mvp.view.adapter.DialogScreenAdapter;
import com.ppg.base.BaseActivity;
import com.ppg.base.BaseApplication;
import com.ppg.base.BasePresenter;
import com.ppg.bean.ScreenDialogBean;
import com.ppg.bean.TestBean;
import com.ppg.mvp.view.adapter.MyProjectAdapter;
import com.ppg.utils.PopupWindowUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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
    @BindView(R.id.tv_time)
    TextView tv_time;



    //popupwindow相关组件
    RecyclerView rvDialogStatus;
    RecyclerView rvDialogPeople;

    private GridLayoutManager gridLayoutManagerStatus;
    private GridLayoutManager gridLayoutManagerPeople;

    private DialogScreenAdapter dialogScreenAdapterStatus;
    private List<ScreenDialogBean> screenStatusList = new ArrayList<>();


    private DialogScreenAdapter dialogScreenAdapterPeople;
    private List<ScreenDialogBean> screenPeopleList = new ArrayList<>();


    //popupwindow相关
    private PopupWindow mcontactsBottompopup;
    private View contactBottomPopulayout;
    private TextView bt_popup_rest;
    private TextView bt_popup_finish;





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
        initAllView();
        getData();


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

        mcontactsBottompopup
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
                        contactBottomPopulayout.startAnimation(animation);
                    }
                });

        dialogScreenAdapterStatus.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                screenStatusList.get(position).setSelect(!screenStatusList.get(position).isSelect());
                dialogScreenAdapterStatus.notifyDataSetChanged();

            }
        });
        dialogScreenAdapterPeople.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                screenPeopleList.get(position).setSelect(!screenPeopleList.get(position).isSelect());
                dialogScreenAdapterPeople.notifyDataSetChanged();

            }
        });

        tv_filtrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mcontactsBottompopup.isShowing()) {
                    mcontactsBottompopup.dismiss();// 关闭
                } else {
                    mcontactsBottompopup.showAsDropDown(findViewById(R.id.cl_filtrate));
                    // 动画效果
                    WindowManager.LayoutParams params = getWindow()
                            .getAttributes();
                    params.alpha = 0.7f;
                    getWindow().setAttributes(params);

                    ScaleAnimation animation = new ScaleAnimation(1f, 1f, 0f, 1f,
                            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                            0f);
                    animation.setDuration(150);
                    animation.setFillAfter(true);
                    contactBottomPopulayout.startAnimation(animation);
                }
            }

        });
        tv_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPopupWindow(view);
            }

        });

        bt_popup_rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0;i < screenStatusList.size();i++){
                    screenStatusList.get(i).setSelect(false);
                }
                for(int i = 0;i < screenPeopleList.size();i++){
                    screenPeopleList.get(i).setSelect(false);
                }
                dialogScreenAdapterStatus.notifyDataSetChanged();
                dialogScreenAdapterPeople.notifyDataSetChanged();
            }
        });
        bt_popup_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mcontactsBottompopup != null) {
                    if (mcontactsBottompopup.isShowing()) {
                        mcontactsBottompopup.dismiss();// 关闭
                    }
                }

            }
        });

    }


    private void initAllView(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(BaseApplication.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        projectAdapter=new MyProjectAdapter(R.layout.item_home,testBeanList);
        recyclerView.setAdapter(projectAdapter);

        //项目状态初始化
        dialogScreenAdapterStatus = new DialogScreenAdapter(MyProjectActivity.this,R.layout.item_dialog_screen,screenStatusList,true);
        gridLayoutManagerStatus = new GridLayoutManager(MyProjectActivity.this,3);
        rvDialogStatus.setLayoutManager(gridLayoutManagerStatus);
        rvDialogStatus.setHasFixedSize(true);
        rvDialogStatus.setItemAnimator(new DefaultItemAnimator());
        rvDialogStatus.setAdapter(dialogScreenAdapterStatus);

        //项目负责人初始化
        dialogScreenAdapterPeople = new DialogScreenAdapter(MyProjectActivity.this,R.layout.item_dialog_screen,screenPeopleList,true);
        gridLayoutManagerPeople = new GridLayoutManager(MyProjectActivity.this,3);
        rvDialogPeople.setLayoutManager(gridLayoutManagerPeople);
        rvDialogPeople.setHasFixedSize(true);
        rvDialogPeople.setItemAnimator(new DefaultItemAnimator());
        rvDialogPeople.setAdapter(dialogScreenAdapterPeople);
    }

    private void getData(){
        for (int i = 0; i < 19; i++) {
            testBeanList.add(new TestBean());
        }
        projectAdapter.notifyDataSetChanged();

        screenStatusList.add(new ScreenDialogBean("新建",false));
        screenStatusList.add(new ScreenDialogBean("施工中",false));
        screenStatusList.add(new ScreenDialogBean("已完成",false));
        screenStatusList.add(new ScreenDialogBean("已验收",false));
        screenStatusList.add(new ScreenDialogBean("已作废",false));
        dialogScreenAdapterStatus.notifyDataSetChanged();

        screenPeopleList.add(new ScreenDialogBean("袁军",false));
        screenPeopleList.add(new ScreenDialogBean("魏国西",false));
        screenPeopleList.add(new ScreenDialogBean("任杰",false));
        screenPeopleList.add(new ScreenDialogBean("曾海波",false));
        screenPeopleList.add(new ScreenDialogBean("陈学军",false));
        dialogScreenAdapterPeople.notifyDataSetChanged();
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

         rvDialogStatus = contactBottomPopulayout.findViewById(R.id.rv_dialog_status);
        rvDialogPeople = contactBottomPopulayout.findViewById(R.id.rv_dialog_people);

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

        bt_popup_rest = contactBottomPopulayout
                .findViewById(R.id.bt_popup_rest);

        bt_popup_finish = contactBottomPopulayout
                .findViewById(R.id.bt_popup_finish);
    }

    public void setPopupWindow(View mView){
        final List<String> items = new ArrayList<>();
        items.add("时间顺序");
        items.add("项目负责人");

        // 点击控件后显示popup窗口
        final PopupWindowUtil popupWindow = new PopupWindowUtil(MyProjectActivity.this, items);

        popupWindow.setItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupWindow.dismiss();
                tv_time.setText(items.get(position));

            }
        });
        //根据后面的数字 手动调节窗口的宽度
        popupWindow.show(mView, 2);
    }
}
