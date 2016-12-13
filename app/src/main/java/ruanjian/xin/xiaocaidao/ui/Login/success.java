package ruanjian.xin.xiaocaidao.ui.Login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;

import ruanjian.xin.xiaocaidao.R;

public class success extends Activity {
    private ProgressDialog pd;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success);


        Timer timer=new Timer();
        pd=ProgressDialog.show(success.this,"提示","正在为您自动跳转至登录页");
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                jump();
                handler.sendEmptyMessage(0);
                finish();
            }
        };
        timer.schedule(task,1000*3);
    }

    public void  jump(){
        final Intent i=new Intent(this,First.class);
        startActivity(i);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            pd.dismiss();
            // handler接收到消息后就会执行此方法
        }
    };
}
