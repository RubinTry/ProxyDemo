package com.wzlsz.proxy.proxydemo.common.http;

import com.wzlsz.proxy.proxydemo.common.bean.BaseModel;
import com.wzlsz.proxy.proxydemo.common.bean.TestModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author logcat
 * api接口管理器
 */
public interface ApiService {
    @GET("/wxarticle/chapters/json")
    Observable<BaseModel<List<TestModel>>> getWxArticle();
}
