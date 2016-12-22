package ruanjian.xin.xiaocaidao.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.utils.SpicyUtil;

/**
 * Created by liyuxuan on 2016/12/17.
 */

public class SpiceActivity extends Activity {
    /*有关 include Title*/
    private TextView Tvtitle;       //Title 标题
    private Button backBtn;       //返回按钮
    /*其他控件*/
    private ImageView IvSpiceImg;           //图片控件
    private TextView TvSpiceName;          //名称控件
    private TextView TvSpiceDes;        //描述控件
    private TextView TvNutrition;           //营养控件
    /*内容相关*/
    private SpicyUtil spicyUtil = new SpicyUtil();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spice);

        getView();
        setMessage();
        setView();
    }


    public void setMessage(){
        Intent i = getIntent();
        int type = i.getIntExtra("type",0);
        IvSpiceImg.setBackgroundResource(spicyUtil.getImage(type));
        TvSpiceName.setText(spicyUtil.getName(type));
        TvSpiceDes.setText(spicyUtil.getDes(type));
        TvNutrition.setText(spicyUtil.getNatrition(type));
    }

    public void getView(){
        Tvtitle = (TextView)findViewById(R.id.text_title);
        backBtn = (Button)findViewById(R.id.button_backward);

        IvSpiceImg = (ImageView)findViewById(R.id.NetSpiceImg);
        TvSpiceName = (TextView)findViewById(R.id.Tv_spiceName);
        TvSpiceDes = (TextView)findViewById(R.id.Tv_spiceDes);
        TvNutrition = (TextView)findViewById(R.id.Tv_spiceNutrition);
    }
    public void setView(){
        Tvtitle.setText("食材酱料");
        backBtn.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button_backward:
                    finish();
                    break;
            }
        }
    };
}
