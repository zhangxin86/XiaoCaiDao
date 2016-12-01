package ruanjian.xin.xiaocaidao.ui.Friend;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;

import ruanjian.xin.xiaocaidao.R;

public class FriendItemAdd extends Activity {

    private Button btn;
    private ImageView add_imageView;
    private ImageView back_imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_item_add);

        findView();
        setListener();
    }

    private void setListener() {
        back_imageView.setOnClickListener(mylistener);
        btn.setOnClickListener(mylistener);
    }

    private void findView() {
        btn = (Button)findViewById(R.id.select_photo);
        add_imageView = (ImageView)findViewById(R.id.add_photo);
        back_imageView = (ImageView)findViewById(R.id.back);
    }
    View.OnClickListener mylistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.back:
                    finish();
                    break;
                case R.id.select_photo:
                    Intent intent = new Intent();
                    /*开启pictureactivity画面Type设定为image*/
                    intent.setType("image/*");
                    /**/
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    /*返回本activity*/
                    startActivityForResult(intent,1);
                    break;
            }
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK ){
            Uri uri = data.getData();
            Log.e("uri",uri.toString());
            ContentResolver cr = this.getContentResolver();
            try{
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));//字节流
                add_imageView.setVisibility(View.VISIBLE);
                add_imageView.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                Log.e("Exception",e.getMessage(),e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
