package com.ppg.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;

import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BasePresenter;
import com.ppg.mvp.view.adapter.ProjectDetailAdapter;
import com.ppg.mvp.view.fragment.HandlingComplaintFragment;
import com.ppg.mvp.view.fragment.PrejectMagFragment;
import com.ppg.mvp.view.fragment.ServiceReportFragment;
import com.ppg.mvp.view.fragment.SpreadingRateFragment;
import com.ppg.mvp.view.fragment.TechnicalDisclosureFragment;
import com.ppg.mvp.view.fragment.TempletReportFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;

/**
 * Create by Donny.
 * 2017/12/17
 * Description：
 * 项目详情
 */
public class ProjectDetailActivity extends BaseActivity implements View.OnClickListener{
    private ViewPager mVpContent;
    private MagicIndicator tabs;
    private ProjectDetailAdapter mDetailAdapter;
    private String[] detailName = {"项目信息", "技术交底", "投诉处理", "涂布率测试", "现场服务报告", "现场样板报告"};
    private CommonNavigator mCommonNavigator;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_project_detail;
    }

    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        baseTitle.setText("项目详情");
        btnAdd.setVisibility(View.VISIBLE);
        mVpContent = findViewById(R.id.vp_content);
        tabs = findViewById(R.id.tabs);
        mVpContent.setOffscreenPageLimit(1);
        initDetailList(tabs, mVpContent);
    }

    @Override
    protected void initListener() {
        btnAdd.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btn_add:
            Intent intent=new Intent(this,AddActivity.class);
            startActivity(intent);
            break;

        default:
            break;
        }

    }

    private void initDetailList(MagicIndicator tabs, final ViewPager mVpContent) {
        final ArrayList<Fragment> mDetailFragmentList = new ArrayList<>();
        //项目信息
        mDetailFragmentList.add(PrejectMagFragment.getInstance());
        //技术交底记录
        mDetailFragmentList.add(TechnicalDisclosureFragment.getInstance());
        //投诉处理
        mDetailFragmentList.add(HandlingComplaintFragment.getInstance());
        //涂布率测试
        mDetailFragmentList.add(SpreadingRateFragment.getInstance());
        //现场服务报告
        mDetailFragmentList.add(ServiceReportFragment.getInstance());
        //现场样板报告
        mDetailFragmentList.add(TempletReportFragment.getInstance());


        mDetailAdapter = new ProjectDetailAdapter(getSupportFragmentManager(), mDetailFragmentList, detailName);
        mVpContent.setAdapter(mDetailAdapter);

        mCommonNavigator = new CommonNavigator(this);
        mCommonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return detailName.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                simplePagerTitleView.setText(detailName[index]);
                //原色
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.white));
                //选择后的颜色
                simplePagerTitleView.setSelectedColor(Color.BLUE);
                simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);

                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mVpContent.setCurrentItem(index);
                    }
                });

                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_MATCH_EDGE);
                linePagerIndicator.setColors(Color.BLUE);
                return linePagerIndicator;
            }
        });

        tabs.setNavigator(mCommonNavigator);
        ViewPagerHelper.bind(tabs, mVpContent);
    }


}