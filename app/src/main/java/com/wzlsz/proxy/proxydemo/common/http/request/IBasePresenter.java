package com.wzlsz.proxy.proxydemo.common.http.request;

/**
 * @author logcat
 */
public interface IBasePresenter<T> {

    /**
     * 请求成功
     * @param t
     */
    void onNext(T t);

    

    /**
     * 请求失败
     * @param e 异常
     */
    void onError(Exception e);
}
