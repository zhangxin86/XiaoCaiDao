package ruanjian.xin.xiaocaidao;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import ruanjian.xin.xiaocaidao.ui.Friend.HotFriendDetail_Fragment;

public class Client_hot_detail extends AppCompatActivity {    //帖子评论框架，上面边框和下面输入框

    private FrameLayout HotFlay_Page;
    private RelativeLayout HotRlay_Head;
    private LinearLayout HotLlay_Bottom;
    private HotFriendDetail_Fragment hotFriendDetailFragment;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_client_hot_item_detail);

        findView();
        setContent();
        setListener();

    }

    private void setListener() {
        MyListener listener = new MyListener();
        imageView.setOnClickListener(listener);
    }

    private void findView() {
        HotRlay_Head = (RelativeLayout) findViewById(R.id.Rlaylayout_hotHead);
        HotLlay_Bottom = (LinearLayout)findViewById(R.id.Llaylayout_hotBottom);
        imageView = (ImageView)findViewById(R.id.back);
    }

    private void setContent(){
        HotRlay_Head.setVisibility(View.VISIBLE);
        HotLlay_Bottom.setVisibility(View.VISIBLE);
        if( hotFriendDetailFragment ==null ){ hotFriendDetailFragment = new HotFriendDetail_Fragment(); }
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.Hotlayout_clientPage, hotFriendDetailFragment);
        transaction.commit();
    }

    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            finish();
        }
    }
}
