package com.wzlsz.proxy.proxydemo.common.bean;

/**
 * @author logcat
 */
public class RecyclerData<T> implements Cloneable {
    private T t;
    
    private int dataType;

    public RecyclerData(T t, int dataType) {
        this.t = t;
        this.dataType = dataType;
    }


    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    @Override
    public RecyclerData clone() throws CloneNotSupportedException {
        RecyclerData data = null;
        try {
            data = (RecyclerData) super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return data;
    }
}
