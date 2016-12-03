package ruanjian.xin.xiaocaidao.ui.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import ruanjian.xin.xiaocaidao.R;

public class First extends AppCompatActivity {
    private Button btnsign;
    private Button btnlogin;

    private Animation Fly_In_btnsign;
    private Animation Fly_In_btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        //得到按钮控件
        btnlogin = (Button)findViewById(R.id.btnlogin);
        btnsign = (Button)findViewById(R.id.btnsign);

        Fly_In_btn_login = new TranslateAnimation(0,0,200,0);
        Fly_In_btn_login.setFillAfter(false);
        Fly_In_btn_login.setDuration(800);

        Fly_In_btnsign = new TranslateAnimation(0,0,200,0);
        Fly_In_btnsign.setFillAfter(false);
        Fly_In_btnsign.setDuration(1000);

        btnsign.setAnimation(Fly_In_btnsign);
        btnlogin.startAnimation(Fly_In_btn_login);
        //绑定登录事件监听器
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //...

                Intent i =new Intent(First.this,Login.class);
                startActivity(i);
                First.this.overridePendingTransition(0,0);
            }
        });

        //绑定注册事件监听器
        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //...
                Intent i =new Intent(First.this,Sign_in.class);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onPause() {
        First.this.overridePendingTransition(0,0);
        super.onPause();
    }

    @Override
    protected void onResume() {
        First.this.overridePendingTransition(0,0);
        super.onResume();
    }
}
