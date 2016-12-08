package ruanjian.xin.xiaocaidao.ui;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.ArrayList;
import java.util.List;

import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.adapter.CommentAdapter;
import ruanjian.xin.xiaocaidao.adapter.SpicyAdapter;
import ruanjian.xin.xiaocaidao.adapter.TodayAdapter;
import ruanjian.xin.xiaocaidao.domain.Parts;
import ruanjian.xin.xiaocaidao.ui.Main.ThreeMeals;
import ruanjian.xin.xiaocaidao.utils.Calculator;
import ruanjian.xin.xiaocaidao.utils.RefreshView;

/**
 * Created by xin on 2016/11/17.
 */

public class MainPage extends Fragment implements RefreshView.RefrshListener{
    private TodayAdapter todayAdapter;
    private SpicyAdapter spicyAdapter;
    private CommentAdapter commentAdapter;
    private LinearLayout Ll_Breakfast;
    private LinearLayout Ll_Lunch;
    private LinearLayout Ll_dinner;
    private ListView Lv_Today;
    private GridView Gv_Spicy;
    private ListView Lv_Comment;
    private List<Parts> data1 = new ArrayList<>();
    private List<Parts> data2 = new ArrayList<>();
    private List<Parts> data3 = new ArrayList<>();

    private RollPagerView mRollViewPager;
    private RefreshView refreshView;
    private static final int STATE_FRESHED = 3;

    protected Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==STATE_FRESHED){
                refreshView.ChangeHeaderState(STATE_FRESHED);
                refreshView.isFreshCommplete = true;
                Toast.makeText(getActivity(),"刷新完毕",Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    };

    /*
    * 生命周期相关函数
    * */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main_page,container,false);
        getView(v);
        getData1();
        getData2();
        getData3();
        setRollViewPager();
        setToday();
        setSpicy();
        setComment();

        return v;
    }


    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    refreshView.isFreshCommplete = false;
                    Thread.sleep(2000);
                    myHandler.sendEmptyMessage(STATE_FRESHED);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /*
        * 辅助函数
        * */
    private void getView(View v){
        Lv_Today = (ListView)v.findViewById(R.id.Lvactivity_main_pageToday);
        Gv_Spicy = (GridView)v.findViewById(R.id.Gvactivity_main_pageFoodAndSpicy);
        Lv_Comment = (ListView)v.findViewById(R.id.Lvactivity_main_pageComment);

        Ll_Breakfast = (LinearLayout)v.findViewById(R.id.Llayactivity_main_pageBreakFast);
        Ll_Lunch = (LinearLayout)v.findViewById(R.id.Llayactivity_main_pageAfternoon);
        Ll_dinner = (LinearLayout)v.findViewById(R.id.Llayactivity_main_pageDinner);

        mRollViewPager = (RollPagerView) v.findViewById(R.id.roll_view_pager);

        refreshView = (RefreshView) v.findViewById(R.id.Sv_refresh);
        refreshView.setRefrshListener(this);
        refreshView.init();
    }
    private void setToday(){
        todayAdapter = new TodayAdapter(getActivity(),data1);
        Lv_Today.setAdapter(todayAdapter);
        Calculator.setListViewHeightBasedOnChildren(Lv_Today,0);
    }
    private void setSpicy(){
        spicyAdapter = new SpicyAdapter(getActivity(),data2);
        Gv_Spicy.setAdapter(spicyAdapter);
    }
    private void setComment(){
        commentAdapter = new CommentAdapter(getActivity(),data3);
        Lv_Comment.setAdapter(commentAdapter);
        Calculator.setListViewHeightBasedOnChildren(Lv_Comment,0);
    }
    private void setRollViewPager(){
        //设置播放时间间隔
        mRollViewPager.setPlayDelay(3000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);
        //设置适配器
        mRollViewPager.setAdapter(new TestNormalAdapter());

        //设置指示器（顺序依次）
        //自定义指示器图片
        //设置圆点指示器颜色
        //设置文字指示器
        //隐藏指示器
        //mRollViewPager.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
        mRollViewPager.setHintView(new ColorPointHintView(getActivity(), Color.YELLOW,Color.WHITE));
        //mRollViewPager.setHintView(new TextHintView(this));
        //mRollViewPager.setHintView(null);
    }

    /*
    * 辅助类
    * */
    private class TestNormalAdapter extends StaticPagerAdapter {
        private int[] imgs = {
                R.drawable.img0,
                R.drawable.img1,
                R.drawable.img2,
                R.drawable.img3
        };


        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }


        @Override
        public int getCount() {
            return imgs.length;
        }
    }


    /*
    * 实验函数
    * */
    private void getData1(){
        data1.clear();
        data1.add(new Parts());
        data1.add(new Parts());
        data1.add(new Parts());
    }
    private void getData2(){
        data2.clear();
        data2.add(new Parts());
        data2.add(new Parts());
        data2.add(new Parts());
        data2.add(new Parts());
        data2.add(new Parts());
        data2.add(new Parts());
    }
    private void getData3(){
        data3.clear();
        data3.add(new Parts());
        data3.add(new Parts());
        data3.add(new Parts());
        data3.add(new Parts());
    }
}
