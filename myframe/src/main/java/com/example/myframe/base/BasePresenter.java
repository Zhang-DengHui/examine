package com.example.myframe.base;

import com.example.myframe.interfaces.IBasePersenter;
import com.example.myframe.interfaces.IBaseView;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

abstract class BasePersenter<V extends IBaseView> implements IBasePersenter<V> {
    protected V mView;
    //对V层进行弱引用
    WeakReference<V> weakReference;

    protected CompositeDisposable compositeDisposable;
    @Override
    public void attachView(V view) {
        weakReference = new WeakReference<>(view);
        mView = weakReference.get();
    }
    //把当前业务下的网络请求对象添加到compositeDisposable
    protected void addSubscribe(Disposable disposable){
        if(compositeDisposable==null){
            compositeDisposable=new CompositeDisposable();
            compositeDisposable.add(disposable);
        }
    }
    protected void unSubsoribe(){
        if(compositeDisposable!=null){
            compositeDisposable.clear();
        }
    }

    @Override
    public void detachView() {
        this.mView=null;
    }
}