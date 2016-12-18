package ruanjian.xin.xiaocaidao.ui.Login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.utils.HttpUtil;
import ruanjian.xin.xiaocaidao.utils.Utils;

/**
 * Created by 贾紫璇 on 2016/11/16.
 */
public class SignActivity extends Activity {
    private ImageView back;
    private EditText account;
    private EditText etpwd;
    private EditText etpwd2;
    private Button btnsign;
    private Animation scale;
    private HttpUtil httpUtil = new HttpUtil();
    private ProgressDialog pd1;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            pd1.dismiss();// 关闭ProgressDialog
            if(msg.what==0){
                Toast.makeText(SignActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SignActivity.this,LoginActivity.class);
                startActivity(i);
            }else if(msg.what==1){
                Toast.makeText(SignActivity.this,"用户名已存在",Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    };

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //获取控件
        back = (ImageView)findViewById(R.id.sign_in_back);
        btnsign=(Button)findViewById(R.id.btnsign);
        account=(EditText) findViewById(R.id.etaccount);
        etpwd=(EditText)findViewById(R.id.etpwd);
        etpwd2=(EditText)findViewById(R.id.etpwd2);



        //绑定注册监听器
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //..
                register_check();
            }
        });
    }
    //确认按钮的监听事件
    public void register_check(){
        if(isUserNameAndPwdValid()){
            final String useraccount=account.getText().toString().trim();
            final String pwd=etpwd.getText().toString().trim();
            final String repwd=etpwd2.getText().toString().trim();
            if(isUserNameAndPwdValid()){
                if (pwd.equals(repwd)==false) {
                    Toast.makeText(this, "两次密码输入不相同", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    pd1 = ProgressDialog.show(SignActivity.this, "提示", "正在注册中,请稍后");
                    new Thread(){
                        @Override
                        public void run() {
                            httpUtil.setValue(httpUtil.FINDORCHECK_AC,useraccount);
                            String end = httpUtil.HttpRequest_post(Utils.check_acUrl);
                            Message message = new Message();
                            if(end.equals("1")){
                                message.what = 1;
                            }else{
                                message.what = 0;
                                httpUtil.setValue(httpUtil.LOGINORSIGN,useraccount,pwd);
                                httpUtil.HttpRequest_post(Utils.sign_Url);
                                finish();
                            }
                            handler.sendMessage(message);
                            super.run();
                        }
                    }.start();
                }
            }
        }
    }
    public boolean isUserNameAndPwdValid(){
        if (account.getText().toString().trim().equals("")){
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
