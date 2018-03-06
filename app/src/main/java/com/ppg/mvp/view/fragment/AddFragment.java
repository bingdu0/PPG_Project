package com.ppg.mvp.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ppg.R;
import com.ppg.base.BaseApplication;
import com.ppg.base.BaseFragment;
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
 * 2017/12/14
 * Description：
 */
public class AddFragment extends BaseFragment {
    private static AddFragment addFragment;
    private List<TestBean> testBeanList = new ArrayList<>();
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private AddAdapter addAdapter;
    private String[] tv01 = {"项目名称：", "区域：", "城市：", "地址：", "面积：", "产品体系：", "经销商名称：", "销售人员：", "施工单位：", "监理单位："};
    private String[] et02 = {"请输入项目名称", "请选择区域信息", "请输入城市信息", "请输入地址信息", "请输入面积信息", "请输入产品体系信息", "请输入经销商名称", "请输入销售人员信息", "请输入施工单位", "请输入监理单位"};


    /** popup窗口里的ListView */
    private ListView mPopupWinList;
    /** popup窗口 */
    private PopupWindow typeSelectPopup;
    /** 模拟的假数据 */
    private List<String> testData;
    /** 数据适配器 */
    private ArrayAdapter<String> testDataAdapter;



    public static AddFragment getInstance() {
        return addFragment == null ? new AddFragment() : addFragment;
    }

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
        btnBaseBarBack.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BaseApplication.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        addAdapter = new AddAdapter(getActivity(), testBeanList);
        recyclerView.setAdapter(addAdapter);
        getData();


    }

    @Override
    protected void initListener() {

        addAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.btn_back_d:
                        setPopupWindow(view,position);
                        break;
                    default:
                        break;
                }
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

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    @Override
    public void onPause() {
        super.onPause();
        testBeanList.clear();

    }

    public void setPopupWindow(View mView,int index){
         final List<String> items = new ArrayList<>();
        final TestBean bean=testBeanList.get(index);
        items.add("第一项xxxxxxx");
        items.add("第二项");
        items.add("第三项");
        items.add("第四项");

        // 点击控件后显示popup窗口
        final PopupWindowUtil popupWindow = new PopupWindowUtil(getActivity(), items);

        popupWindow.setItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupWindow.dismiss();
                bean.setContent(items.get(position));
                addAdapter.notifyItemChanged(1);
            }
        });
        //根据后面的数字 手动调节窗口的宽度
        popupWindow.show(mView, 6);
    }

}
