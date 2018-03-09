package com.ppg.mvp.view.activity;

import android.Manifest;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BaseApplication;
import com.ppg.base.BasePresenter;
import com.ppg.bean.TestBean;
import com.ppg.constants.Constant;
import com.ppg.mvp.view.adapter.MyImgAdapter;
import com.ppg.mvp.view.adapter.ReportTeDisRFourAdapter;
import com.ppg.mvp.view.adapter.SampleProblemAdapter;
import com.ppg.utils.LogUtils;
import com.ppg.utils.ToastUtil;
import com.ppg.utils.ToolUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * 样板效果及问题
 */
public class SampleProblemsActivity extends BaseActivity {

    @BindView(R.id.rv_sample_problem)
    RecyclerView rvSampleProblem;

    @BindView(R.id.rv_image)
    RecyclerView rvImage;

    private List<TestBean> textBeanList = new ArrayList<>();
    private String[] tv01 = {"样板基面情况", "ESP保温基面", "围墙水泥抹灰面", "PH值大于10", "PH值小于10", "含水10以内", "含水率10-15", "含水率大于20", "基面完好", "基面粉化", "基面开裂", "腻子", "双组分灰色", "双组分白色", "单组分灰色", "单组分白色"};
    private String[] tv02 = {"", "", "", "", "", "", "", "", "", "", "", "", "R 型粗腻子", "P型细腻子", "", "P型细腻子",};
    private SampleProblemAdapter mSampleProblemAdapter;



    /**
     * 多张图片相关
     */
    private int IMAGE_MAX = 6;
    private MyImgAdapter myImgAdapter;
    private GridLayoutManager gridLayoutManager;
    private ArrayList<String> mImageList = new ArrayList<>();
    private final int MULTI_IMG = 130;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sample_problems;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        baseTitle.setText("现场样板效果及问题");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BaseApplication.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
        rvSampleProblem.setLayoutManager(linearLayoutManager);
        rvSampleProblem.setHasFixedSize(true);
        rvSampleProblem.setNestedScrollingEnabled(false);
        mSampleProblemAdapter = new SampleProblemAdapter(this, textBeanList);
        rvSampleProblem.setAdapter(mSampleProblemAdapter);





        myImgAdapter = new MyImgAdapter(SampleProblemsActivity.this, mImageList);
        gridLayoutManager = new GridLayoutManager(SampleProblemsActivity.this, 3);
        rvImage.setLayoutManager(gridLayoutManager);
        rvImage.setHasFixedSize(true);
        rvImage.setItemAnimator(new DefaultItemAnimator());
        rvImage.setAdapter(myImgAdapter);
        myImgAdapter.setOnItemClickListener(new MyImgAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {

                RxPermissions rxPermissions = new RxPermissions(SampleProblemsActivity.this);
                rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            Intent picIntent = new Intent(SampleProblemsActivity.this, ImageActivity.class);
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
    protected void initListener() {

    }

    private void getData() {
        textBeanList.clear();
        if (tv01.length == tv02.length) {
            for (int i = 0; i < tv01.length; i++) {

                switch (i) {
                    case 0:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TEXT_WARNING, tv01[i], tv02[i]));


                        break;

                    case 1:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV, tv01[i], tv02[i]));

                        break;

                    case 2:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV, tv01[i], tv02[i]));

                        break;

                    case 3:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV, tv01[i], tv02[i]));

                        break;

                    case 4:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV, tv01[i], tv02[i]));

                        break;
                    case 5:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV, tv01[i], tv02[i]));

                        break;
                    case 6:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV, tv01[i], tv02[i]));

                        break;
                    case 7:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV, tv01[i], tv02[i]));

                        break;
                    case 8:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV, tv01[i], tv02[i]));

                        break;
                    case 9:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV, tv01[i], tv02[i]));
                        break;
                    case 10:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_TEXT_WARNING, tv01[i], tv02[i]));
                        break;
                    case 11:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV_TV, tv01[i], tv02[i]));
                        break;
                    case 12:

                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV_TV, tv01[i], tv02[i]));
                        break;
                    case 13:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV_TV, tv01[i], tv02[i]));
                        break;
                    case 14:
                        textBeanList.add(new TestBean(Constant.ITEM_TYPE_CB_TV_TV, tv01[i], tv02[i]));
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
            mSampleProblemAdapter.notifyDataSetChanged();
            LogUtils.i("LK", "数组长度为：" + tv01.length);
        } else {
            LogUtils.i("LK", "数组长度不一样");
        }
    }



    private void startCameras() {
        //添加相机，存储权限
        RxPermissions rxPermissions = new RxPermissions(SampleProblemsActivity.this);
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
                            .start(SampleProblemsActivity.this, MULTI_IMG);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case MULTI_IMG: //多张图片返回
                ArrayList<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                mImageList.clear();
                mImageList.addAll(path);
                myImgAdapter.notifyDataSetChanged();
                break;
        }
    }
}
