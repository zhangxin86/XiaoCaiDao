package ruanjian.xin.xiaocaidao.ui;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ruanjian.xin.xiaocaidao.Client_hot_detail;
import ruanjian.xin.xiaocaidao.Controller.ApplicationController;
import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.adapter.CommentAdapter;
import ruanjian.xin.xiaocaidao.adapter.SpicyAdapter;
import ruanjian.xin.xiaocaidao.adapter.TodayAdapter;
import ruanjian.xin.xiaocaidao.domain.BlogItem;
import ruanjian.xin.xiaocaidao.domain.Parts;
import ruanjian.xin.xiaocaidao.domain.TodayItem;
import ruanjian.xin.xiaocaidao.utils.Calculator;
import ruanjian.xin.xiaocaidao.utils.RefreshView;
import ruanjian.xin.xiaocaidao.utils.Utils;

/**
 * Created by xin on 2016/11/17.
 */

public class MainPage extends Fragment implements RefreshView.RefrshListener{
    private TodayAdapter todayAdapter;
    private SpicyAdapter spicyAdapter;
    private CommentAdapter commentAdapter;
    private ListView Lv_Today;
    private GridView Gv_Spicy;

    private ListView Lv_Comment;
    //跳转相关按钮：
    private LinearLayout Llayactivity_main_page_SeasonFood; //应季食材
    private LinearLayout Llayactivity_main_page_Spicy;      //推荐酱料
    private LinearLayout Llayactivity_main_page_Follow;     //教你做菜
    private LinearLayout Llayactivity_main_pageBreakFast;   //早餐
    private LinearLayout Llayactivity_main_pageAfternoon;   //午餐
    private LinearLayout Llayactivity_main_pageDinner;      //晚餐
    private List<TodayItem> dataToday = new ArrayList<>();
    private List<Parts> dataSpice = new ArrayList<>();
    private List<BlogItem> dataComment = new ArrayList<>();

    private RollPagerView mRollViewPager;
    private RefreshView refreshView;
    private static final int STATE_FRESHED = 3;

    private View v;
    private View.OnClickListener listener;
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
    private String menuName;
    private String mId;

    private final int TODAY_FIRST = 1;
    private final int TODAY_SECOND = 2;
    private final int TODAY_THERR = 3;

    private static String[] Labs = {"早餐","午餐","晚餐","热菜","凉菜","酱料","食材"};

