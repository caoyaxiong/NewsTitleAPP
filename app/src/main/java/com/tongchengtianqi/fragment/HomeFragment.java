package com.tongchengtianqi.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.tongchengtianqi.R;
import com.tongchengtianqi.activity.MainActivity;
import com.tongchengtianqi.activity.SearchActivity;
import com.tongchengtianqi.view.SearchView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment  {
    private MainActivity mainActivity;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private String[]title={"推荐","热点","娱乐","电影","游戏","社会","笑话","军事","科技","汽车","体育","财经"};
    private List<Fragment> list;

    private ImageView more_channer;
    private SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.home_page, null);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = view.findViewById(R.id.home_tab);
        viewPager = view.findViewById(R.id.home_vp);
        searchView = view.findViewById(R.id.search_view);
        more_channer = view.findViewById(R.id.more_channer);

        initListener();
        initData();

        viewPager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        });
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);
    }
   private void initListener() {
       searchView.setLogo_buttonOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(getContext(), "Logo", Toast.LENGTH_SHORT).show();
           }
       });
       searchView.setLogin_buttonOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(getContext(), "跳转登录页面", Toast.LENGTH_SHORT).show();
           }
       });
       searchView.setSearch_edOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(getContext(), SearchActivity.class);
               startActivity(intent);
           }
       });
        //跳转频道管理页面
        more_channer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "跳转频道管理页面", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            Bundle bundle = new Bundle();
            bundle.putInt("id", i);
            TitleFragment titleFragment = new TitleFragment();
            titleFragment.setArguments(bundle);
            list.add(titleFragment);
        }
    }



}



