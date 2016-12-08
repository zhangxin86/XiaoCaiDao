package ruanjian.xin.xiaocaidao.ui.personal;

import android.app.Fragment;
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
 * 子选项：我的帖子
 * Created by 你的账户 on 2016/11/23.
 */

public class Person_post extends AppCompatActivity {
    private Button btn_Post;//主贴按钮
    private Button btn_Repost;//回帖按钮
    private Fragment fragment_Post;//主贴覆盖页
    private Fragment fragmentRepost;//回帖覆盖页
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_post);
        initview();
    }
    private void initview(){
        btn_Post= (Button)findViewById(R.id.btn1);
        btn_Repost= (Button)findViewById(R.id.btn2);
        linearLayout = (LinearLayout)findViewById(R.id.ll);
        setListener();
        setDefaultFragment();
    }
    private void setDefaultFragment(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        fragment_Post = new Fragment_post_mainpost();
        ft.replace(R.id.f1,fragment_Post);
        ft.commit();
    }
    private void setListener(){
        btn_Post.setOnClickListener(listener);
        btn_Repost.setOnClickListener(listener);
    }
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft =fm.beginTransaction();
            if(v ==btn_Post){
                btn_Post.setTextColor(getResources().getColor(R.color.tab_select));
                btn_Repost.setTextColor(getResources().getColor(R.color.tab_default));
            }else {
                btn_Post.setTextColor(getResources().getColor(R.color.tab_default));
                btn_Repost.setTextColor(getResources().getColor(R.color.tab_select));
            }
            switch (v.getId()){
                //点击主贴按钮出现主页页面
                case R.id.btn1:
                    if(fragment_Post ==null){
                        fragment_Post =new Fragment_post_mainpost();
                    }
                    ft.replace(R.id.f1,fragment_Post);
                    break;
                //点击回帖按钮出现回帖页面
                case R.id.btn2:
                    if(fragmentRepost ==null){
                        fragmentRepost =new Fragment_post_repost();
                    }
                    ft.replace(R.id.f1,fragmentRepost);
                    break;
                default:
            }
            ft.commit();
            linearLayout.invalidate();
        }
    };
}
