package ruanjian.xin.xiaocaidao.ui.Login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import ruanjian.xin.xiaocaidao.R;

public class Sign_in extends Activity {
    private EditText etusername;
    private EditText etpwd;
    private EditText etpwd2;
    private Button btnsign;
    private Animation scale;
    private LinearLayout all;
    private UserDataManager mUserDataManager;//用户数据管理类
    private ProgressDialog pd1;


    private TextView tv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //获取控件
        all = (LinearLayout) findViewById(R.id.all);
        btnsign = (Button) findViewById(R.id.btnsign);
        etusername = (EditText) findViewById(R.id.etusername);
        etpwd = (EditText) findViewById(R.id.etpwd);
        etpwd2 = (EditText) findViewById(R.id.etpwd2);
        tv=(TextView)findViewById(R.id.tv);

        scale = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scaleanim);
        all.setAnimation(scale);
        all.startAnimation(scale);
        //建立本地数据库
        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();
        }

        //绑定注册监听器
        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pd1 = ProgressDialog.show(Sign_in.this, "提示", "正在注册中,请稍后");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                            switch (register_check()){
                                case 1:
                                    Message msg=handler.obtainMessage();
                                    msg.what=1;
                                    handler.sendMessage(msg);
                                    break;
                                case 2:
                                    handler.sendEmptyMessage(2);
                                    break;
                                case 3:
                                    handler.sendEmptyMessage(3);
                                    break;
                                case 4:
                                    handler.sendEmptyMessage(4);
                                    break;
                            }
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }

                    }
                }).start();
                //..

            }
        });

    }

    //确认按钮的监听事件
    public int  register_check() {
            if (isUserNameAndPwdValid()) {
                String username = etusername.getText().toString().trim();
                String pwd1 = etpwd.getText().toString().trim();
                String pwd2 = etpwd2.getText().toString().trim();
                //检查用户是否存在
                int count = mUserDataManager.findUserByName(username);
                //用户已经存在时返回，给出提示文字
                if (count > 0) {
                    return 1;
                }
                //两次密码输入不一样
                if (pwd1.equals(pwd2) == false) {
                    return 2;
                }
                else {
                    UserData mUser = new UserData(username, pwd1);
                    mUserDataManager.openDataBase();
                    long flag = mUserDataManager.insertUserData(mUser);//新建用户信息
                    if (flag == -1) {
                        return 3;
                    }
                    else {
                        Intent i = new Intent(Sign_in.this, success.class);
                        startActivity(i);
                        finish();
                    }
                }
            }
        return 0;
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
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {// handler接收到消息后就会执行此方法
            switch (msg.what){
                case 1:
                    tv.setText("用户名已存在");
                    break;
                case 2:
                    tv.setText("密码不正确，请重新输入");
                    break;
                case 3:
                    tv.setText("注册失败");
                    break;
            }
            pd1.dismiss();// 关闭ProgressDialog
        }
    };
}
