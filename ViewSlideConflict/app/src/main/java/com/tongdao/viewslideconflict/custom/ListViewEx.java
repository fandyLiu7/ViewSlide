package com.tongdao.viewslideconflict.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * 公司:北京同道伟业体育科技有限公司
 * 作者：Shinelon
 * 时间:on 2018/1/8 11:42
 * 说明:
 */
public class ListViewEx extends ListView {


    private HorizontalScrollViewEx2 mHorizontalScrollViewEx2;
    private int mLastX = 0;
    private int mLastY = 0;


    public ListViewEx(Context context) {
        this(context, null);
    }

    public ListViewEx(Context context, AttributeSet attrs) {
        super(context, attrs, -1);
    }

    public ListViewEx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setHorizontalScrollViewEx2(
            HorizontalScrollViewEx2 horizontalScrollViewEx2) {
        mHorizontalScrollViewEx2 = horizontalScrollViewEx2;
    }

    /**
     * 重写方法,进行内部事件的拦截
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        //初始坐标
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN://按下
                //请求是否禁止拦截事件;返回true 代表禁止,,返回false代表不禁止拦截事件
                mHorizontalScrollViewEx2.requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE://滑动
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    //表示的横向滑动
                    mHorizontalScrollViewEx2.requestDisallowInterceptTouchEvent(false);
                }

                break;
            case MotionEvent.ACTION_UP://抬起
                break;
            default:
                break;
        }
        mLastX = x;
        mLastY = y;
        return super.dispatchTouchEvent(ev);
    }
}
