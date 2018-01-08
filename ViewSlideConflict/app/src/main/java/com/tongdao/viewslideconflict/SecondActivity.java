package com.tongdao.viewslideconflict;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tongdao.viewslideconflict.custom.HorizontalScrollViewEx2;
import com.tongdao.viewslideconflict.custom.ListViewEx;
import com.tongdao.viewslideconflict.utils.MyUtils;

import java.util.ArrayList;

public class SecondActivity extends Activity {

    private HorizontalScrollViewEx2 mListContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
    }

    private void initView() {
        LayoutInflater layoutInflater = getLayoutInflater();
        mListContainer = (HorizontalScrollViewEx2) findViewById(R.id.container);

        int widthPixels = MyUtils.getScreenMetrics(this).widthPixels;
        int heightPixels = MyUtils.getScreenMetrics(this).heightPixels;
        for (int i = 0; i < 3; i++) {
            ViewGroup layout = (ViewGroup) layoutInflater.inflate(R.layout.content_layout2, mListContainer, false);
            layout.getLayoutParams().width = widthPixels;
            TextView textView = (TextView) layout.findViewById(R.id.title);
            textView.setText("page " + (i + 1));
            creatList(layout);
            mListContainer.addView(layout);
        }

    }

    private void creatList(ViewGroup layout) {
        ListViewEx listview = (ListViewEx) layout.findViewById(R.id.list2);
        ArrayList<String> datas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            datas.add("name " + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.content_list_item, R.id.name, datas);
        listview.setAdapter(adapter);
        listview.setHorizontalScrollViewEx2(mListContainer);
    }
}
