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
 * Created by 你的账户 on 2016/11/23.
 */

public class Main3 extends AppCompatActivity {
    private Button button1;
    private Button button2;
    private Fragment fragment1;
    private Fragment fragment2;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_three);
        initview();
    }
    private void initview(){
        button1= (Button)findViewById(R.id.btn1);
        button2= (Button)findViewById(R.id.btn2);
        linearLayout = (LinearLayout)findViewById(R.id.ll);
        setListener();
        setDefaultFragment();
    }
    private void setDefaultFragment(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        fragment1 = new Fragment_three1();
        ft.replace(R.id.f1,fragment1);
        ft.commit();
    }
    private void setListener(){
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
    }
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft =fm.beginTransaction();
            switch (v.getId()){
                case R.id.btn1:
                    if(fragment1 ==null){
                        fragment1 =new Fragment_three1();
                    }
                    ft.replace(R.id.f1,fragment1);
                    break;
                case R.id.btn2:
                    if(fragment2 ==null){
                        fragment2 =new Fragment_three2();
                    }
                    ft.replace(R.id.f1,fragment2);
                    break;
                default:
            }
            ft.commit();
            linearLayout.invalidate();
        }
    };
}
