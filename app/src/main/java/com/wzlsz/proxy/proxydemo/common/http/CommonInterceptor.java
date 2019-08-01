package com.wzlsz.proxy.proxydemo.common.http;

import android.text.TextUtils;

import com.orhanobut.hawk.Hawk;
import com.wzlsz.proxy.proxydemo.common.Constants;
import com.wzlsz.proxy.proxydemo.common.bean.LoginInfo;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CommonInterceptor implements Interceptor {
    
    private String token;

    public CommonInterceptor() {
        token = "";
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        //添加统一的token
        token = "";
        Request oldRequest = chain.request();
        HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host());

        LoginInfo loginInfo = Hawk.get(Constants.LOGININFO);
        
        if(loginInfo != null && !TextUtils.isEmpty(loginInfo.getToken())){
            token = loginInfo.getToken();
            authorizedUrlBuilder.addQueryParameter("token" , token);
        }
        
        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method() , oldRequest.body())
                .url(authorizedUrlBuilder.build())
                .build();
        return chain.proceed(newRequest);
    }
}