    /*
    * 生命周期相关函数
    * */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_main_page,container,false);
        getView(v);
        setColorSquareListener();


        getTodayData();
        getSpiceData();
        getCommentData();

        setRollViewPager();
        setToday();
        setSpicy();
        setComment();

        OnClickListView();
        return v;
    }

    public void OnClickListView(){
        /*今日推荐的点击事件*/
        Lv_Today.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position+1){
                    case TODAY_FIRST:
                        menuName ="番茄炒虾仁";
                        break;
                    case TODAY_SECOND:
                        menuName = "翡翠彩蔬卷";
                        break;
                    case TODAY_THERR:
                        menuName ="枇杷百合银耳汤";
                }

                mId = "Today";

                Intent intent = new Intent();
                intent.setClass(getActivity(),XiangqingPage.class);
                intent.putExtra("menuName",menuName);
                intent.putExtra("id",mId);
                startActivity(intent);
                //需链接到内容页
            }
        });

        Gv_Spicy.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //跳转到食材详情
                Intent intent = new Intent();
                intent.setClass(getActivity(),SpiceActivity.class);
                startActivity(intent);
            }
        });

        Lv_Comment.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //跳转到评价详情页面
                //parent.getId();
                Log.e("帖子详情跳转Id",""+dataComment.get(position).getId());

                Intent intent = new Intent();
                intent.putExtra("blog_id",""+dataComment.get(position).getId());
                intent.setClass(getActivity(), Client_hot_detail.class);
                startActivity(intent);
            }
        });
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
        Log.i("diaoyong","diaoyong");
        //三个推荐位：
        Lv_Today = (ListView)v.findViewById(R.id.Lvactivity_main_pageToday);
        Gv_Spicy = (GridView)v.findViewById(R.id.Gvactivity_main_pageFoodAndSpicy);
        Lv_Comment = (ListView)v.findViewById(R.id.Lvactivity_main_pageComment);
        //轮播图：
        mRollViewPager = (RollPagerView) v.findViewById(R.id.roll_view_pager);
        //刷新相关：
        refreshView = (RefreshView) v.findViewById(R.id.Sv_refresh);
        refreshView.setRefrshListener(this);
        refreshView.init();
        //中央彩块：
        Llayactivity_main_page_SeasonFood = (LinearLayout)v.findViewById(R.id.Llayactivity_main_page_SeasonFood);
        Llayactivity_main_page_Spicy = (LinearLayout)v.findViewById(R.id.Llayactivity_main_page_Spicy);
        Llayactivity_main_page_Follow = (LinearLayout)v.findViewById(R.id.Llayactivity_main_page_Follow);
        Llayactivity_main_pageBreakFast = (LinearLayout)v.findViewById(R.id.Llayactivity_main_pageBreakFast);
        Llayactivity_main_pageAfternoon = (LinearLayout)v.findViewById(R.id.Llayactivity_main_pageAfternoon);
        Llayactivity_main_pageDinner = (LinearLayout)v.findViewById(R.id.Llayactivity_main_pageDinner);
    }
    //中央彩块相关逻辑：
    public void getColorSquareListener(View.OnClickListener listener){
        this.listener = listener;
    }
    private void setColorSquareListener(){
        Llayactivity_main_page_SeasonFood.setOnClickListener(listener);
        Llayactivity_main_page_Spicy.setOnClickListener(listener);
        Llayactivity_main_page_Follow.setOnClickListener(listener);
        Llayactivity_main_pageBreakFast.setOnClickListener(listener);
        Llayactivity_main_pageAfternoon.setOnClickListener(listener);
        Llayactivity_main_pageDinner.setOnClickListener(listener);
    }
    //轮播图相关逻辑：
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
    //推荐位相关逻辑：（今日推荐、应季食材、今日热帖）
    private void setToday(){
        todayAdapter = new TodayAdapter(getActivity(),dataToday);
        Lv_Today.setAdapter(todayAdapter);
        Calculator.setListViewHeightBasedOnChildren(Lv_Today,0);
    }
    private void setSpicy(){
        spicyAdapter = new SpicyAdapter(getActivity(),dataSpice);
        Gv_Spicy.setAdapter(spicyAdapter);
    }
    private void setComment(){
        commentAdapter = new CommentAdapter(getActivity(),dataComment);
        Lv_Comment.setAdapter(commentAdapter);
        Calculator.setListViewHeightBasedOnChildren(Lv_Comment,0);
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
            final int picNo = position + 1;
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            view.setOnClickListener(new View.OnClickListener()      // 点击事件
            {
                @Override
                public void onClick(View v)
                {
                    switch (picNo){
                        case 1:
                            menuName = "水果沙拉";
                            mId = "RollView";

                            break;
                        case 2:
                            menuName = "鱼香肉丝";
                            mId = "RollView";
                            break;
                        case 3:
                            menuName = "红烧肉";
                            mId = "RollView";
                            break;
                        case 4:
                            menuName = "蔬菜沙拉";
                            mId = "RollView";
                            break;
                    }

                    Intent intent = new Intent();
                    intent.setClass(getActivity(),XiangqingPage.class);
                    intent.putExtra("menuName",menuName);
                    intent.putExtra("id",mId);
                    startActivity(intent);
                }

            });
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
    private void getTodayData(){
        dataToday.clear();
        dataToday.add(new TodayItem("红红纷纷好吃又过瘾~",R.drawable.todayone,"番茄炒虾仁"));
        dataToday.add(new TodayItem("少年，来一卷幸运绿色蔬菜健康卷！！！",R.drawable.todaytwo,"翡翠彩蔬卷"));
        dataToday.add(new TodayItem("喝一口充满暖暖爱意的营养汤！好美味！",R.drawable.todaythree,"枇杷百合银耳汤"));
    }
    private void getSpiceData(){
        dataSpice.clear();
        dataSpice.add(new Parts());
        dataSpice.add(new Parts());
        dataSpice.add(new Parts());
        dataSpice.add(new Parts());
        dataSpice.add(new Parts());
        dataSpice.add(new Parts());
    }

    /**
     *  getCommentData()  从服务器申请热帖数据
     * by 李宇轩
     * */
    public void getCommentData(){
        dataComment.clear();
        StringRequest req=new StringRequest(Request.Method.POST, Utils.URL+"blog/fetchHotBlogList", new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //hidePDialog();
                    }
                },1000);

                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                System.out.println("blog jsonArray:"+jsonArray.toString());
                String[] Ls = {"",""};
                try {
                    for (int i = 0;i<3;i++){
                        JSONObject obj = jsonArray.getJSONObject(i);     //取出每个对象
                        //String user = obj.getString("edit_user");       //取出用户名称，拿到适配器中处理后显示，由此找到 头像 和 昵称
                        String imgsrc = obj.getString("img_src");       //取出图片url
                        System.out.println("imgsrc");
                        int thumb = obj.getInt("thumb");            //点赞数
                        String content = obj.getString("content");      //内容
                        String name = obj.getString("name");
                        String userName = obj.getString("userName");    //userName
                        String userImg = obj.getString("userImg");      //头像url

                        int Id = obj.getInt("id");         //得到此id，不在显示，备于界面跳转到对应帖子的界面

                        String label = obj.getString("label");
                        int count = 0;
                        for (int j=0;j<label.length();j++){
                            char tempChar = label.charAt(j);
                            if (tempChar == '1'){
                                System.out.println(Labs[j]);
                                Ls[count] = Labs[j];
                                count++;
                            }
                            if (count==2){
                                break;
                            }
                            if(j==label.length()&&Labs[j].equals("0")){
                                Ls[0]="";
                                Ls[1]="";
                            }
                        }

                        /*用户名，头像url(没用上在适配器中处理了)，图片，评论数（没用上），内容文字，  id    ，标签1（待用），标签2（待用），点赞数
                        *String account, String avatarUrl, String blogImg, String comment, String countent, Long id, String lab1, String lab2, String thumb*/
//                        BlogItem blog = new BlogItem(userName,userImg,imgsrc,thumb,content,(long)Id,"清淡","养生",thumb);
                        BlogItem blog = new BlogItem(userName,userImg,imgsrc,"",name,(long)Id,Ls[0],Ls[1],thumb);
                        dataComment.add(blog);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //commentAdapter.notifyDataSetChanged();
                Calculator.setListViewHeightBasedOnChildren(Lv_Comment,0);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("CaipuMenuPge","error:"+volleyError.getMessage());
                //hidePDialog();
            }
        });
        ApplicationController.getInstance().addToRequestQueue(req);
    }

}
