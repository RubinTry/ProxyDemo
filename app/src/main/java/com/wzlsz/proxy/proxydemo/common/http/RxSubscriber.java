package com.wzlsz.proxy.proxydemo.common.http;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.wzlsz.proxy.proxydemo.common.bean.BaseModel;
import com.wzlsz.proxy.proxydemo.common.http.widgets.LoadingDialog;
import com.wzlsz.proxy.proxydemo.common.utils.ToastUtils;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * @author logcat
 */
public abstract class RxSubscriber<T> implements Observer<BaseModel<T>> {
    private LoadingDialog loadingDialog;
    private Disposable disposable;
    private FragmentActivity context;
    private String errorMsg;
    private boolean showDialog;

    public RxSubscriber(FragmentActivity context) {
        this(context , true);
    }

    public RxSubscriber(FragmentActivity context , boolean showDialog) {
        this.context = context;
        this.showDialog = showDialog;
    }

    @Override
    public void onSubscribe(Disposable d) {
        disposable = new CompositeDisposable();
        if(showDialog){
            showLoading();
        }
    }

    private void showLoading() {
        if(loadingDialog == null){
            loadingDialog = new LoadingDialog(context);
            loadingDialog.show();
        }
    }

    @Override
    public void onNext(BaseModel<T> tBaseModel) {
        if(!TextUtils.isEmpty(tBaseModel.getErrorMsg())){
            ApiException apiException = new ApiException(tBaseModel.getErrorCode() , tBaseModel.getErrorMsg());
            onError(apiException);
            return;
        }
        
        onSuccess(tBaseModel.getData());
        dismissDialog();
    }

    /**
     * api调用成功并有返回值
     * @param t
     */
    public abstract void onSuccess(T t);


    /**
     * api调用失败并抛出异常
     * @param e
     */
    public abstract void onFail(Exception e);

    @Override
    public void onError(Throwable e) {
        if(e instanceof IOException){
            errorMsg = "网络异常，请检查";
        }else if(e instanceof HttpException){
            /** 网络异常，http 请求失败，即 http 状态码不在 [200, 300) 之间, such as: "server internal error". */
            errorMsg = ((HttpException)e).response().message();
        }else if (e instanceof ApiException) {
            /** 网络正常，http 请求成功，服务器返回逻辑错误 */
            errorMsg = ((ApiException)e).getMessage();
        } else {
            /** 其他未知错误 */
            errorMsg = !TextUtils.isEmpty(e.getMessage()) ? e.getMessage() : "unknown error";
        }
        onError(e);
        dismissDialog();
        ToastUtils.showShortToast(errorMsg);
        if(!disposable.isDisposed()){
            disposable.dispose();
        }
    }

    private void dismissDialog() {
        if(loadingDialog != null && loadingDialog.isShowing()){
            //cancel方法中包含了dismiss()
            loadingDialog.cancel();
        }
    }

    @Override
    public void onComplete() {

    }
}
