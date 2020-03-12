package com.example.myframe.interfaces;

public interface IBasePersenter<V extends IBaseView> {
    void attachView(V view);
    void detachView();
}

