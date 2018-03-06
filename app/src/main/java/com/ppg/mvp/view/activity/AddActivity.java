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
import com.ppg.mvp.view.adapter.AddAdapter;
import com.ppg.utils.PopupWindowUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Create by Donny.
 * 2017/12/17
 * Description：
 * 跳转的新建项目
 */
public class AddActivity extends BaseActivity{
    private List<TestBean> testBeanList = new ArrayList<>();
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private AddAdapter addAdapter;
    private String[] tv01 = {"项目名称：", "区域：", "城市：", "地址：", "面积：", "产品体系：", "经销商名称：", "销售人员：", "施工单位：", "监理单位："};
    private String[] et02 = {"请输入项目名称", "请选择区域信息", "请输入城市信息", "请输入地址信息", "请输入面积信息", "请输入产品体系信息", "请输入经销商名称", "请输入销售人员信息", "请输入施工单位", "请输入监理单位"};

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        baseTitle.setText("新建项目");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BaseApplication.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        addAdapter = new AddAdapter(this, testBeanList);
        recyclerView.setAdapter(addAdapter);
        getData();
    }

    @Override
    protected void initListener() {
        addAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                PopupWindowUtil.setPopupWindow(BaseApplication.getContext(),testBeanList,addAdapter,view,position);

            }
        });
    }

    private void getData() {
        testBeanList.clear();
        for (int i = 0; i < tv01.length; i++) {
            if (i == 1) {
                testBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_TV_BTN_D, tv01[i], et02[i]));
            } else {
                testBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_EV, tv01[i], et02[i]));
            }

        }
        addAdapter.notifyDataSetChanged();
    }
}
