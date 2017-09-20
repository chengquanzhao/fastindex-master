package com.davidzhao.fastindexing;

import android.app.Activity;
import android.os.Handler;
import android.os.Bundle;
import android.view.animation.OvershootInterpolator;
import android.widget.ListView;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends Activity {
    private FastIndexBar quickIndexBar;
    private ListView listview;
    private TextView currentWord;

    private ArrayList<DataBean> friends = new ArrayList<DataBean>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quickIndexBar = (FastIndexBar) findViewById(R.id.quickIndexBar);
        listview = (ListView) findViewById(R.id.listview);
        currentWord = (TextView) findViewById(R.id.currentWord);

        //1.准备数据
        fillList();
        //2.对数据进行排序
        Collections.sort(friends);
        //3.设置Adapter
        listview.setAdapter(new MyAdapter(this,friends));

        quickIndexBar.setOnTouchLetterChangeListener(new FastIndexBar.OnTouchIndexChangedListner() {
            @Override
            public void onLetterChange(String mText) {
                //根据当前触摸的字母，去集合中找那个item的首字母和letter一样，然后将对应的item放到屏幕顶端
                for (int i = 0; i < friends.size(); i++) {
                    String firstWord = friends.get(i).getPinyin().charAt(0)+"";
                    if(mText.equals(firstWord)){
                        //说明找到了，那么应该讲当前的item放到屏幕顶端
                        listview.setSelection(i);
                        break;//只需要找到第一个就行
                    }
                }

                //显示当前触摸的字母
                showCurrentWord(mText);
            }

        });


        //通过缩小currentWord来隐藏
        ViewHelper.setScaleX(currentWord, 0);
        ViewHelper.setScaleY(currentWord, 0);

//		Log.e("tag", PinYinUtil.getPinyin("黑    马"));//HEIMA
//		Log.e("tag", PinYinUtil.getPinyin("#黑**马"));//#HEI**MA
//		Log.e("tag", PinYinUtil.getPinyin("O(∩_∩)O~黑。，马"));//HEIMA
    }
    private boolean isScale = false;
    private Handler handler = new Handler();
    protected void showCurrentWord(String letter) {
        currentWord.setText(letter);
        if(!isScale){
            isScale = true;
            ViewPropertyAnimator.animate(currentWord).scaleX(1f)
                    .setInterpolator(new OvershootInterpolator())
                    .setDuration(450).start();
            ViewPropertyAnimator.animate(currentWord).scaleY(1f)
                    .setInterpolator(new OvershootInterpolator())
                    .setDuration(450).start();
        }

        //先移除之前的任务
        handler.removeCallbacksAndMessages(null);

        //延时隐藏currentWord
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//				currentWord.setVisibility(View.INVISIBLE);
                ViewPropertyAnimator.animate(currentWord).scaleX(0f).setDuration(450).start();
                ViewPropertyAnimator.animate(currentWord).scaleY(0f).setDuration(450).start();
                isScale = false;
            }
        }, 1500);
    }

    private void fillList() {
        // 虚拟数据
        friends.add(new DataBean("唐僧 "));
        friends.add(new DataBean("江流"));
        friends.add(new DataBean("唐三藏"));
        friends.add(new DataBean("玄奘"));
        friends.add(new DataBean("金蝉"));
        friends.add(new DataBean("旃檀功德佛 "));
        friends.add(new DataBean("孙悟空"));
        friends.add(new DataBean("孙行者"));
        friends.add(new DataBean("猴王"));
        friends.add(new DataBean("齐天大圣"));
        friends.add(new DataBean("斗战胜佛 "));
        friends.add(new DataBean("猪悟能"));
        friends.add(new DataBean("猪刚鬣 "));
        friends.add(new DataBean("猪八戒"));
        friends.add(new DataBean("天蓬元帅"));
        friends.add(new DataBean("净坛使者 "));
        friends.add(new DataBean("沙悟净"));
        friends.add(new DataBean("魏征"));
        friends.add(new DataBean("尉迟敬德 "));
        friends.add(new DataBean("车迟国皇后"));
        friends.add(new DataBean("比丘国驿丞"));
        friends.add(new DataBean("穿针儿张旺女 "));
        friends.add(new DataBean("天竺国怡宗皇帝"));
        friends.add(new DataBean("赤尻马猴崩将军"));
        friends.add(new DataBean("车迟国皇后"));
        friends.add(new DataBean("比丘国驿丞"));
        friends.add(new DataBean("凤仙郡郡侯上官氏  "));
        friends.add(new DataBean("灭法国国王"));
        friends.add(new DataBean("乌鸡国太子"));
    }

}
