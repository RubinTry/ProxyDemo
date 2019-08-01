package com.wzlsz.proxy.proxydemo;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Button;

import com.wzlsz.proxy.proxydemo.common.bean.TestModel;
import com.wzlsz.proxy.proxydemo.httpProcessor.HttpProcessor;
import com.wzlsz.proxy.proxydemo.httpProcessor.ICallback;

/**
 * 
 * 买房的客户
 * @author logcat
 */
public class MainActivity extends FragmentActivity implements ICallback {

    private Button btnRequest;
    private HttpProcessor httpProcessor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRequest = findViewById(R.id.btnRequest);
        
        //寻找一家房地产公司并且委托他们购房任务
        httpProcessor = new HttpProcessor(this , this);
    }

    /**
     * 买到房后走这个方法
     * @param o 买到的房子
     */
    @Override
    public void onSuccess(Object o) {
        
        //装修一下房子
        TestModel model = (TestModel) o;
    }

    @Override
    public void onFail(Exception e) {
        //买不到房了
    }
}
