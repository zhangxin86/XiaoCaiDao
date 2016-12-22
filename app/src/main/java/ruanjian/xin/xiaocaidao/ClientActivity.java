package ruanjian.xin.xiaocaidao;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import ruanjian.xin.xiaocaidao.ui.Friend.BlogSubmitActivity;
import ruanjian.xin.xiaocaidao.ui.FriendPage;
import ruanjian.xin.xiaocaidao.ui.MainPage;
import ruanjian.xin.xiaocaidao.ui.MenuPage;
import ruanjian.xin.xiaocaidao.ui.PersonPage;
import ruanjian.xin.xiaocaidao.ui.ThreeMeals;
import ruanjian.xin.xiaocaidao.utils.Utils;

public class ClientActivity extends AppCompatActivity {
    private FrameLayout  Flay_Page;
    private LinearLayout Llay_ClientHead;
    private LinearLayout Llay_MainPage;
    private LinearLayout Llay_MenuPage;
    private LinearLayout Llay_FriendPage;
    private LinearLayout Llay_PersonPage;
    private LinearLayout Llay_TabHost;

    private TextView Tv_MainPage;
    private TextView Tv_MenuPage;
    private TextView Tv_FriendPage;
    private TextView Tv_PersonPage;

    private ImageView Iv_MainPage;
    private ImageView Iv_MenuPage;
    private ImageView Iv_FriendPage;
    private ImageView Iv_PersonPage;

    private MainPage mainPage;
    private MenuPage menuPage;
    private FriendPage friendPage;
    private PersonPage personPage;

    private ImageButton searchButton;//搜索按钮
    private ImageButton addButton;//发帖按钮
    private EditText searchEdit;



    private static int pre_select = 0;

    public static final int BREAKFAST = 0;
    public static final int LUNCH = 1;
    public static final int DINNER = 2;
    /*生命周期函数*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_client);

        findView();
        setListener();
        switch (Utils.flag){
            case 0:
                setMainPage();
                break;
            case 1:
                setMenuPage();
                break;
            case 2:
                setFriendPage();
                break;
            case 3:
                setPersonPage();
                break;
        }
    }
    @Override
    public void onBackPressed() {
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.addCategory(Intent.CATEGORY_HOME);
        startActivity(home);
        super.onBackPressed();
    }

    /*辅助函数*/
    private void findView(){
        Llay_ClientHead = (LinearLayout)findViewById(R.id.Llaylayout_clientHead);
        Llay_MainPage = (LinearLayout)findViewById(R.id.Llaylayout_clientMainPage);
        Llay_MenuPage = (LinearLayout)findViewById(R.id.Llaylayout_clientMenuPage);
        Llay_FriendPage = (LinearLayout)findViewById(R.id.Llaylayout_clientFriendPage);
        Llay_PersonPage = (LinearLayout)findViewById(R.id.Llaylayout_clientPersonPage);
        Llay_TabHost = (LinearLayout)findViewById(R.id.Llaylayout_clientTabHost);


        Tv_MainPage = (TextView)findViewById(R.id.Tvlayout_clientMainPage);
        Tv_MenuPage = (TextView)findViewById(R.id.Tvlayout_clientMenuPage);
        Tv_FriendPage = (TextView)findViewById(R.id.Tvlayout_clientFriendPage);
        Tv_PersonPage = (TextView)findViewById(R.id.Tvlayout_clientPersonPage);

        Iv_MainPage = (ImageView)findViewById(R.id.Ivlayout_clientMainPage);
        Iv_MenuPage = (ImageView)findViewById(R.id.Ivlayout_clientMenuPage);
        Iv_FriendPage = (ImageView)findViewById(R.id.Ivlayout_clientFriendPage);
        Iv_PersonPage = (ImageView)findViewById(R.id.Ivlayout_clientPersonPage);

        searchButton = (ImageButton) findViewById(R.id.Ivlayout_clientPerMessage);
        addButton = (ImageButton) findViewById(R.id.Ivlayout_clientUpMessage);
        searchEdit = (EditText)findViewById(R.id.search_menu);
    }

    private void setListener(){
        MyListener myListener = new MyListener();
        Llay_MainPage.setOnClickListener(myListener);
        Llay_MenuPage.setOnClickListener(myListener);
        Llay_FriendPage.setOnClickListener(myListener);
        Llay_PersonPage.setOnClickListener(myListener);
        addButton.setOnClickListener(addlistener);
        searchButton.setOnClickListener(searchlistener);
    }

