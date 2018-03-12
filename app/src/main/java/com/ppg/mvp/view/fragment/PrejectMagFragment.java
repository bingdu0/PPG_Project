package com.ppg.mvp.view.fragment;

import android.Manifest;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ppg.R;
import com.ppg.base.BaseApplication;
import com.ppg.base.BaseFragment;
import com.ppg.base.BasePresenter;
import com.ppg.bean.ScreenDialogBean;
import com.ppg.bean.TestBean;
import com.ppg.constants.Constant;
import com.ppg.mvp.view.activity.CameraActivity;
import com.ppg.mvp.view.activity.ComplainBrieflyActivity;
import com.ppg.mvp.view.activity.ImageActivity;
import com.ppg.mvp.view.adapter.MyImgAdapter;
import com.ppg.mvp.view.adapter.PrMsgRAdapter;
import com.ppg.utils.BottomeListDialog;
import com.ppg.utils.LogUtils;
import com.ppg.utils.PopupWindowUtil;
import com.ppg.utils.ToastUtil;
import com.ppg.utils.ToolUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

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
    private String[] tv01 = {"项目状态：", "项目名称：", "区域：", "城市：", "地址：", "面积：", "产品体系：", "经销商名称：", "销售人员：", "技术服务人员：", "施工单位：", "监理单位：", "创建日期：", };
    private String[] et02 = {"进行中", "请输入项目名称", "", "请输入城市信息", "请输入项目地址", "请输入面积信息", "请输入施工方负责人", "请输入经销商名称", "请输入销售人员信息", "请输入技术服务人员信息", "请输入施工单位信息", "请输入监理单位信息", "2017-10-23",};




    /**
     * 多张图片相关
     */
    private int IMAGE_MAX = 6;
    private MyImgAdapter myImgAdapter;
    private GridLayoutManager gridLayoutManager;
    private ArrayList<String> mImageList = new ArrayList<>();
    public static final int MULTI_IMG = 130;

    @BindView(R.id.rv_horizontal)
    RecyclerView recyclerViewImg;
    List<ScreenDialogBean> mList;

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
       // EventBus.getDefault().register(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BaseApplication.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        mAdapter = new PrMsgRAdapter(getActivity(), textBeanList);

        recyclerView.setAdapter(mAdapter);





        myImgAdapter = new MyImgAdapter(getActivity(), mImageList);
        gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerViewImg.setLayoutManager(gridLayoutManager);
        recyclerViewImg.setHasFixedSize(true);
        recyclerViewImg.setItemAnimator(new DefaultItemAnimator());
        recyclerViewImg.setAdapter(myImgAdapter);
        myImgAdapter.setOnItemClickListener(new MyImgAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {

                RxPermissions rxPermissions = new RxPermissions(getActivity());
                rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            Intent picIntent = new Intent(getActivity(), ImageActivity.class);
                            picIntent.putExtra(ImageActivity.IMAGE_INTENT, mImageList.get(position));
                            startActivity(picIntent);
                        } else {
                            // 用户拒绝了该权限，并且选中不再询问,引导用户跳转权限管理页面
                        }
                    }
                });


            }

            @Override
            public void onAddClick(View view, int position) {
                if (ToolUtil.isEmpty(mImageList) || mImageList.size() < IMAGE_MAX) {
                    startCameras();
                } else {
                    ToastUtil.showShort("图片张数上限,请在删除部分照片后重试");
                }
            }

            @Override
            public void onDeleteClick(View view, int position) {
                showSelectDialog(position);
            }
        });
        getData();
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void initListener() {
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(position == 0){
                    View mView = getView();
                    mList = new ArrayList<>();
                    mList.add(new ScreenDialogBean("新建",false));
                    mList.add(new ScreenDialogBean("施工中",false));
                    mList.add(new ScreenDialogBean("完成",false));
                    mList.add(new ScreenDialogBean("已验收",false));
                    mList.add(new ScreenDialogBean("取消",false));
                    BottomeListDialog.showBottomeDialog(getActivity(), getActivity(), mView, mList, new BottomeListDialog.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Log.d("qqqq","点击监听1==="+position);
                            ToastUtil.showShort(mList.get(position).getText());
                        }
                    });

                }else if(position == 2){
                    View mView = getView();
                    mList = new ArrayList<>();
                    mList.add(new ScreenDialogBean("华东区",false));
                    mList.add(new ScreenDialogBean("华中区",false));
                    mList.add(new ScreenDialogBean("华南区",false));
                    mList.add(new ScreenDialogBean("西南区",false));
                    mList.add(new ScreenDialogBean("西北区",false));
                    BottomeListDialog.showBottomeDialog(getActivity(), getActivity(), mView, mList, new BottomeListDialog.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Log.d("qqqq","点击监听2==="+position);
                            ToastUtil.showShort(mList.get(position).getText());
                        }
                    });

                }

                switch (view.getId()) {
                    case R.id.btn_back_d:
                        //setPopupWindow(view,position);

                        break;
                    case R.id.imageView4:
                        LogUtils.i("YWQ","点击摄像头");
                        /*Intent intent=new Intent(getActivity(), CameraActivity.class);
                        startActivity(intent);*/


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
//                    case 13:
//                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_IMG_IMG_ADD, tv01[i], et02[i]));
//                        break;
//                    case 14:
//                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_BTN, tv01[i], et02[i]));
//                        break;
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

    private void startCameras() {
        //添加相机，存储权限
        RxPermissions rxPermissions = new RxPermissions(getActivity());
        rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    MultiImageSelector.create()
                            .showCamera(true) // 是否显示相机. 默认为显示
                            .count(6) // 最大选择图片数量, 默认为9. 只有在选择模式为多选时有效
                            .single() // 单选模式
                            .multi() // 多选模式, 默认模式;
                            .origin(mImageList)
                            .start(getActivity(), MULTI_IMG);
                } else {
                    // 用户拒绝了该权限，并且选中不再询问,引导用户跳转权限管理页面
                }
            }
        });


    }

    private void showSelectDialog(final int pos) {
        if (!ToolUtil.isEmpty(mImageList)) {
            mImageList.remove(pos);
            myImgAdapter.notifyDataSetChanged();
        }

        /*new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("是否删除当前照片?")
                // .setContentText("删除后无法恢复!")
                .setConfirmText("是")
                .setCancelText("取消")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        if (!ToolUtil.isEmpty(mImageList)) {
                            mImageList.remove(pos);
                            myImgAdapter.notifyDataSetChanged();
                        }
                    }
                })
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                })
                .show();*/
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ArrayList<String> path) {
        Log.d("qqqq","传过来的个数=="+path.size());
        mImageList.clear();
        mImageList.addAll(path);
        myImgAdapter.notifyDataSetChanged();
    }

}
