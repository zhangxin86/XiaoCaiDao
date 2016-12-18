package ruanjian.xin.xiaocaidao.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ruanjian.xin.xiaocaidao.R;

/**
 * Created by liyuxuan on 2016/12/17.
 */

public class SpiceActivity extends Activity {
    /*有关 include Title*/
    private RelativeLayout innerTitle;      //Title inner控件
    private TextView Tvtitle;       //Title 标题
    private Button backBtn;       //返回按钮
    /*其他控件*/
    private ImageView IvSpiceImg;           //图片控件
    private TextView TvSpiceName;          //名称控件
    private TextView TvSpiceDes;        //描述控件
    private TextView TvNutrition;           //营养控件





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spice);

        getView();
        setView();
    }

    public void getView(){
        innerTitle = (RelativeLayout)findViewById(R.id.SpiceTitle);
        Tvtitle = (TextView)innerTitle.findViewById(R.id.text_title);
        backBtn = (Button)innerTitle.findViewById(R.id.button_backward);

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
