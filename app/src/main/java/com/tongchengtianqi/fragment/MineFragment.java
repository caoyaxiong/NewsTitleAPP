package com.tongchengtianqi.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tongchengtianqi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=View.inflate(getContext(), R.layout.mine_page,null);
        return view;
    }

}
