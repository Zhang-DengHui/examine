package com.example.myframe.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myframe.interfaces.IBasePersenter;
import com.example.myframe.interfaces.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends IBasePersenter> extends AppCompatActivity implements IBaseView {
    public P persenter;
    Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        unbinder = ButterKnife.bind(this);
        initView();
        persenter=createPersenter();
        if(persenter!=null){
            persenter.attachView(this);
        }
        initData();
    }

    protected abstract P createPersenter();

    protected abstract int getLayout();

    protected abstract void initData();

    protected abstract void initView();


    @Override
    public void showError(String str) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在界面移除的时候解绑p层和v层，以及ButterKnife
        if(persenter!=null){
            persenter.detachView();
        }
        if(unbinder!=null){
            unbinder.unbind();
        }
    }
}

