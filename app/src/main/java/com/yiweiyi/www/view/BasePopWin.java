package com.yiweiyi.www.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.yiweiyi.www.R;
import com.yiweiyi.www.callback.OnPopupwindow;

import java.util.List;

/**
 * Created by gs on 2020/10/29.
 */
public class BasePopWin extends PopupWindow {
    private View mainView;
    private ListView lv_year;

    public BasePopWin(Context context, OnPopupwindow listener, int paramInt1, List<String> list){
        super(context);
        //窗口布局
        mainView = LayoutInflater.from(context).inflate(R.layout.popwin_view, null);

        lv_year = ((ListView)mainView.findViewById(R.id.lv_year));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                R.layout.iten_list_popup,list);
        lv_year.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listener != null){
                    listener.setOnClickListener(list.get(position));
                }
                dismiss();
            }
        });
        lv_year.setAdapter(adapter);

        setContentView(mainView);
        //设置宽度
        setWidth(paramInt1);
        //设置高度
//        setHeight(paramInt2);
        //设置显示隐藏动画
        setAnimationStyle(R.style.AnimTools);
        //设置背景透明
        setBackgroundDrawable(new ColorDrawable(0));
    }
}
