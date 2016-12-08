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
 * 子选项：我的收藏
 * Created by 你的账户 on 2016/11/23.
 */

public class Person_collect extends AppCompatActivity {
    private Button btn_Menu;//菜谱
    private Button btn_Post;//帖子
    private Fragment fragment_Menu;//菜谱覆盖页
    private Fragment fragment_Post;//帖子覆盖页
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_collect);
        initview();
    }
    private void initview(){
        btn_Menu = (Button)findViewById(R.id.btn1);
        btn_Post = (Button)findViewById(R.id.btn2);
        linearLayout = (LinearLayout)findViewById(R.id.ll);
        setListener();
        setDefaultFragment();
    }
    //默认Fragment页
    private void setDefaultFragment(){
        FragmentManager fm= getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        fragment_Menu = new Fragment_collect_menu();
        ft.replace(R.id.f1,fragment_Menu);
        ft.commit();
    }

    private void setListener(){
        btn_Menu.setOnClickListener(listener);
        btn_Post.setOnClickListener(listener);
    }
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            if(v ==btn_Menu){
                btn_Menu.setTextColor(getResources().getColor(R.color.tab_select));
                btn_Post.setTextColor(getResources().getColor(R.color.tab_default));
            }else {
                btn_Menu.setTextColor(getResources().getColor(R.color.tab_default));
               btn_Post.setTextColor(getResources().getColor(R.color.tab_select));
            }
            switch (v.getId()){
                case R.id.btn1:
                    //点击菜谱出现菜谱页面
                    if (fragment_Menu==null){
                        fragment_Menu=new Fragment_collect_menu();
                    }
                    ft.replace(R.id.f1,fragment_Menu);
                    break;
                case R.id.btn2:
                    //点击帖子出现帖子页面
                    if (fragment_Post==null){
                        fragment_Post=new Fragment_collect_post();
                    }
                    ft.replace(R.id.f1,fragment_Post);
                    break;
                default:
            }
            ft.commit();
            linearLayout.invalidate();
        }
    };
}
