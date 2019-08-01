package com.wzlsz.proxy.proxydemo.common.http.request;

import android.support.v4.app.FragmentActivity;

/**
 * @author logcat
 * 所有请求类的基类,这里封装了一些公共方法
 */
public abstract class BasePresenter <V extends IBasePresenter> {
    protected V view;
    
    protected FragmentActivity context;

    /**
     * 构造方法让Presenter层持有View层的引用
     * @param context
     * @param view
     */
    public BasePresenter(FragmentActivity context, V view) {
        this.context = context;
        this.view = view;
    }
}
