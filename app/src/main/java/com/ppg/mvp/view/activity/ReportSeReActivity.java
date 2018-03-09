package com.ppg.mvp.view.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BaseApplication;
import com.ppg.base.BasePresenter;
import com.ppg.bean.TestBean;
import com.ppg.constants.Constant;
import com.ppg.mvp.view.adapter.ReportHaCoAdapter;
import com.ppg.utils.LogUtils;
import com.ppg.utils.PopupWindowUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Create by Donny.
 * 2017/12/17
 * Description：
 * 现场服务报告
 */
public class ReportSeReActivity extends BaseActivity {

    private List<TestBean> textBeanList = new ArrayList<>();
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private ReportHaCoAdapter mAdapter;
    private String[] tv01 = {"项目信息：", "涂料体系：", "涂料供应商：", "技术服务人员：", "施工单位：", "施工方负责人：", "主要施工内容：", "开始时间：", "结束时间：", "天气情况：", "现场工程情况", "巡检问题整及整改情况：", "基面主要问题及整改情况：", "腻子施工情况：", "底漆施工情况：", "中层或面漆施工情况：", "现场问题总结分析：", "施工建议及整改措施：", "保存"};
    private String[] et02 = {"上海喜来登大酒店", "请输入涂料体系信息", "请输入涂料供应商", "请输入技术服务人员信息", "请输入施工单位信息", "请输入施工方负责人", "请输入主要施工内容", "2017-10-23", "2017-10-28", "点击查看详情", "点击查看详情", "", "点击查看详情", "点击查看详情", "点击查看详情", "点击查看详情", "点击查看详情", "点击查看详情", ""};
    private Intent intent;

    @Override
    protected int getLayoutId() {
        return R.layout.avtivity_report_te_dis_r;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        baseTitle.setText("现场服务报告");
        baseTvRight.setText("生成报告");
        baseTvRight.setVisibility(View.VISIBLE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BaseApplication.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new ReportHaCoAdapter(this, textBeanList);
        recyclerView.setAdapter(mAdapter);
        getData();
    }

    @Override
    protected void initListener() {
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.btn_back_d:
                        PopupWindowUtil.setPopupWindow(BaseApplication.getContext(), textBeanList, mAdapter, view, position);
                        break;

                    default:
                        break;
                }
            }
        });

        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        intent = new Intent(ReportSeReActivity.this, ReportTeDisROneActivity.class);
                        startActivity(intent);
                        break;
                    //天气情况
                    case 9:
                        intent = new Intent(ReportSeReActivity.this, ReportHaCoTwoActivity.class);
                        startActivity(intent);
                        break;
                    //现场工程情况
                    case 10:
                        intent = new Intent(ReportSeReActivity.this, DatumStatusActivity.class);
                        startActivity(intent);
                        break;
                    //基面主要问题及整改情况
                    case 12:
                        intent = new Intent(ReportSeReActivity.this, DatumIssueChangeActivity.class);
                        startActivity(intent);
                        break;
                    //腻子施工情况
                    case 13:
                        intent = new Intent(ReportSeReActivity.this, DatumNiZiStatusActivity.class);
                        startActivity(intent);
                        break;
                    //底漆施工情况
                    case 14:
                        intent = new Intent(ReportSeReActivity.this, DatumDiQiStatusActivity.class);
                        startActivity(intent);
                        break;
                    //中层或面漆施工情况
                    case 15:
                        intent = new Intent(ReportSeReActivity.this, DatumZcmqStatusActivity.class);
                        startActivity(intent);
                        break;
                    //现场问题总结分析
                    case 16:
                        intent = new Intent(ReportSeReActivity.this, DatumOtherInfoActivity.class);
                        intent.putExtra("type","现场问题总结分析");
                        startActivity(intent);
                        break;
                    //施工建议及整改措施
                    case 17:
                        intent = new Intent(ReportSeReActivity.this, DatumOtherInfoActivity.class);
                        intent.putExtra("type","施工建议及整改措施");
                        startActivity(intent);
                        break;


                    default:
                        break;
                }

            }
        });
    }

    private void getData() {
        textBeanList.clear();
        if (tv01.length == et02.length) {
            for (int i = 0; i < tv01.length; i++) {

                switch (i) {
                    case 0:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_TV_BTN_R, tv01[i], et02[i]));


                        break;

                    case 1:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_EV, tv01[i], et02[i]));

                        break;

                    case 2:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_EV, tv01[i], et02[i]));


                        break;

                    case 3:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_EV, tv01[i], et02[i]));


                        break;

                    case 4:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_EV, tv01[i], et02[i]));


                        break;
                    case 5:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_EV, tv01[i], et02[i]));


                        break;
                    case 6:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_EV, tv01[i], et02[i]));


                        break;
                    case 7:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_TV_BTN_D, tv01[i], et02[i]));

                        break;
                    case 8:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_TV_BTN_D, tv01[i], et02[i]));


                        break;
                    case 9:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_TV_R_BTN_R, tv01[i], et02[i]));

                        break;
                    case 10:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_TV_R_BTN_R, tv01[i], et02[i]));

                        break;
                    case 11:
                        //
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TEXT_NORMAL, tv01[i], et02[i]));

                        break;
                    case 12:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_TV_R_BTN_R, tv01[i], et02[i]));

                        break;
                    case 13:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_TV_R_BTN_R, tv01[i], et02[i]));

                        break;
                    case 14:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_TV_R_BTN_R, tv01[i], et02[i]));

                        break;
                    case 15:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_TV_R_BTN_R, tv01[i], et02[i]));
                        break;
                    case 16:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_TV_R_BTN_R, tv01[i], et02[i]));
                        break;
                    case 17:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_TV_R_BTN_R, tv01[i], et02[i]));
                        break;
                    case 18:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_BTN, tv01[i], et02[i]));
                        break;
                    case 19:
                        break;
                    case 20:
                        break;


                    default:
                        break;
                }
            }
            mAdapter.notifyDataSetChanged();
            LogUtils.i("YWQ", "数组长度为：" + tv01.length);
        } else {
            LogUtils.i("YWQ", "数组长度不一样");
        }
    }
}
