package com.ppg.mvp.view.activity;

import android.Manifest;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BasePresenter;
import com.ppg.bean.ScreenDialogBean;
import com.ppg.mvp.view.adapter.CheckBoxSmallAdapter;
import com.ppg.mvp.view.adapter.MyImgAdapter;
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
 * Created by lixu on 2018/3/8.
 */

public class ComplainBrieflyActivity extends BaseActivity{

    private List<ScreenDialogBean> cbList = new ArrayList<>();
    private CheckBoxSmallAdapter checkBoxSmallAdapter;
    private GridLayoutManager gridLayoutManager2;
    @BindView(R.id.rv_issue_info)
    RecyclerView rv1;


    @BindView(R.id.rv_horizontal)
    RecyclerView recyclerViewImg;

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
        return R.layout.activity_complain_briefly;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        baseTitle.setText("现场投诉问题概括");
        checkBoxSmallAdapter = new CheckBoxSmallAdapter(ComplainBrieflyActivity.this,R.layout.item_small_checkbar,cbList);
        gridLayoutManager2 = new GridLayoutManager(ComplainBrieflyActivity.this,3);
        rv1.setLayoutManager(gridLayoutManager2);
        rv1.setHasFixedSize(true);
        rv1.setItemAnimator(new DefaultItemAnimator());
        rv1.setAdapter(checkBoxSmallAdapter);
        initCbDataForTest();




        myImgAdapter = new MyImgAdapter(ComplainBrieflyActivity.this, mImageList);
        gridLayoutManager = new GridLayoutManager(ComplainBrieflyActivity.this, 3);
        recyclerViewImg.setLayoutManager(gridLayoutManager);
        recyclerViewImg.setHasFixedSize(true);
        recyclerViewImg.setItemAnimator(new DefaultItemAnimator());
        recyclerViewImg.setAdapter(myImgAdapter);
        myImgAdapter.setOnItemClickListener(new MyImgAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {

                RxPermissions rxPermissions = new RxPermissions(ComplainBrieflyActivity.this);
                rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            Intent picIntent = new Intent(ComplainBrieflyActivity.this, ImageActivity.class);
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
    }

    @Override
    protected void initListener() {

    }

    private void initCbDataForTest(){
        cbList.add(new ScreenDialogBean("色差",true));
        cbList.add(new ScreenDialogBean("起皮／脱落",false));
        cbList.add(new ScreenDialogBean("泛碱",false));
        cbList.add(new ScreenDialogBean("水白",false));
        cbList.add(new ScreenDialogBean("雨痕",false));
        cbList.add(new ScreenDialogBean("雨痕",false));
        cbList.add(new ScreenDialogBean("发花／透底",false));
        cbList.add(new ScreenDialogBean("接痕",false));
        cbList.add(new ScreenDialogBean("流挂",false));
        cbList.add(new ScreenDialogBean("开裂",false));
        cbList.add(new ScreenDialogBean("杂质",false));
        cbList.add(new ScreenDialogBean("用量",false));
        cbList.add(new ScreenDialogBean("粘度",false));
        cbList.add(new ScreenDialogBean("水印",false));
        cbList.add(new ScreenDialogBean("浮色",false));
        cbList.add(new ScreenDialogBean("气味",false));
        cbList.add(new ScreenDialogBean("咬底",false));
        cbList.add(new ScreenDialogBean("颗粒",false));
        cbList.add(new ScreenDialogBean("结皮",false));
        cbList.add(new ScreenDialogBean("刷痕",false));
        cbList.add(new ScreenDialogBean("失光",false));
        cbList.add(new ScreenDialogBean("长霉",false));
        cbList.add(new ScreenDialogBean("褪色/粉化",false));
        checkBoxSmallAdapter.notifyDataSetChanged();
    }

    private void startCameras() {
        //添加相机，存储权限
        RxPermissions rxPermissions = new RxPermissions(ComplainBrieflyActivity.this);
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
                            .start(ComplainBrieflyActivity.this, MULTI_IMG);
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
