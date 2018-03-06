package com.ppg.mvp.view.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ppg.R;
import com.ppg.base.BaseApplication;
import com.ppg.base.BaseFragment;
import com.ppg.base.BasePresenter;
import com.ppg.bean.TestBean;
import com.ppg.constants.Constant;
import com.ppg.mvp.view.activity.CameraActivity;
import com.ppg.mvp.view.adapter.PrMsgRAdapter;
import com.ppg.utils.LogUtils;
import com.ppg.utils.PopupWindowUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Create by Donny.
 * 2017/12/14
 * Description：
 * 项目信息
 */
public class PrejectMagFragment extends BaseFragment {
    private static PrejectMagFragment mPrejectMagFragment;
    private List<TestBean> textBeanList = new ArrayList<>();
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private PrMsgRAdapter mAdapter;
    private String[] tv01 = {"项目状态：", "项目名称：", "区域：", "城市：", "地址：", "面积：", "产品体系：", "经销商名称：", "销售人员：", "技术服务人员：", "施工单位：", "监理单位：", "创建日期：", "", "保存"};
    private String[] et02 = {"进行中", "请输入项目名称", "", "请输入城市信息", "请输入项目地址", "请输入面积信息", "请输入施工方负责人", "请输入经销商名称", "请输入销售人员信息", "请输入技术服务人员信息", "请输入施工单位信息", "请输入监理单位信息", "2017-10-23", "", ""};


    public static PrejectMagFragment getInstance() {
        return mPrejectMagFragment == null ? new PrejectMagFragment() : mPrejectMagFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_preject_msg;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BaseApplication.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new PrMsgRAdapter(getActivity(), textBeanList);
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
                        setPopupWindow(view,position);
                        break;
                    case R.id.imageView4:
                        LogUtils.i("YWQ","点击摄像头");
                        Intent intent=new Intent(getActivity(), CameraActivity.class);
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
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_TV_BTN_D, tv01[i], et02[i]));
                        break;

                    case 1:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_EV, tv01[i], et02[i]));
                        break;

                    case 2:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_TV_BTN_D, tv01[i], et02[i]));
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
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_EV, tv01[i], et02[i]));
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
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_EV, tv01[i], et02[i]));
                        break;
                    case 12:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TV_TV_BTN_D, tv01[i], et02[i]));
                        break;
                    case 13:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_IMG_IMG_ADD, tv01[i], et02[i]));
                        break;
                    case 14:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_BTN, tv01[i], et02[i]));
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


    public void setPopupWindow(View mView, final int index){
        final List<String> items = new ArrayList<>();
        final TestBean bean=textBeanList.get(index);
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
                mAdapter.notifyItemChanged(index);
            }
        });
        //根据后面的数字 手动调节窗口的宽度
        popupWindow.show(mView, 6);
    }


}
