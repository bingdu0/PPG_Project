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
import com.ppg.mvp.view.adapter.ReportTeDisRFourAdapter;
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
public class ReportTeDisRFourActivity extends BaseActivity{
    private List<TestBean> textBeanList = new ArrayList<>();
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private ReportTeDisRFourAdapter mAdapter;
    private String[] tv01 = {"需要施工培训", "需要示范操作", "培训内容", "", "", "", "", "", "", "确认"};
    private String[] et02 = {"", "", "", "运用基层材料", "基层的要求各处理", "产品特性（底涂、中涂、面涂）","施工环境条件","涂料施工规范（稀释比例、施工方式、干燥重涂时间等）","施工注意事项",""};

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
        baseTitle.setText("施工指导");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BaseApplication.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new ReportTeDisRFourAdapter(this, textBeanList);
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
                    PopupWindowUtil.setPopupWindow(BaseApplication.getContext(),textBeanList,mAdapter,view,position);
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
