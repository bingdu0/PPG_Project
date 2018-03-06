package com.ppg.mvp.view.activity;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BasePresenter;
import com.ppg.customview.FragmentTabHost;
import com.ppg.utils.FragmentKeyUtil;

public class MainActivity extends BaseActivity {

    private String mTitle[] = new String[]{"项目管理", "任务管理", "问题管理", "个人中心"};
    // 定义数组来存放按钮图片
    private int mImageViewArray[] = {R.mipmap.icon_project_manage,R.mipmap.icon_task_manage,R.mipmap.icon_problem_manage,R.mipmap.icon_personal_manage};
    private FragmentTabHost mTabHost;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }


    @Override
    protected void initData() {
        mTabHost = findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.getTabWidget().setDividerDrawable(null);//去除分割线
        FragmentKeyUtil.initFragment();
        for (int postion = 0; postion < mTitle.length; postion++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTitle[postion]).setIndicator(getTabItemView(postion));
            // 将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, FragmentKeyUtil.getFragment(postion).getClass(), null);
        }
        mTabHost.getTabWidget().setBackgroundColor(getResources().getColor(R.color.white));
    }

    @Override
    protected void initListener() {

    }

    /**
     * 给Tab按钮设置图标和文字
     */
    @SuppressLint("ResourceType")
    private View getTabItemView(int index) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_tab_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[index]);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTitle[index]);
        textView.setTextColor(getResources().getColorStateList(R.drawable.selector_color_tabhost));
        return view;
    }
}
