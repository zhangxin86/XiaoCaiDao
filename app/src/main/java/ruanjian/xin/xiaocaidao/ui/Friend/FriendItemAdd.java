package ruanjian.xin.xiaocaidao.ui.Friend;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import ruanjian.xin.xiaocaidao.R;

public class FriendItemAdd extends Activity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_item_add);

        imageView = (ImageView)findViewById(R.id.back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}
