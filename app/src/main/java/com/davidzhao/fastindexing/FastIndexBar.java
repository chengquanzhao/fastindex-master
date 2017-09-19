package com.davidzhao.fastindexing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by davidzhao on 2017/9/19.
 */

public class FastIndexBar extends View {
    private Paint mPaint;

    private int mWidth;
    private float mHeight;
    private String[] mIndexArr = {"A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"};

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
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(16);
        mPaint.setTextAlign(Paint.Align.CENTER);//设置起点是文字边框底边的中心
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
            canvas.drawText(mText, x, y, mPaint);
        }
    }

    private int getTextHeight(String mText, Paint mPaint) {


        return 0;
    }
}
