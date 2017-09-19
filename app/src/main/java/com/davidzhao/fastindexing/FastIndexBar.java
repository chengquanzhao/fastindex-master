package com.davidzhao.fastindexing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.icu.text.BreakIterator;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import static android.app.Notification.COLOR_DEFAULT;

/**
 * Created by davidzhao on 2017/9/19.
 */

public class FastIndexBar extends View {
    private static final int COLOR_PRESSED = Color.WHITE;
    private Paint mPaint;
    private OnTouchIndexChangedListner mListner;

    private int mWidth;
    private float mHeight;
    private String[] mIndexArr = {"A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"};
    private int mIndex;

    public FastIndexBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();//初始化 画笔
    }

    public FastIndexBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();//初始化 画笔


    }

    public FastIndexBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();//初始化 画笔

    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(COLOR_DEFAULT);
        mPaint.setTextSize(20);
        mPaint.setTextAlign(Paint.Align.CENTER);//设置起点是文字边框底边的中心
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight()*1f/mIndexArr.length;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float x = mWidth / 2; // 绘制文字的起点 问宽度的一半
        for (int i = 0; i < mIndexArr.length; i++) {
            String mText = mIndexArr[i];
            int mTextHeight = getTextHeight(mText, mPaint);
            // 绘制文字y轴的起点 =  格子高度的一半 + 文字高度的一半 + i*格子高度
            float y = mHeight / 2 + mTextHeight / 2 + i * mHeight;
            mPaint.setColor(i==mIndex?COLOR_PRESSED: Color.BLACK);
            canvas.drawText(mText, x, y, mPaint);
        }
    }

    private int getTextHeight(String mText, Paint mPaint) {
        Rect mBounds = new Rect();
        mPaint.getTextBounds(mText,0,mText.length(),mBounds);// 方法执行结束后 mBounds 会被赋值
        return mBounds.height();
    }
    private int lastIndex = -1;//上次触摸字母的索引
    @Override
    public boolean onTouchEvent(MotionEvent event) {
       switch (event.getAction()){
           case MotionEvent.ACTION_DOWN:
           case MotionEvent.ACTION_MOVE:
               float y = event.getY();
               //触摸的字母对应的 index
               mIndex = (int) (y/mHeight);
               if(mIndex >=0 && mIndex <mIndexArr.length){
                   if(mListner!=null){
                       mListner.onLetterChange(mIndexArr[mIndex]);
                   }
               }
               lastIndex = mIndex;
               break;
           case MotionEvent.ACTION_UP:
               lastIndex = -1;
               break;

       }
        invalidate();//每次 执行完后引起重写绘制。改变颜色
        return true;
    }

    public void setOnTouchLetterChangeListener(OnTouchIndexChangedListner listner){
        this.mListner = listner;
    }

    public interface OnTouchIndexChangedListner{
        void onLetterChange(String mText);
    }
}
