package com.ppg.bean;

/**
 * Created by lixu on 2018/3/7.
 */

public class ScreenDialogBean {
    private String text;
    private boolean isSelect;

    public ScreenDialogBean() {
    }

    public ScreenDialogBean(String text, boolean isSelect) {
        this.text = text;
        this.isSelect = isSelect;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
