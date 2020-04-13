package com.athtech.tv_app;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class TestClass implements Serializable {
    int x=5;

    public String getXString(){
        String str="x is"+x;
        return str;
    }

}
