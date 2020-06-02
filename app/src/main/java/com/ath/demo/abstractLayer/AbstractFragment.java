
package com.ath.demo.abstractLayer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class AbstractFragment extends Fragment {

    public abstract int getLayout();

    public abstract void initLayout(View view);

    public abstract void runOperation();

    public abstract void stopOperation();

    public abstract void destroyLayout();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initLayout(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        runOperation();
    }

    @Override
    public void onPause() {
        stopOperation();
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        destroyLayout();
        super.onDestroyView();
    }
}
