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
 * 现场样板报告
 */
public class ReportTeReActivity extends BaseActivity {

    private List<TestBean> textBeanList = new ArrayList<>();
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private ReportHaCoAdapter mAdapter;
    private String[] tv01 = {"项目信息：", "涂料体系：", "技术服务代表：", "施工单位：", "施工方负责人：", "主要施工内容：", "开始时间：", "结束时间：", "天气情况：", "现场投诉问题概括：", "现场问题查询及信息收集：", "现场遇到问题分析：", "最终解决方案及整改措施：", "保存"};
    private String[] et02 = {"上海喜来登大酒店", "请输入涂料体系信息", "请输入涂料供应商", "技术服务人员信息", "请输入施工单位信息", "请输入施工方负责人", "请输入主要施工内容", "2017-10-23","2017-10-28", "点击查看详情", "点击查看详情", "点击查看详情", "点击查看详情", "点击查看详情", ""};
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
        baseTitle.setText("现场样板报告");
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
                        intent = new Intent(ReportTeReActivity.this, ReportTeDisROneActivity.class);
                        startActivity(intent);
                        break;
                    case 8:
                        intent = new Intent(ReportTeReActivity.this, EditActivity.class);
                        intent.putExtra(Constant.EDIT_TITLE,"目前进展情况");
                        intent.putExtra(Constant.EDIT_HINT,BaseApplication.getContext().getResources().getString(R.string.r_t_d_r_01));
                        startActivity(intent);
                        break;

                    case 9:
                        intent = new Intent(ReportTeReActivity.this, ReportTeDisRTwoActivity.class);
                        startActivity(intent);
                        break;

                    case 10:
                        intent = new Intent(ReportTeReActivity.this, ReportTeDisRThreeActivity.class);
                        startActivity(intent);
                        break;

                    case 11:
                        intent = new Intent(ReportTeReActivity.this, ReportTeDisRFourActivity.class);
                        startActivity(intent);
                        break;

                    case 12:
                        intent = new Intent(ReportTeReActivity.this, EditActivity.class);
                        intent.putExtra(Constant.EDIT_TITLE,"现场实施情况");
                        intent.putExtra(Constant.EDIT_HINT,BaseApplication.getContext().getResources().getString(R.string.r_t_d_r_02));
                        startActivity(intent);
                        break;

                    case 13:
                        intent = new Intent(ReportTeReActivity.this, EditActivity.class);
                        intent.putExtra(Constant.EDIT_TITLE,"现场问题隐患");
                        intent.putExtra(Constant.EDIT_HINT,BaseApplication.getContext().getResources().getString(R.string.r_t_d_r_03));
                        startActivity(intent);
                        break;

                    case 14:
                        intent = new Intent(ReportTeReActivity.this, EditActivity.class);
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

                switch (i) {
                    case 0:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_SV, tv01[i], et02[i]));


                        break;

                    case 1:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_SV, tv01[i], et02[i]));

                        break;

                    case 2:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TEXT_NORMAL, tv01[i], et02[i]));

                        break;

                    case 3:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV, tv01[i], et02[i]));

                        break;

                    case 4:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV, tv01[i], et02[i]));

                        break;
                    case 5:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV, tv01[i], et02[i]));

                        break;
                    case 6:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV, tv01[i], et02[i]));

                        break;
                    case 7:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV, tv01[i], et02[i]));

                        break;
                    case 8:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV, tv01[i], et02[i]));

                        break;
                    case 9:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_BTN, tv01[i], et02[i]));
                        break;
                    case 10:

                        break;
                    case 11:
                        break;
                    case 12:

                        break;
                    case 13:
                        break;
                    case 14:
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
            mAdapter.notifyDataSetChanged();
            LogUtils.i("YWQ", "数组长度为：" + tv01.length);
        } else {
            LogUtils.i("YWQ", "数组长度不一样");
        }
    }
}
