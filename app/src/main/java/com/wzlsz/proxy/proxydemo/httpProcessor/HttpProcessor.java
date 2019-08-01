package com.wzlsz.proxy.proxydemo.httpProcessor;

import android.support.v4.app.FragmentActivity;

import com.wzlsz.proxy.proxydemo.common.bean.BaseModel;
import com.wzlsz.proxy.proxydemo.common.bean.TestModel;
import com.wzlsz.proxy.proxydemo.common.http.RetrofitManager;
import com.wzlsz.proxy.proxydemo.common.http.RxHelper;
import com.wzlsz.proxy.proxydemo.common.http.RxSubscriber;

import java.util.List;

/**
 * 
 * 代理类（房产公司）
 * @author logcat
 */
public class HttpProcessor {
    private FragmentActivity activity;
    private ICallback callback;

    public HttpProcessor(FragmentActivity activity, ICallback callback) {
        this.activity = activity;
        this.callback = callback;
    }

    /**
     * 这里找到一个卖家
     */
    public void request(){
        RetrofitManager.getDefault().getWxArticle()
                .compose(RxHelper.<BaseModel<List<TestModel>>>observableOnMainThread())
                .subscribe(new RxSubscriber<List<TestModel>>(activity) {
                    @Override
                    public void onSuccess(List<TestModel> testModels) {
                        if(callback != null){
                            callback.onSuccess(testModels);
                        }
                    }

                    @Override
                    public void onFail(Exception e) {
                        if(callback != null){
                            callback.onFail(e);
                        }
                    }
                });
    }
}