    /*发布按钮监听事件*/
    private View.OnClickListener addlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(ClientActivity.this, BlogSubmitActivity.class);
            startActivity(i);
        }
    };
    /*搜索按钮监听事件*/
    private View.OnClickListener searchlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(searchEdit.getText().toString().length()==0){
                Toast.makeText(getApplicationContext(),"请输入搜索内容",Toast.LENGTH_SHORT).show();
                return;
            }
            clearPreTab();
            setMenuPage();
        }
    };
    //页面跳转相关：
    private void setMainPage(){
        Llay_ClientHead.setVisibility(View.VISIBLE);
        if( mainPage==null ){ mainPage = new MainPage(); }
        ColorSquareListener listener = new ColorSquareListener();
        Tv_MainPage.setTextColor(getResources().getColor(R.color.tab_select));
        Iv_MainPage.setBackgroundResource(R.drawable.iv_main_sel);
        pre_select = 0;
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.Flaylayout_clientPage,mainPage);
        transaction.commit();
        mainPage.getColorSquareListener(listener);
        Llay_TabHost.invalidate();
    }
    private void setMenuPage(){
        Llay_ClientHead.setVisibility(View.VISIBLE);
        menuPage = new MenuPage();
        Tv_MenuPage.setTextColor(getResources().getColor(R.color.tab_select));
        Iv_MenuPage.setBackgroundResource(R.drawable.iv_menu_sel);
        pre_select = 1;
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.Flaylayout_clientPage, menuPage);
        transaction.commit();
        Llay_TabHost.invalidate();
    }
    private void setFriendPage(){
        Llay_ClientHead.setVisibility(View.GONE);
        if( friendPage==null ){ friendPage = new FriendPage(); }
        Tv_FriendPage.setTextColor(getResources().getColor(R.color.tab_select));
        Iv_FriendPage.setBackgroundResource(R.drawable.iv_friend_sel);
        pre_select = 2;
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.Flaylayout_clientPage, friendPage);
        transaction.commit();
        Llay_TabHost.invalidate();
    }
    private void setPersonPage(){
        Llay_ClientHead.setVisibility(View.GONE);
        if( personPage==null ){ personPage = new PersonPage(); }
        Tv_PersonPage.setTextColor(getResources().getColor(R.color.tab_select));
        Iv_PersonPage.setBackgroundResource(R.drawable.iv_person_sel);
        pre_select = 3;
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.Flaylayout_clientPage, personPage);
        transaction.commit();
        Llay_TabHost.invalidate();
    }

    //清除上一个选择的tab项：
    private void clearPreTab(){
        switch (pre_select){
            case 0:
                Tv_MainPage.setTextColor(getResources().getColor(R.color.tab_default));
                Iv_MainPage.setBackgroundResource(R.drawable.iv_main);
                break;
            case 1:
                Tv_MenuPage.setTextColor(getResources().getColor(R.color.tab_default));
                Iv_MenuPage.setBackgroundResource(R.drawable.iv_menu);
                break;
            case 2:
                Tv_FriendPage.setTextColor(getResources().getColor(R.color.tab_default));
                Iv_FriendPage.setBackgroundResource(R.drawable.iv_friend);
                break;
            case 3:
                Tv_PersonPage.setTextColor(getResources().getColor(R.color.tab_default));
                Iv_PersonPage.setBackgroundResource(R.drawable.iv_person);
                break;
        }
    }

    /*辅助类*/
    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            clearPreTab();
            switch (v.getId()){
                case R.id.Llaylayout_clientMainPage:
                    setMainPage();
                    break;
                case R.id.Llaylayout_clientMenuPage:
                    setMenuPage();
                    break;
                case R.id.Llaylayout_clientFriendPage:
                    setFriendPage();
                    break;
                case R.id.Llaylayout_clientPersonPage:
                    setPersonPage();
                    break;
            }
        }
    }

    private class ColorSquareListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent i = new Intent();
            switch (v.getId()){
                case R.id.Llayactivity_main_page_SeasonFood:
                    i.setClass(ClientActivity.this, ThreeMeals.class);
                    i.putExtra("推荐","冬    季");
                    startActivity(i);
                    break;
                case R.id.Llayactivity_main_page_Spicy:
                    mainPage.scrollToSpicy();
                    break;
                case R.id.Llayactivity_main_page_Follow:
                    clearPreTab();
                    setMenuPage();
                    break;
                case R.id.Llayactivity_main_pageBreakFast:
                    i.setClass(ClientActivity.this, ThreeMeals.class);
                    i.putExtra("推荐","早    餐");
                    startActivity(i);
                    break;
                case R.id.Llayactivity_main_pageAfternoon:
                    i.setClass(ClientActivity.this, ThreeMeals.class);
                    i.putExtra("推荐","午    餐");
                    startActivity(i);
                    break;
                case R.id.Llayactivity_main_pageDinner:
                    i.setClass(ClientActivity.this, ThreeMeals.class);
                    i.putExtra("推荐","晚    餐");
                    startActivity(i);
                    break;
            }
        }
    }
}
