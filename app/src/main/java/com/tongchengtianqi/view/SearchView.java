package com.tongchengtianqi.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tongchengtianqi.R;
import com.tongchengtianqi.activity.MainActivity;

/**
 * Created by 111 on 2017/7/31.
 * 自定义搜索框
 */

public class SearchView extends LinearLayout {
    private MainActivity mainActivity;
    private ImageView logo_button;
    private ImageView login_button;
    private EditText search_ed;
    private Context mContext;
    private View view;

    public SearchView(Context context) {
        super(context);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        //true为显示状态，false隐藏搜索栏目
        view = LayoutInflater.from(mContext).inflate(R.layout.search_view_layout, this,true);
        initView();
    }


    public void setLogo_buttonOnClickListener(OnClickListener listener) {
        logo_button.setOnClickListener(listener);
    }
    public void setLogin_buttonOnClickListener(OnClickListener listener) {
        login_button.setOnClickListener(listener);
    }
    public void setSearch_edOnClickListener(OnClickListener listener) {
        search_ed.setOnClickListener(listener);
    }


    private void initView() {
        logo_button = view.findViewById(R.id.logo_button);
        login_button = view.findViewById(R.id.login_button);
        search_ed = view.findViewById(R.id.search_ed);
    }


//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.logo_button:
//                Toast.makeText(getContext(), "Logo", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.login_button:
//                Toast.makeText(getContext(), "跳转登录页面", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.search_ed:
//                Intent intent = new Intent(getContext(), SearchActivity.class);
//                mainActivity.startActivity(intent);
//                mainActivity.finish();
//                break;
//        }
//    }
}
