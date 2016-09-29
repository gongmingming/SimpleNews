package com.lauren.simplenews.menu1.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lauren.simplenews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/13
 */
public class Fragment1 extends Fragment {

    public static final int NEWS_TYPE_TOP = 0;
    public static final int NEWS_TYPE_NBA = 1;
    public static final int NEWS_TYPE_CARS = 2;
    public static final int NEWS_TYPE_JOKES = 3;

    private TabLayout mTablayout;
    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       //用inflater.inflate（）将xml变成View
        View view = inflater.inflate(R.layout.fragment1, null);

        mTablayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        //设置Tablayout的ViewPager个数
        mViewPager.setOffscreenPageLimit(3);

        //定义方法为ViewPager添加适配器
        setupViewPager(mViewPager);

        //设置Tablayout的各个卡片标题
        mTablayout.addTab(mTablayout.newTab().setText(R.string.tab1));
        mTablayout.addTab(mTablayout.newTab().setText(R.string.tab2));
        mTablayout.addTab(mTablayout.newTab().setText(R.string.tab3));
        mTablayout.addTab(mTablayout.newTab().setText(R.string.tab4));

        //将ViewPager放置到Tablayout中
        mTablayout.setupWithViewPager(mViewPager);

        return view;
    }

        private void setupViewPager(ViewPager mViewPager) {
        //Fragment中嵌套使用Fragment时，一定要使用getChildFragmentManager(),否则会有问题
        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_TOP), getString(R.string.tab1));
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_NBA), getString(R.string.tab2));
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_CARS), getString(R.string.tab3));
        adapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_JOKES), getString(R.string.tab4));
        mViewPager.setAdapter(adapter);
    }

 //FragmentPagerAdapter适配器就是用来实现Fragment在ViewPager里面进行滑动切换的
    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
