package com.ucsdextandroid1.snapapp.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ucsdextandroid1.snapapp.camera.EmptyFragment;
import com.ucsdextandroid1.snapapp.chat.ChatFragment;
import com.ucsdextandroid1.snapapp.stories.StoriesFragment;

/**
 * Created by rjaylward on 4/15/19
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return ChatFragment.create();
            case 1:
                return EmptyFragment.create();
            case 2:
                return StoriesFragment.create();
        }

        throw new IllegalArgumentException("no fragment for position" + position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0:
                return "Chat";
            case 1:
                return "Search";
            case 2:
                return "Stories";
        }

        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return 3;
    }

}

