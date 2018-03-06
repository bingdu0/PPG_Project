package com.ppg.customview.PopupWin;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;

import com.ppg.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * 从底部滑上来的popup
 */
public class PopupWinFromToBo extends BasePopupWindow implements View.OnClickListener {

    private View popupView;

    public PopupWinFromToBo(Activity context) {
        super(context);
        bindEvent();
    }

    @Override
    protected Animation initShowAnimation() {
        return getTranslateAnimation(250*2, 0, 300);
    }

    @Override
    public View getClickToDismissView() {
        return popupView.findViewById(R.id.click_to_dismiss);
    }

    @Override
    public View onCreatePopupView() {
        popupView = LayoutInflater.from(getContext()).inflate(R.layout.popup_img_img_b, null);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return popupView.findViewById(R.id.popup_anima);
    }

    private void bindEvent() {
        if (popupView != null) {
//            popupView.findViewById(R.id.tx_1).setOnClickListener(this);
//            popupView.findViewById(R.id.tx_2).setOnClickListener(this);
//            popupView.findViewById(R.id.tx_3).setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.tx_1:
//                ToastUtils.ToastMessage(getContext(), "click tx_1");
//                break;
//            case R.id.tx_2:
//                ToastUtils.ToastMessage(getContext(), "click tx_2");
//
//                break;
//            case R.id.tx_3:
//                ToastUtils.ToastMessage(getContext(), "click tx_3");
//                break;
//            default:
//                break;
//        }

    }



}
