package com.wzlsz.proxy.proxydemo.common.http;

import android.os.Environment;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.wzlsz.proxy.proxydemo.BuildConfig;
import com.wzlsz.proxy.proxydemo.common.Application;

import java.io.File;
import java.util.concurrent.TimeUnit;


import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author logcat
 * retrofit管理器
 */
public class RetrofitManager {
    private static ApiService service;
    
    
    public static ApiService getDefault(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        
        
        if(BuildConfig.DEBUG){
            //日志拦截
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
            
        }
        
        CommonInterceptor commonInterceptor = new CommonInterceptor();
        
        OkHttpClient client = builder
                .connectTimeout(10 , TimeUnit.SECONDS)
                .readTimeout(10 , TimeUnit.SECONDS)
                .writeTimeout(10 , TimeUnit.SECONDS)
                .cache(new Cache(new File(Environment.getExternalStorageDirectory() + "/RxJavaDemo"),1024*1024*10))
                .cookieJar(new PersistentCookieJar(new SetCookieCache() , new SharedPrefsCookiePersistor(Application.getContext())))
                .retryOnConnectionFailure(true)
                .addInterceptor(commonInterceptor)
                .build();
        
        
        service = new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.wanandroid.com")
                .build()
                .create(ApiService.class);
        
        
        return service;
    }
}
