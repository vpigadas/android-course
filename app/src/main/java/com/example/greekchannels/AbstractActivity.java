package com.example.greekchannels;

import androidx.annotation.LayoutRes;
import androidx.fragment.app.FragmentActivity;

public abstract class AbstractActivity extends FragmentActivity {

    @LayoutRes
    public abstract int getLayout();

    public abstract void initialiseLayout();

    public abstract void runOperation();

    public abstract void stopOperation();

    public abstract void destroyLayout();

}
