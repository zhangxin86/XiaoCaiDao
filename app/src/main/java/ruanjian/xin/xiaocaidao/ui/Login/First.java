package ruanjian.xin.xiaocaidao.ui.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ruanjian.xin.xiaocaidao.Client;
import ruanjian.xin.xiaocaidao.R;

public class First extends AppCompatActivity {
    private EditText etusername;
    private EditText etpwd;
    private Button btnlogin;
    private UserDataManager mUserDataManager;//用户数据管理类
    private SharedPreferences login_sp;
    private Button btnRegist;

    //用户数据
    private UserData db;

    String username;
    String pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        findView();
        setAnimation();  //设置动画
        setListener(); //绑定注册监听器


    }

    View.OnClickListener mylistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId())   {
                case R.id.btnLogin:
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

                    Intent intent=new Intent(First.this,Client.class);
                    startActivity(intent);
                    finish();
//                }else if (result == 0){
//                    Toast.makeText(getApplicationContext(),"登陆失败！", Toast.LENGTH_SHORT).show();
//                }
                    break;
                case R.id.btnRegist:
                    Intent i=new Intent(First.this,Sign_in.class);
                    startActivity(i);

            }
        }
    };

    private void setAnimation() {

    }

    private void setListener() {
        btnlogin.setOnClickListener(mylistener);
        btnRegist.setOnClickListener(mylistener);
    }

    private void findView() {
        etusername=(EditText)findViewById(R.id.userId);
        etpwd=(EditText)findViewById(R.id.userPswd);
        btnlogin=(Button)findViewById(R.id.btnLogin);
        btnRegist = (Button)findViewById(R.id.btnRegist);
    }

//    @Override
//    protected void onPause() {
//        First.this.overridePendingTransition(0,0);
//        super.onPause();
//    }
//
//    @Override
//    protected void onResume() {
//        First.this.overridePendingTransition(0,0);
//        super.onResume();
//    }
}
