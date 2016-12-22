package ruanjian.xin.xiaocaidao.ui.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import ruanjian.xin.xiaocaidao.ClientActivity;
import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.utils.HttpUtil;
import ruanjian.xin.xiaocaidao.utils.Utils;

public class LoginActivity extends AppCompatActivity {
    private EditText account;
    private EditText etpwd;
    private Button btnlogin;
    private CheckBox re_pwd;
    private RelativeLayout bottom_mes;
    //用户数据
    private SharedPreferences userMes;
    private Button btnRegist;
    private ProgressDialog pd;

    String useraccount;
    String pwd;

    private HttpUtil httpUtil = new HttpUtil();
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            pd.dismiss();// 关闭ProgressDialog
            if(msg.what == 1){
                userMes = getSharedPreferences("user",MODE_PRIVATE);
                SharedPreferences.Editor editor = userMes.edit();
                editor.putString("useraccount",useraccount);
                if(re_pwd.isChecked()){ editor.putString("pwd",pwd); }
                editor.commit();
                Intent i = new Intent(LoginActivity.this,ClientActivity.class);
                startActivity(i);
            }else{
                Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findView();
    }
    @Override
    protected void onStop() {
        this.finish();
        super.onStop();
    }

    @Override
    protected void onResume() {
        setAnimation();  //设置动画

        //填入默认账号密码：
        userMes = getSharedPreferences("user",MODE_PRIVATE);
        account.setText(userMes.getString("useraccount",null));
        etpwd.setText(userMes.getString("pwd",null));
        setListener(); //绑定注册监听器
        super.onResume();
    }

    View.OnClickListener mylistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            useraccount=account.getText().toString().trim();
            pwd=etpwd.getText().toString().trim();
            switch (v.getId())   {
                case R.id.btnLogin:
                    pd= ProgressDialog.show(LoginActivity.this,"提示","正在登录中,请稍后");
                    new Thread(){
                        @Override
                        public void run() {
                            httpUtil.setValue(httpUtil.LOGINORSIGN,useraccount,pwd);
                            httpUtil.HttpRequest_post(Utils.check_ipUrl);
                            String end = httpUtil.HttpRequest_post(Utils.loginUrl);
                            Message message = new Message();
                            HttpUtil.uac = useraccount;
                            if(end.equals("1")){
                                message.what = 1;
                                httpUtil.setValue(httpUtil.FINDORCHECK_AC,HttpUtil.uac);
                                Log.i("name",HttpUtil.uac);
                                String name = httpUtil.HttpRequest_post(Utils.find_naUrl);
                                HttpUtil.una = name;
                            }else{
                                message.what = 0;
                            }
                            handler.sendMessage(message);
                            super.run();
                        }
                    }.start();
                    break;
                case R.id.btnRegist:
                    Intent i=new Intent(LoginActivity.this,SignActivity.class);
                    startActivity(i);
                    break;
            }
        }
    };

    private void setAnimation() {
        Animation fly_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fly_in);
        Animation fly_left = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fly_left);
        Animation flay_right = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fly_right);
        account.setAnimation(fly_left);
        etpwd.setAnimation(flay_right);
        bottom_mes.setAnimation(fly_in);
        btnlogin.setAnimation(fly_in);
    }

    private void setListener() {
        btnlogin.setOnClickListener(mylistener);
        btnRegist.setOnClickListener(mylistener);
    }

    private void findView() {
        account=(EditText)findViewById(R.id.etaccount);
        etpwd=(EditText)findViewById(R.id.etpwd);
        re_pwd = (CheckBox)findViewById(R.id.re_pwd);
        btnlogin=(Button)findViewById(R.id.btnLogin);
        btnRegist = (Button)findViewById(R.id.btnRegist);
        bottom_mes = (RelativeLayout)findViewById(R.id.bottom_mes);
    }

}
