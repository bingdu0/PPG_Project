package com.ppg.mvp.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BaseApplication;
import com.ppg.base.BasePresenter;
import com.ppg.bean.TestBean;
import com.ppg.constants.Constant;
import com.ppg.mvp.view.adapter.ReportTeDisRTwoAdapter;
import com.ppg.utils.LogUtils;
import com.ppg.utils.PopupWindowUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Create by Donny.
 * 2017/12/17
 * Description：
 * 涂装方案
 */
public class ReportTeDisRTwoActivity extends BaseActivity{
    private List<TestBean> textBeanList = new ArrayList<>();
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private ReportTeDisRTwoAdapter mAdapter;
    private String[] tv01 = {"底涂", "名称：", "用量：", "中涂", "名称：", "用量：", "颜色：","面漆","名称：","用量：","颜色：","涂装施工个采用：","","","","","确认"};
    private String[] et02 = {"", "请输入底涂名称", "请输入底涂用量", "", "请输入中涂名称", "请输入中涂用量","请输入中涂颜色","","请输入面漆名称","请输入面漆用量","请输入面漆颜色","","吊篮","脚手架","其他","请输入涂装施工方式说明",""};

    @Override
    protected int getLayoutId() {
      return R.layout.avtivity_report_te_dis_r_one;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        baseTitle.setText("涂装方案");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BaseApplication.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new ReportTeDisRTwoAdapter(this, textBeanList);
        recyclerView.setAdapter(mAdapter);
        getData();
    }

    @Override
    protected void initListener() {
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                PopupWindowUtil.setPopupWindow(BaseApplication.getContext(),textBeanList,mAdapter,view,position);
            }
        });
    }

    private void getData() {
        textBeanList.clear();
        if (tv01.length == et02.length) {
            for (int i = 0; i < tv01.length; i++) {

                switch (i) {
                    case 0:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TEXT_NORMAL, tv01[i], et02[i]));
                        break;

                    case 1:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_EV, tv01[i], et02[i]));
                        break;

                    case 2:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_EV, tv01[i], et02[i]));
                        break;

                    case 3:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TEXT_NORMAL, tv01[i], et02[i]));
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
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TEXT_NORMAL, tv01[i], et02[i]));

                        break;
                    case 8:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_EV, tv01[i], et02[i]));
                        break;
                    case 9:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_EV, tv01[i], et02[i]));
                        break;
                    case 10:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_EV, tv01[i], et02[i]));
                        break;
                    case 11:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TEXT_NORMAL, tv01[i], et02[i]));
                        break;
                    case 12:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV, tv01[i], et02[i]));

                        break;
                    case 13:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV, tv01[i], et02[i]));
                        break;
                    case 14:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV, tv01[i], et02[i]));
                        break;
                    case 15:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_ET, tv01[i], et02[i]));
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
            LogUtils.i("YWQ", "数组长度不一样");
        }
    }
}
