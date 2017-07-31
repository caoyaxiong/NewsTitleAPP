package com.tongchengtianqi.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.tongchengtianqi.R;
import com.tongchengtianqi.fragment.HomeFragment;
import com.tongchengtianqi.fragment.MineFragment;
import com.tongchengtianqi.fragment.VedioFragment;

public class MainActivity extends FragmentActivity {
    private FragmentTabHost mTabHost;
    private LayoutInflater mLayoutInflater;
    private Class mFragmentArray[] = { HomeFragment.class, VedioFragment.class,
            MineFragment.class };
    private int mImageArray[] = { R.drawable.home_page_button,
            R.drawable.vedio_page_button,
            R.drawable.mine_page_button };
    private String mTextArray[] = { "首页", "视频", "我的" };
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }
    private void initView() {
        mLayoutInflater = LayoutInflater.from(this);
        // 实例化tabhost  
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.fl);
        int count = mFragmentArray.length;
        for (int i = 0; i < count; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextArray[i])
                    .setIndicator(getTabItemView(i));
            mTabHost.addTab(tabSpec, mFragmentArray[i], null);
        }
        //去掉分割的竖线
        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
    }

    private View getTabItemView(int index) {
        //得到布局实例
        View view = mLayoutInflater.inflate(R.layout.tab_item_view, null);
        //得到布局对象
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        //设置图标
        imageView.setImageResource(mImageArray[index]);
        //设置标题
        textView.setText(mTextArray[index]);

        return view;
    }

}
