package com.ppg.mvp.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ppg.R;
import com.ppg.base.BaseActivity;
import com.ppg.base.BasePresenter;
import com.ppg.mvp.view.adapter.MyImgAdapter;
import com.ppg.utils.PopupWindowUtil;
import com.ppg.utils.StringUtils;
import com.ppg.utils.ToastUtil;
import com.ppg.utils.ToolUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by lixu on 2018/3/7.
 */

public class OtherInfoActivity extends BaseActivity{
    private final int MULTI_IMG = 130;

    @BindView(R.id.rv_horizontal)
    RecyclerView recyclerViewImg;
    @BindView(R.id.base_title)
    TextView tvTitle;
    @BindView(R.id.ll_spinner)
    LinearLayout ll_spinner;
    @BindView(R.id.tv_sp)
    TextView tv_sp;

    private  String mType;


    /**
     * 多张图片相关
     */
    private int IMAGE_MAX = 6;
    private MyImgAdapter myImgAdapter;
    private GridLayoutManager gridLayoutManager;
    private ArrayList<String> mImageList = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_other_info;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {

        Intent gIntent = getIntent();
        mType = gIntent.getExtras().getString("type","");
        tvTitle.setText(mType);
        if(StringUtils.isEmpty(mType) && mType.equals("落实情况")){
            ll_spinner.setVisibility(View.GONE);
        }


        myImgAdapter = new MyImgAdapter(OtherInfoActivity.this, mImageList);
        gridLayoutManager = new GridLayoutManager(OtherInfoActivity.this, 3);
        recyclerViewImg.setLayoutManager(gridLayoutManager);
        recyclerViewImg.setHasFixedSize(true);
        recyclerViewImg.setItemAnimator(new DefaultItemAnimator());
        recyclerViewImg.setAdapter(myImgAdapter);
        myImgAdapter.setOnItemClickListener(new MyImgAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent picIntent = new Intent(OtherInfoActivity.this, ImageActivity.class);
                picIntent.putExtra(ImageActivity.IMAGE_INTENT, mImageList.get(position));
                startActivity(picIntent);
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
        ll_spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPopupWindow(view);
            }
        });
    }


    private void startCameras() {
        MultiImageSelector.create()
                .showCamera(true) // 是否显示相机. 默认为显示
                .count(6) // 最大选择图片数量, 默认为9. 只有在选择模式为多选时有效
                .single() // 单选模式
                .multi() // 多选模式, 默认模式;
                .origin(mImageList)
                .start(OtherInfoActivity.this, MULTI_IMG);
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
    public void setPopupWindow(View mView){
        final List<String> items = new ArrayList<>();
        items.add("供应商问题");
        items.add("其它问题");

        // 点击控件后显示popup窗口
        final PopupWindowUtil popupWindow = new PopupWindowUtil(OtherInfoActivity.this, items);

        popupWindow.setItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupWindow.dismiss();
                tv_sp.setText(items.get(position));

            }
        });
        //根据后面的数字 手动调节窗口的宽度
        popupWindow.show(mView, 1);
    }
}
