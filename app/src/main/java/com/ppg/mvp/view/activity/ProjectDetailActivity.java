package com.ppg.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;

import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BasePresenter;
import com.ppg.bean.TestBean;
import com.ppg.mvp.view.adapter.ProjectDetailAdapter;
import com.ppg.mvp.view.fragment.HandlingComplaintFragment;
import com.ppg.mvp.view.fragment.PrejectMagFragment;
import com.ppg.mvp.view.fragment.ServiceReportFragment;
import com.ppg.mvp.view.fragment.SpreadingRateFragment;
import com.ppg.mvp.view.fragment.TechnicalDisclosureFragment;
import com.ppg.mvp.view.fragment.TempletReportFragment;
import com.ppg.utils.PopupWindowUtil;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;

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
//            Intent intent=new Intent(this,AddActivity.class);
//            startActivity(intent);
            setPopupWindow(v);
            break;
        default:
            break;
        }
    }

    public void setPopupWindow(View mView){
        final List<String> items = new ArrayList<>();

        items.add("技术交底记录");
        items.add("投诉处理报告");
        items.add("涂布率测试");
        items.add("现场施工巡查服务报告");
        items.add("现场样板制作服务报告");

        // 点击控件后显示popup窗口
        final PopupWindowUtil popupWindow = new PopupWindowUtil(ProjectDetailActivity.this, items);

        popupWindow.setItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupWindow.dismiss();

            }
        });
        //根据后面的数字 手动调节窗口的宽度
        popupWindow.show(mView, 6);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("qqqq",resultCode+"222onActivityReult =="+requestCode);
//        if (resultCode != RESULT_OK) {
//            return;
//        }
        if(data == null){
            return;
        }
        switch (requestCode) {
            case PrejectMagFragment.MULTI_IMG: //多张图片返回
                ArrayList<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                EventBus.getDefault().post(path);

                break;
        }

    }
}
