package ruanjian.xin.xiaocaidao.ui.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import ruanjian.xin.xiaocaidao.R;

public class First extends AppCompatActivity {
    private Button btnsign;
    private Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        //得到按钮控件
        btnlogin = (Button)findViewById(R.id.btnlogin);
        btnsign = (Button)findViewById(R.id.btnsign);
        //绑定登录事件监听器
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //...

                Intent i =new Intent(First.this,Login.class);
                startActivity(i);
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
}
