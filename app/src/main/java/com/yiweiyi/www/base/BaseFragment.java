package com.yiweiyi.www.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Description: 通用fragment父类
 * @Author: zsh 2020/9/23
 */
public class BaseFragment extends Fragment {
    public Context mContext;

    public BaseFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }
}
