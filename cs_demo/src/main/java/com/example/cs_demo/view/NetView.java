package com.example.cs_demo.view;

import com.example.cs_demo.Bean;

public interface View {
    void setData(Bean bean);
    void showToast(String str);
}
