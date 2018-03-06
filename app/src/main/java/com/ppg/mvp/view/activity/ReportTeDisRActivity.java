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
import com.ppg.mvp.view.adapter.ReportTeDisRAdapter;
import com.ppg.utils.LogUtils;
import com.ppg.utils.PopupWindowUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Create by Donny.
 * 2017/12/17
 * Description：
 * 技术交底报告
 */
public class ReportTeDisRActivity extends BaseActivity {

    private List<TestBean> textBeanList = new ArrayList<>();
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private ReportTeDisRAdapter mAdapter;
    private String[] tv01 = {"项目名称：", "销售代表：", "技术服务代表：", "甲方代表：", "监理代表：", "经销商代表：", "施工方代表：", "技术交底日期：", "目前进展情况：", "涂装方案：", "施工方式：", "施工指导：", "现场实操情况：", "现场施工问题隐患：", "施工建议&配合措施：", "保存"};
    private String[] et02 = {"上海喜来登大酒店", "请输入销售代表信息", "请输入技术服务代表信息", "请输入甲方代表信息", "请输入监理代表信息", "请输入经销商代表信息", "请输入施工方信息", "2017-10-23", "", "点击查看详情", "点击查看详情", "点击查看详情", "", "点击查看详情", "", ""};
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
        baseTitle.setText("技术交底报告");
        baseTvRight.setText("生成报告");
        baseTvRight.setVisibility(View.VISIBLE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BaseApplication.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new ReportTeDisRAdapter(this, textBeanList);
        recyclerView.setAdapter(mAdapter);
        getData();
    }

    @Override
    protected void initListener() {
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                PopupWindowUtil.setPopupWindow(BaseApplication.getContext(), textBeanList, mAdapter, view, position);
            }
        });

        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        intent = new Intent(ReportTeDisRActivity.this, ReportTeDisROneActivity.class);
                        startActivity(intent);
                        break;
                    case 8:
                        intent = new Intent(ReportTeDisRActivity.this, EditActivity.class);
                        intent.putExtra(Constant.EDIT_TITLE,"目前进展情况");
                        intent.putExtra(Constant.EDIT_HINT,BaseApplication.getContext().getResources().getString(R.string.r_t_d_r_01));
                        startActivity(intent);
                        break;

                    case 9:
                        intent = new Intent(ReportTeDisRActivity.this, ReportTeDisRTwoActivity.class);
                        startActivity(intent);
                        break;

                    case 10:
                        intent = new Intent(ReportTeDisRActivity.this, ReportTeDisRThreeActivity.class);
                        startActivity(intent);
                        break;

                    case 11:
                        intent = new Intent(ReportTeDisRActivity.this, ReportTeDisRFourActivity.class);
                        startActivity(intent);
                        break;

                    case 12:
                        intent = new Intent(ReportTeDisRActivity.this, EditActivity.class);
                        intent.putExtra(Constant.EDIT_TITLE,"现场实施情况");
                        intent.putExtra(Constant.EDIT_HINT,BaseApplication.getContext().getResources().getString(R.string.r_t_d_r_02));
                        startActivity(intent);
                        break;

                    case 13:
                        intent = new Intent(ReportTeDisRActivity.this, EditActivity.class);
                        intent.putExtra(Constant.EDIT_TITLE,"现场问题隐患");
                        intent.putExtra(Constant.EDIT_HINT,BaseApplication.getContext().getResources().getString(R.string.r_t_d_r_03));
                        startActivity(intent);
                        break;

                    case 14:
                        intent = new Intent(ReportTeDisRActivity.this, EditActivity.class);
                        intent.putExtra(Constant.EDIT_TITLE,"施工建议&配合措施");
                        intent.putExtra(Constant.EDIT_HINT,BaseApplication.getContext().getResources().getString(R.string.r_t_d_r_04));
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
                if (i == 0 || i == 8 || i == 12 || i == 14) {
                    textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_TV_BTN_R, tv01[i], et02[i]));
                } else if (i == 7) {
                    textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_TV_BTN_D, tv01[i], et02[i]));
                } else if (i == 1 || i <= 6) {
                    textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_EV, tv01[i], et02[i]));
                } else if (i >= 9 && i <= 11) {
                    textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_TV_R_BTN_R, tv01[i], et02[i]));
                } else if (i == 13) {
                    textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_TV_R_BTN_R, tv01[i], et02[i]));
                } else if (i == tv01.length - 1) {
                    textBeanList.add(new TestBean(Constant.ITEM_TYPE_BTN, tv01[i], et02[i]));
                }
            }
            mAdapter.notifyDataSetChanged();
            LogUtils.i("YWQ", "数组长度为：" + tv01.length);
        } else {
            LogUtils.i("YWQ", "数组长度不一样");
        }
    }
}
