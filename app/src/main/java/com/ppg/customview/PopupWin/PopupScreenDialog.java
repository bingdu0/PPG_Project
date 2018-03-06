package com.ppg.customview.PopupWin;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import com.ppg.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by lixu on 2018/3/6.
 */

public class PopupScreenDialog extends BasePopupWindow implements View.OnClickListener{
    private TextView ok;
    private TextView rest;


    public PopupScreenDialog(Context context) {
        super(context);

//        ok= (TextView) findViewById(R.id.bt_popup_finish);
//        rest= (TextView) findViewById(R.id.bt_popup_rest);
//        setViewClickListener(this,ok,rest);
    }

    @Override
    protected Animation initShowAnimation() {
        AnimationSet set=new AnimationSet(false);
        Animation shakeAnima=new RotateAnimation(0,15,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        shakeAnima.setInterpolator(new CycleInterpolator(5));
        shakeAnima.setDuration(400);
        set.addAnimation(getDefaultAlphaAnimation());
        set.addAnimation(shakeAnima);
        return set;
    }

    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.dialog_screen);
    }

    @Override
    public View initAnimaView() {
        //return findViewById(R.id.ll_dialog_screen);
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_popup_finish:
                break;
            case R.id.bt_popup_rest:
                break;
            default:
                break;
        }
    }
}
