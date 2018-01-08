package com.tongdao.viewslideconflict;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tongdao.viewslideconflict.custom.HorizontalScrollViewEx;
import com.tongdao.viewslideconflict.utils.MyUtils;

import java.util.ArrayList;

/**
 * view 滑动冲突
 */
public class MainActivity extends Activity {

    //这种方式采用的是外部拦截法,就是对父容器的onINtercepter做操作
    private HorizontalScrollViewEx mListContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        LayoutInflater layoutInflater = getLayoutInflater();
        mListContainer = (HorizontalScrollViewEx) findViewById(R.id.container);

        int widthPixels = MyUtils.getScreenMetrics(this).widthPixels;
        int heightPixels = MyUtils.getScreenMetrics(this).heightPixels;
        for (int i = 0; i < 3; i++) {
            ViewGroup layout = (ViewGroup) layoutInflater.inflate(R.layout.content_layout, mListContainer, false);
            layout.getLayoutParams().width = widthPixels;
            TextView textView = (TextView) layout.findViewById(R.id.title);
            textView.setText("page " + (i + 1));
            creatList(layout);
            mListContainer.addView(layout);
        }

    }

    private void creatList(ViewGroup layout) {
        ListView listview = (ListView) layout.findViewById(R.id.list);
        ArrayList<String> datas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            datas.add("name " + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.content_list_item, R.id.name, datas);
        listview.setAdapter(adapter);
    }
}
