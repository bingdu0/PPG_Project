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
import com.ppg.mvp.view.adapter.ReportSpRaAdapter;
import com.ppg.utils.LogUtils;
import com.ppg.utils.PopupWindowUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Create by Donny.
 * 2017/12/17
 * Description：
 * 涂布率测试报告
 */
public class ReportSpRaActivity extends BaseActivity {

    private List<TestBean> textBeanList = new ArrayList<>();
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private ReportSpRaAdapter mAdapter;                                                                                  //8
    private String[] tv01 = {"项目信息：", "测试部门：", "基面情况：", "汗水率：", "碱性：", "测试日期：", "天气：", "温度：", "湿度：", "基面情况：", "施工条件：", " 产品体系&比重：", "底漆测试结果：", "面漆中层测试结果(第一次)：", "面漆中层测试结果(第二次)：", "罩面漆测试结果：", "保存"};
    private String[] et02 = {"上海喜来登大酒店", "请输入销售代表信息", "请输入基面情况", "请输入汗水率信息", "请输入碱性信息", "2017-10-23", "晴", "23", "79%", "点击查看详情", "点击查看详情", "点击查看详情", "点击查看详情", "点击查看详情", "点击查看详情", "点击查看详情", ""};
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
        baseTitle.setText("涂布率测试报告");
        baseTvRight.setText("生成报告");
        baseTvRight.setVisibility(View.VISIBLE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BaseApplication.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new ReportSpRaAdapter(this, textBeanList);
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
                        intent = new Intent(ReportSpRaActivity.this, ReportTeDisROneActivity.class);
                        startActivity(intent);
                        break;
                    case 9:  //基面情况
                        intent = new Intent(ReportSpRaActivity.this, BaseAreaActivity.class);
                        startActivity(intent);
                        break;
                    case 10: //施工条件
                        intent = new Intent(ReportSpRaActivity.this, ConstructConditionActivity.class);
                        startActivity(intent);
                        break;
                    case 11:
                        String strings_title = "产品体系";
                        ArrayList<String> strings_titles = new ArrayList<>();
                        strings_titles.add("底漆产品");
                        strings_titles.add("底漆比重");
                        strings_titles.add("中层漆产品");
                        strings_titles.add("中层漆比重");
                        strings_titles.add("面漆产品");
                        strings_titles.add("面漆比重");
                        strings_titles.add("罩面漆产品");
                        strings_titles.add("罩面漆比重");
                        strings_titles.add("affirm");
                        ArrayList<String> strings_contents = new ArrayList<>();
                        strings_contents.add("请输入底漆产品信息");
                        strings_contents.add("请输入底漆比重信息");
                        strings_contents.add("请输入中层漆产品信息");
                        strings_contents.add("请输入中层漆比重信息");
                        strings_contents.add("请输入面漆产品信息");
                        strings_contents.add("请输入面漆比重信息");
                        strings_contents.add("请输入罩面漆产品信息");
                        strings_contents.add("请输入罩面漆比重信息");
                        strings_contents.add("");


                        intent = new Intent(ReportSpRaActivity.this, ReportSpRaOneActivity.class);
                        intent.putExtra(Constant.STRINGS_TITLE, strings_title);
                        intent.putStringArrayListExtra(Constant.STRINGS_TITLES, strings_titles);
                        intent.putStringArrayListExtra(Constant.STRINGS_CONTENTS, strings_contents);
                        startActivity(intent);
                        break;

                    case 12:
                        String strings_title1 = "底漆测试结果";
                        ArrayList<String> strings_titles1 = new ArrayList<>();
                        strings_titles1.add("测试部位长度 L (m)：");
                        strings_titles1.add("测试部位宽度 W (m)：");
                        strings_titles1.add("涂刷面积 S = L * W (m)：");
                        strings_titles1.add("施工前总重量G1(kg)");
                        strings_titles1.add("施工后总重量G2(kg)");
                        strings_titles1.add("实际耗漆量G=G1-G2(kg)");
                        strings_titles1.add("实际涂布率C=S/G(m2/kg)");
                        strings_titles1.add("实际涂布率E=S/G/P(m2/kg)");
                        strings_titles1.add("affirm");
                        ArrayList<String> strings_contents1 = new ArrayList<>();
                        strings_contents1.add("请输入长度信息");
                        strings_contents1.add("请输入宽度信息");
                        strings_contents1.add("自动计算");
                        strings_contents1.add("请输入施工前总重量信息");
                        strings_contents1.add("请输入施工后总重量信息");
                        strings_contents1.add("自动计算");
                        strings_contents1.add("自动计算");
                        strings_contents1.add("自动计算");
                        strings_contents1.add("");


                        intent = new Intent(ReportSpRaActivity.this, ReportSpRaOneActivity.class);
                        intent.putExtra(Constant.STRINGS_TITLE, strings_title1);
                        intent.putStringArrayListExtra(Constant.STRINGS_TITLES, strings_titles1);
                        intent.putStringArrayListExtra(Constant.STRINGS_CONTENTS, strings_contents1);
                        startActivity(intent);
                        break;

                    case 13:
                        String strings_title2 = "面漆／中层(第一次)测试结果";
                        ArrayList<String> strings_titles2 = new ArrayList<>();
                        strings_titles2.add("测试部位长度L(m)");
                        strings_titles2.add("测试部位宽度W(m)");
                        strings_titles2.add("涂刷面积S=L*W(m)");
                        strings_titles2.add("施工前总重量G1(kg)");
                        strings_titles2.add("施工后总重量G2(kg)");
                        strings_titles2.add("实际耗漆量G=G1-G2(kg)");
                        strings_titles2.add("实际涂布率C=S/G(m2/kg)");
                        strings_titles2.add("实际涂布率E=S/G/P(m2/L)");
                        strings_titles2.add("affirm");
                        ArrayList<String> strings_contents2 = new ArrayList<>();
                        strings_contents2.add("请输入长度信息");
                        strings_contents2.add("请输入宽度信息");
                        strings_contents2.add("自动计算");
                        strings_contents2.add("请输入施工前总重量信息");
                        strings_contents2.add("请输入施工后总重量信息");
                        strings_contents2.add("自动计算");
                        strings_contents2.add("自动计算");
                        strings_contents2.add("自动计算");
                        strings_contents2.add("");


                        intent = new Intent(ReportSpRaActivity.this, ReportSpRaOneActivity.class);
                        intent.putExtra(Constant.STRINGS_TITLE, strings_title2);
                        intent.putStringArrayListExtra(Constant.STRINGS_TITLES, strings_titles2);
                        intent.putStringArrayListExtra(Constant.STRINGS_CONTENTS, strings_contents2);
                        startActivity(intent);
                        break;

                    case 14:
                        String strings_title3 = "面漆／中层(第二次)测试结果";
                        ArrayList<String> strings_titles3 = new ArrayList<>();
                        strings_titles3.add("测试部位长度L(m)");
                        strings_titles3.add("测试部位宽度W(m)");
                        strings_titles3.add("涂刷面积S=L*W(m)");
                        strings_titles3.add("施工前总重量G1(kg)");
                        strings_titles3.add("施工后总重量G2(kg)");
                        strings_titles3.add("实际耗漆量G=G1-G2(kg)");
                        strings_titles3.add("实际涂布率C=S/G(m2/kg)");
                        strings_titles3.add("实际涂布率E=S/G/P(m2/L)");
                        strings_titles3.add("affirm");
                        ArrayList<String> strings_contents3 = new ArrayList<>();
                        strings_contents3.add("请输入长度信息");
                        strings_contents3.add("请输入宽度信息");
                        strings_contents3.add("自动计算");
                        strings_contents3.add("请输入施工前总重量信息");
                        strings_contents3.add("请输入施工后总重量信息");
                        strings_contents3.add("自动计算");
                        strings_contents3.add("自动计算");
                        strings_contents3.add("自动计算");
                        strings_contents3.add("");


                        intent = new Intent(ReportSpRaActivity.this, ReportSpRaOneActivity.class);
                        intent.putExtra(Constant.STRINGS_TITLE, strings_title3);
                        intent.putStringArrayListExtra(Constant.STRINGS_TITLES, strings_titles3);
                        intent.putStringArrayListExtra(Constant.STRINGS_CONTENTS, strings_contents3);
                        startActivity(intent);
                        break;

                    case 15:
                        String strings_title4 = "罩面漆测试结果";
                        ArrayList<String> strings_titles4 = new ArrayList<>();
                        strings_titles4.add("测试部位长度L(m)");
                        strings_titles4.add("测试部位宽度W(m)");
                        strings_titles4.add("涂刷面积S=L*W(m)");
                        strings_titles4.add("施工前总重量G1(kg)");
                        strings_titles4.add("施工后总重量G2(kg)");
                        strings_titles4.add("实际耗漆量G=G1-G2(kg)");
                        strings_titles4.add("实际涂布率C=S/G(m2/kg)");
                        strings_titles4.add("实际涂布率E=S/G/P(m2/L)");
                        strings_titles4.add("affirm");
                        ArrayList<String> strings_contents4 = new ArrayList<>();
                        strings_contents4.add("请输入长度信息");
                        strings_contents4.add("请输入宽度信息");
                        strings_contents4.add("自动计算");
                        strings_contents4.add("请输入施工前总重量信息");
                        strings_contents4.add("请输入施工后总重量信息");
                        strings_contents4.add("自动计算");
                        strings_contents4.add("自动计算");
                        strings_contents4.add("自动计算");
                        strings_contents4.add("");


                        intent = new Intent(ReportSpRaActivity.this, ReportSpRaOneActivity.class);
                        intent.putExtra(Constant.STRINGS_TITLE, strings_title4);
                        intent.putStringArrayListExtra(Constant.STRINGS_TITLES, strings_titles4);
                        intent.putStringArrayListExtra(Constant.STRINGS_CONTENTS, strings_contents4);
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
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_TV_R_BTN_R, tv01[i], et02[i]));


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
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_TV_BTN_D, tv01[i], et02[i]));

                        break;
                    case 6:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_TV, tv01[i], et02[i]));

                        break;
                    case 7:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_TV, tv01[i], et02[i]));


                        break;
                    case 8:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_TV, tv01[i], et02[i]));


                        break;
                    case 9:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_TV_R_BTN_R, tv01[i], et02[i]));

                        break;
                    case 10:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_TV_R_BTN_R, tv01[i], et02[i]));

                        break;
                    case 11:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_TV_R_BTN_R, tv01[i], et02[i]));

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
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_BTN, tv01[i], et02[i]));
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
            LogUtils.e("YWQ", "数组长度不一样");
        }
    }
}
