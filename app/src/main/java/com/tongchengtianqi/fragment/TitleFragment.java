package com.tongchengtianqi.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tongchengtianqi.R;

/**
 * 1. 类的用途
 * 2. @author dell
 * 3. @date 2017/2/12 18:55
 */

public class TitleFragment extends Fragment  {
    private int currentPage = 0;
    private View view;

    private String url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.titlefragment, null);

        return view;
        
    }


}
