package ruanjian.xin.xiaocaidao;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import ruanjian.xin.xiaocaidao.ui.FriendPage;
import ruanjian.xin.xiaocaidao.ui.MainPage;
import ruanjian.xin.xiaocaidao.ui.MenuPage;
import ruanjian.xin.xiaocaidao.ui.PersonPage;
import ruanjian.xin.xiaocaidao.utils.Utils;

public class Client extends AppCompatActivity {
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

    private static int pre_select = 0;

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
    }

    private void setListener(){
        MyListener listener = new MyListener();
        Llay_MainPage.setOnClickListener(listener);
        Llay_MenuPage.setOnClickListener(listener);
        Llay_FriendPage.setOnClickListener(listener);
        Llay_PersonPage.setOnClickListener(listener);
    }

    private void setMainPage(){
        Llay_ClientHead.setVisibility(View.VISIBLE);
        if( mainPage==null ){ mainPage = new MainPage(); }
        Tv_MainPage.setTextColor(getResources().getColor(R.color.tab_select));
        Iv_MainPage.setImageResource(R.drawable.iv_main_sel);
        pre_select = 0;
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.Flaylayout_clientPage,mainPage);
        transaction.commit();
        Llay_TabHost.invalidate();
    }

    private void setMenuPage(){
        Llay_ClientHead.setVisibility(View.VISIBLE);
        if( menuPage==null ){ menuPage = new MenuPage(); }
        Tv_MenuPage.setTextColor(getResources().getColor(R.color.tab_select));
        Iv_MenuPage.setImageResource(R.drawable.iv_menu_sel);
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
        Iv_FriendPage.setImageResource(R.drawable.iv_friend_sel);
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
        Iv_PersonPage.setImageResource(R.drawable.iv_person_sel);
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
                Iv_MainPage.setImageResource(R.drawable.iv_main);
                break;
            case 1:
                Tv_MenuPage.setTextColor(getResources().getColor(R.color.tab_default));
                Iv_MenuPage.setImageResource(R.drawable.iv_menu);
                break;
            case 2:
                Tv_FriendPage.setTextColor(getResources().getColor(R.color.tab_default));
                Iv_FriendPage.setImageResource(R.drawable.iv_friend);
                break;
            case 3:
                Tv_PersonPage.setTextColor(getResources().getColor(R.color.tab_default));
                Iv_PersonPage.setImageResource(R.drawable.iv_person);
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
}
