package com.tushuangxi.smart.tv.lding.widget.toptabbar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 名称：TitleFragmentPagerAdapter.java 描述：一个通用的Fragment适配器
 *
 *
 *
 //        List<Fragment> fragments = new ArrayList<>();
 //        fragments.add(new Fragment1());
 //        fragments.add(new Fragment2());
 //        fragments.add(new Fragment3());
 //        fragments.add(new Fragment4());
 //
 //        TitleFragmentPagerAdapter adapter = new TitleFragmentPagerAdapter(getSupportFragmentManager(), fragments, new String[]{"版本信息", "使用教程", "客户服务", "组织设置"});
 //        mViewPager.setAdapter(adapter);
 //        mTabLayout.setupWithViewPager(mViewPager);
 */
public class TitleFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList = null;
    private String[] titles;

    public TitleFragmentPagerAdapter(FragmentManager mFragmentManager,
                                     ArrayList<Fragment> fragmentList) {
        super(mFragmentManager);
        mFragmentList = fragmentList;
    }

    /**
     * titles是给TabLayout设置title用的
     *
     * @param mFragmentManager
     * @param fragmentList
     * @param titles
     */
    public TitleFragmentPagerAdapter(FragmentManager mFragmentManager,
                                     List<Fragment> fragmentList, String[] titles) {
        super(mFragmentManager);
        mFragmentList = fragmentList;
        this.titles = titles;
    }

    /**
     * 描述：获取数量.
     * @return the count
     */
    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    /**
     * 描述：获取索引位置的Fragment.
     * @param position the position
     * @return the item
     */
    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        if (position < mFragmentList.size()) {
            fragment = mFragmentList.get(position);
        } else {
            fragment = mFragmentList.get(0);
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles != null && titles.length > 0)
            return titles[position];
        return null;
    }
}
