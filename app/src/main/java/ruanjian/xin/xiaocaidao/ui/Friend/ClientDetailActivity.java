package ruanjian.xin.xiaocaidao.ui.Friend;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.utils.HttpUtil;
import ruanjian.xin.xiaocaidao.utils.Utils;

public class ClientDetailActivity extends AppCompatActivity {

    private static String tempId;
    private FrameLayout HotFlay_Page;
    private RelativeLayout HotRlay_Head;
    private LinearLayout HotLlay_Bottom;
    private HotFriendDetailFragment hotFriendDetail;
    private ImageView imageView;
    private Button Btn_Comment;
    private EditText Et_Comment;//评论框
    private HttpUtil httpUtil = new HttpUtil();

    private long id = -1;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){
                Et_Comment.setText("");
                Toast.makeText(ClientDetailActivity.this,"评论成功",Toast.LENGTH_SHORT).show();
                hotFriendDetail.getCommentData();
                hotFriendDetail.setComment();
            }else{
                Toast.makeText(ClientDetailActivity.this,"评论失败",Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_client_hot_item_detail);

        tempId = getIntent().getStringExtra("blog_id");
        findView();
        setContent();
        setListener();

    }

    private void setListener() {
        MyListener listener = new MyListener();
        imageView.setOnClickListener(listener);
        Btn_Comment.setOnClickListener(listener);
    }

    private void findView() {
        HotRlay_Head = (RelativeLayout) findViewById(R.id.Rlaylayout_hotHead);
        HotLlay_Bottom = (LinearLayout)findViewById(R.id.Llaylayout_hotBottom);
        imageView = (ImageView)findViewById(R.id.back);
        Btn_Comment = (Button)findViewById(R.id.Btn_Comment);
        Et_Comment = (EditText)findViewById(R.id.Et_Comment);
    }

    private void setContent(){
        HotRlay_Head.setVisibility(View.VISIBLE);
        HotLlay_Bottom.setVisibility(View.VISIBLE);
        Bundle bundle = new Bundle();
        System.out.println("从Activity传之前的帖子详情Id"+tempId);
        bundle.putString("blog_id",tempId);
        if( hotFriendDetail ==null ){
            hotFriendDetail = new HotFriendDetailFragment();
            hotFriendDetail.setArguments(bundle);
        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.Hotlayout_clientPage,hotFriendDetail);
        transaction.commit();
    }


    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back:
                    finish();
                    break;
                case R.id.Btn_Comment:
                    if (Et_Comment.getText().toString().equals("")){
                        Toast.makeText(getApplication(),"您还未进行任何评论！",Toast.LENGTH_SHORT).show();
                    }else {
                        final String comment = Et_Comment.getText().toString();
                        new Thread(){
                            @Override
                            public void run() {
                                httpUtil.setValue(httpUtil.SET_CO,HttpUtil.uac, tempId+"",comment);
                                String end = httpUtil.HttpRequest_post(Utils.upload_coUrl);
                                if(end.equals("1")){
                                    handler.sendEmptyMessage(1);
                                }else{
                                    handler.sendEmptyMessage(0);
                                }
                                super.run();
                            }
                        }.start();
                        break;
                    }
            }
        }
    }
}
