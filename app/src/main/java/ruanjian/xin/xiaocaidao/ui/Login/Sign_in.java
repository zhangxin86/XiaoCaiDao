package ruanjian.xin.xiaocaidao.ui.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ruanjian.xin.xiaocaidao.R;

/**
 * Created by 贾紫璇 on 2016/11/16.
 */
public class Sign_in extends Activity {
    private EditText etusername;
    private EditText etpwd;
    private EditText etpwd2;
    private Button btnsign;
    private UserDataManager mUserDataManager;         //用户数据管理类

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //获取控件
        btnsign=(Button)findViewById(R.id.btnsign);
        etusername=(EditText) findViewById(R.id.etusername);
        etpwd=(EditText)findViewById(R.id.etpwd);
        etpwd2=(EditText)findViewById(R.id.etpwd2);
        //建立本地数据库
        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();
        }

        //绑定注册监听器
        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //..
                register_check();


                Intent i = new Intent(Sign_in.this,success.class);
            }
        });

    }
    //确认按钮的监听事件
    public void register_check(){
        if(isUserNameAndPwdValid()){
            String username=etusername.getText().toString().trim();
            String pwd1=etpwd.getText().toString().trim();
            String pwd2=etpwd2.getText().toString().trim();
            //检查用户是否存在
            int count=mUserDataManager.findUserByName(username);
            //用户已经存在时返回，给出提示文字
            if (count>0){
                Toast.makeText(this,"用户名已存在！", Toast.LENGTH_SHORT).show();
                return;
            }
            //两次密码输入不一样
            if (pwd1.equals(pwd2)==false){
                Toast.makeText(this,"密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                return;
            }else {
                UserData mUser= new UserData(username,pwd1);
                mUserDataManager.openDataBase();
                long flag = mUserDataManager.insertUserData(mUser);//新建用户信息
                if(flag == -1){
                    Toast.makeText(this,"注册失败", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(this,"注册成功", Toast.LENGTH_SHORT).show();

                    Intent i =new Intent(Sign_in.this,Login.class);
                    startActivity(i);
                    finish();
                }
            }

        }

    }
    public boolean isUserNameAndPwdValid(){
        if (etusername.getText().toString().trim().equals("")){
            Toast.makeText(this,"用户名为空，请重新输入", Toast.LENGTH_SHORT).show();
            return false;
        }else if(etpwd.getText().toString().trim().equals("")){
            Toast.makeText(this,"密码为空，请重新输入", Toast.LENGTH_SHORT).show();
            return false;
        }else if (etpwd2.getText().toString().trim().equals("")){
            Toast.makeText(this,"密码确认为空，请重新输入", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
