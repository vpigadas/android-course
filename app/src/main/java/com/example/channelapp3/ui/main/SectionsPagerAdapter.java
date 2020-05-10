package com.example.channelapp3.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.channelapp3.R;
import com.example.channelapp3.alphaFragment;
import com.example.channelapp3.ant1Fragment;
import com.example.channelapp3.megaFragment;
import com.example.channelapp3.openFragment;
import com.example.channelapp3.skaiFragment;
import com.example.channelapp3.starFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new starFragment();
                break;
            case 1:
                fragment = new alphaFragment();
                break;
            case 2:
                fragment = new ant1Fragment();
                break;
            case 3:
                fragment = new megaFragment();
            case 4:
                fragment = new skaiFragment();
            case 5:
                fragment = new openFragment();
                break;
        }

        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "STAR CHANNEL";
            case 1:
                return "ALPHA CHANNEL";
            case 2:
                return "ANT1 CHANNEL";
            case 3:
                return "MEGA CHANNEL";
            case 4:
                return "SKAI CHANNEL";
            case 5:
                return "OPEN CHANNEL";
        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}