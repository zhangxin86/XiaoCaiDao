package ruanjian.xin.xiaocaidao.ui.personal;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import ruanjian.xin.xiaocaidao.R;

/**
 * 选项：个人中心
 * 子选项：我的关注
 * Created by 你的账户 on 2016/11/24.
 */

public class Person_fouse extends AppCompatActivity {
    private Button btn_Fans;//粉丝按钮
    private Button btn_Focus;//关注按钮
    private Fragment_focus_fans fragment_Fans;//粉丝覆盖页
    private Fragment_focus_focus fragment_Focus;//关注覆盖页
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_focus);
        initview();
    }
    private void initview(){
        btn_Fans =(Button)findViewById(R.id.btn1);
        btn_Focus =(Button)findViewById(R.id.btn2);
        linearLayout = (LinearLayout)findViewById(R.id.ll);
        setListener();
        setDefaultFragment();
    }
    private void setDefaultFragment(){
        FragmentManager fragmentManager =getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment_Fans = new Fragment_focus_fans();
        fragmentTransaction.replace(R.id.f1,fragment_Fans);
        fragmentTransaction.commit();
    }
    private void setListener(){
        btn_Fans.setOnClickListener(listener);
        btn_Focus.setOnClickListener(listener);
    }
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if(v ==btn_Fans){
                btn_Fans.setTextColor(getResources().getColor(R.color.tab_select));
                btn_Focus.setTextColor(getResources().getColor(R.color.tab_default));
            }else {
                btn_Fans.setTextColor(getResources().getColor(R.color.tab_default));
                btn_Focus.setTextColor(getResources().getColor(R.color.tab_select));
            }
            switch (v.getId()){
                case R.id.btn1:
                    if(fragment_Fans==null){
                        //点击粉丝按钮出现粉丝页面
                        fragment_Fans = new Fragment_focus_fans();
                    }
                    fragmentTransaction.replace(R.id.f1,fragment_Fans);
                    break;
                case  R.id.btn2:
                    //点击关注按钮出现关注按钮
                    if (fragment_Focus==null){
                        fragment_Focus=new Fragment_focus_focus();
                    }
                    fragmentTransaction.replace(R.id.f1,fragment_Focus);
                    default:
                        break;
            }
            fragmentTransaction.commit();
            linearLayout.invalidate();
        }
    };
}

