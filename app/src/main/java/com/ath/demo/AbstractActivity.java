package com.ath.demo;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public abstract class AbstractActivity extends FragmentActivity {

    @LayoutRes
    public abstract int getLayout();

    public abstract void initialiseLayout();

    public abstract void runOperation();

    public abstract void stopOperation();

    public abstract void destroyLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initialiseLayout();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        runOperation();
    }

    public void attachFragment(Fragment fragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.add(R.id.frame_layout, fragment, fragment.getClass().getSimpleName());
        transaction.addToBackStack(null);

        transaction.commit();
    }

    public void replaceFragment(Fragment fragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frame_layout, fragment, fragment.getClass().getSimpleName());
        transaction.addToBackStack(null);

        transaction.commit();
    }

    public void removeFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.remove(fragment);

        transaction.commit();
    }

    @Override
    protected void onPause() {
        stopOperation();
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        destroyLayout();
        super.onDestroy();
    }
}
