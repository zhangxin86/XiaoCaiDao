package ruanjian.xin.xiaocaidao.ui;

import android.content.Intent;
import android.net.nsd.NsdManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import ruanjian.xin.xiaocaidao.R;

/**
 * Created by Administrator on 2016/11/24.
 */
public class MenuDetail extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu_detail);

        //返回经典菜谱菜单
        ImageButton fanhui=(ImageButton)findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MenuDetail.this,MenuPage.class);
                MenuDetail.this.setResult(RESULT_OK,i);
                MenuDetail.this.finish();
            }
        });
    }

    public void getWebImg(){//加载内容页图片


         }
    public void getJianjie(){//加载简介

    }
    public void getMain(){//加载主料

    }
    public void getSecondary(){//加载辅料

    }
}
