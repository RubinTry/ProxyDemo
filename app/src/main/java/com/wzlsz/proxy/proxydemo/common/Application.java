package com.wzlsz.proxy.proxydemo.common;

import android.content.Context;

import com.wzlsz.proxy.proxydemo.common.utils.ToastUtils;
import com.wzlsz.proxy.proxydemo.common.utils.Utils;

public class Application extends android.app.Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        ToastUtils.init(false);
        context = this;
    }
    
    
    public static Context getContext(){
        return context;
    }
}
