package com.ppg.bean;

/**
 * Create by Donny.
 * 2017/12/6
 * Description：
 */
public class RxBusMessage {
    private int intType;
    private String stringType;
    private Object object;


    public RxBusMessage(int intType) {
        this.intType=intType;
    }

    public RxBusMessage(int intType, Object object) {
        this.intType=intType;
    }

    public RxBusMessage(String stringType) {
        this.stringType=stringType;
    }

    public RxBusMessage(String stringType, Object object) {
        this.stringType=stringType;
    }
    public int getIntType() {
        return intType;
    }

    public String getStringType() {
        return stringType;
    }

    public Object getObject() {
        return object;
    }
}
