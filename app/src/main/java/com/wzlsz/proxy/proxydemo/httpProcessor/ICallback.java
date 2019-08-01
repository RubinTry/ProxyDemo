package com.wzlsz.proxy.proxydemo.httpProcessor;

/**
 * @author logcat
 */
public interface ICallback<T> {
    /**
     * 请求成功
     * @param t 响应回来的数据包
     */
    void onSuccess(T t);

    /**
     * 请求失败
     * @param e
     */
    void onFail(Exception e);
}
