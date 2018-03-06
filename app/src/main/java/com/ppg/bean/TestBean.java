package com.ppg.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * Create by Donny.
 * 2017/12/16
 * Description：
 */
public class TestBean implements MultiItemEntity, Serializable {
    private int type = 0;
    private String title;
    private String content;

    public TestBean() {
    }

    public TestBean(int type) {
        this.type = type;
    }

    public TestBean(int type, String title, String content) {
        this.type = type;
        this.title = title;
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int getItemType() {
//        switch (getType()) {
//            case Constant.ITEM_TYPE_TV_EV:
//                return Constant.ITEM_TYPE_TV_EV;
//
//            case Constant.ITEM_TYPE_TV_TV_BTN_D:
//                return Constant.ITEM_TYPE_TV_TV_BTN_D;
//            default:
//                break;
//        }
//        return Constant.ITEM_TYPE_TV_EV;

        return getType();
    }

}
