package com.example.manhua.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.manhua.GuomanFragment;
import com.example.manhua.RecommendFragment;
import com.example.manhua.RimanFragment;

public class DashboardViewPagerAdapter extends FragmentPagerAdapter {

    private static final String[] PAGE_TITLE = {"推荐", "国漫", "日漫"};
    public DashboardViewPagerAdapter(@NonNull FragmentManager fm)
    {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position%3 == 0){
            return new RecommendFragment();
        }else if (position%3 == 1){
            return new GuomanFragment();
        }else{
            return new RimanFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    // 获取当前Fragment的标题
    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        return PAGE_TITLE[position];
    }
}
