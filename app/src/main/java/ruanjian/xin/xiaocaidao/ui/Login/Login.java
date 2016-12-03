package ruanjian.xin.xiaocaidao.ui.Login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import ruanjian.xin.xiaocaidao.Client;
import ruanjian.xin.xiaocaidao.R;

/**
 * Created by 贾紫璇 on 2016/11/16.
 */
public class Login extends Activity {
    private LinearLayout all;
    private EditText etusername;
    private EditText etpwd;
    private Button btnlogin;
    private UserDataManager mUserDataManager;//用户数据管理类
    private SharedPreferences login_sp;
    private Animation scale;

    //用户数据
    private UserData db;

    String username;
    String pwd;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //获取控件
        all = (LinearLayout)findViewById(R.id.all);
        etusername=(EditText)findViewById(R.id.etusername);
        etpwd=(EditText)findViewById(R.id.etpwd);
        btnlogin=(Button)findViewById(R.id.btnlogin);
        scale = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scaleanim);
        all.setAnimation(scale);
        all.startAnimation(scale);

        //建立本地数据库

        //给登录按钮绑定注册监听器

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //...
                //获取当前输入的用户名和密码
                username=etusername.getText().toString().trim();
                pwd=etpwd.getText().toString().trim();
                //SharedPreferences.Editor editor =login_sp.edit();
//                int result=mUserDataManager.findUserByNameAndPwd(username,pwd);
//                if (result == 1){//返回1说明用户名和密码均正确
                    //保存用户名和密码
//                    editor.putString("USER_NAME", username);
//                    editor.putString("PASSWORD", pwd);
//                    editor.commit();

                    Intent intent=new Intent(Login.this,Client.class);
                    startActivity(intent);
                    finish();
//                }else if (result == 0){
//                    Toast.makeText(getApplicationContext(),"登陆失败！", Toast.LENGTH_SHORT).show();
//                }

            }
        });

    }

}
