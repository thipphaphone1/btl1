package com.example.app_doc_bao;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPager_Adapter extends FragmentStatePagerAdapter {
    List<Fragment_News> listFr = new ArrayList<>();
    List<String> listTitl= new ArrayList<>();

    public ViewPager_Adapter(FragmentManager fm) {
        super(fm);
    }

    public  void addFrag(Fragment_News fragment_news,String chuoi){
        listFr.add(fragment_news);
        listTitl.add(chuoi);
    }

    @Override
    public Fragment getItem(int i) {
        return listFr.get(i);
    }

    @Override
    public int getCount() {
        return listTitl.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return  listTitl.get(position);
    }
}
