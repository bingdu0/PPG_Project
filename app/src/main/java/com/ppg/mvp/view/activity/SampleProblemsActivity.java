package com.ppg.mvp.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BaseApplication;
import com.ppg.base.BasePresenter;
import com.ppg.bean.TestBean;
import com.ppg.constants.Constant;
import com.ppg.mvp.view.adapter.ReportTeDisRFourAdapter;
import com.ppg.mvp.view.adapter.SampleProblemAdapter;
import com.ppg.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 样板效果及问题
 */
public class SampleProblemsActivity extends BaseActivity {

    @BindView(R.id.rv_sample_problem)
    RecyclerView rvSampleProblem;
    private List<TestBean> textBeanList = new ArrayList<>();
    private String[] tv01 = {"样板基面情况", "ESP保温基面", "围墙水泥抹灰面", "PH值大于10", "PH值小于10", "含水10以内", "含水率10-15", "含水率大于20", "基面完好", "基面粉化", "基面开裂", "腻子", "双组分灰色", "双组分白色", "单组分灰色", "单组分白色"};
    private String[] tv02 = {"", "", "", "", "", "", "", "", "", "", "", "", "R 型粗腻子", "P型细腻子", "", "P型细腻子",};
    private SampleProblemAdapter mSampleProblemAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_sample_problems;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        baseTitle.setText("现场样板效果及问题");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BaseApplication.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
        rvSampleProblem.setLayoutManager(linearLayoutManager);
        rvSampleProblem.setHasFixedSize(true);
        rvSampleProblem.setNestedScrollingEnabled(false);
        mSampleProblemAdapter = new SampleProblemAdapter(this, textBeanList);
        rvSampleProblem.setAdapter(mSampleProblemAdapter);
        getData();
    }

    @Override
    protected void initListener() {

    }

    private void getData() {
        textBeanList.clear();
        if (tv01.length == tv02.length) {
            for (int i = 0; i < tv01.length; i++) {

                switch (i) {
                    case 0:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TEXT_WARNING, tv01[i], tv02[i]));


                        break;

                    case 1:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV, tv01[i], tv02[i]));

                        break;

                    case 2:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV, tv01[i], tv02[i]));

                        break;

                    case 3:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV, tv01[i], tv02[i]));

                        break;

                    case 4:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV, tv01[i], tv02[i]));

                        break;
                    case 5:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV, tv01[i], tv02[i]));

                        break;
                    case 6:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV, tv01[i], tv02[i]));

                        break;
                    case 7:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV, tv01[i], tv02[i]));

                        break;
                    case 8:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV, tv01[i], tv02[i]));

                        break;
                    case 9:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV, tv01[i], tv02[i]));
                        break;
                    case 10:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TEXT_WARNING, tv01[i], tv02[i]));
                        break;
                    case 11:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV_TV, tv01[i], tv02[i]));
                        break;
                    case 12:

                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV_TV, tv01[i], tv02[i]));
                        break;
                    case 13:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV_TV, tv01[i], tv02[i]));
                        break;
                    case 14:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV_TV, tv01[i], tv02[i]));
                        break;
                    case 15:
                        break;
                    case 16:

                        break;
                    case 17:
                        break;
                    case 18:
                        break;
                    case 19:
                        break;
                    case 20:
                        break;


                    default:
                        break;
                }
            }
            mSampleProblemAdapter.notifyDataSetChanged();
            LogUtils.i("LK", "数组长度为：" + tv01.length);
        } else {
            LogUtils.i("LK", "数组长度不一样");
        }
    }
}
