package ruanjian.xin.xiaocaidao.ui.Friend;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import ruanjian.xin.xiaocaidao.ClientActivity;
import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.ui.Login.LoginActivity;
import ruanjian.xin.xiaocaidao.ui.PersonPage;
import ruanjian.xin.xiaocaidao.ui.SelectWindow.SelectPicPopupWindow;
import ruanjian.xin.xiaocaidao.utils.HttpUtil;
import ruanjian.xin.xiaocaidao.utils.Utils;

import static ruanjian.xin.xiaocaidao.utils.Utils.JIFINAL_UPLOAD;

public class BlogSubmitActivity extends Activity implements View.OnClickListener {
    private Context mContext;
    private Button select_btn;//选择图片
    private TextView Release;//发布
    private ImageView add_imageView;//图片显示控件
    private ImageView back_imageView;
    private EditText et_blogname;
    private EditText et_blogcontent;

    private String BlogName;
    private String BlogContent;
    private HttpUtil httpUtil = new HttpUtil();

    private Uri photoUri;
    private SelectPicPopupWindow menuWindow; // 自定义的头像编辑弹出框
    /** 使用照相机拍照获取图片 */
    public static final int SELECT_PIC_BY_TACK_PHOTO = 1;
    /** 使用相册中的图片 */
    public static final int SELECT_PIC_BY_PICK_PHOTO = 2;
    /** 获取到的图片路径 */
    private String picPath = "";
    private static ProgressDialog pd;

    /** 标签相关 */
    private Button Tag01,Tag02,Tag03,Tag04,Tag05,Tag06,Tag07;
    private String[] lable = {"0","0","0","0","0","0","0"};
    private int selectlablenum = 0;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 1){
                Toast.makeText(BlogSubmitActivity.this,"发布成功",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(BlogSubmitActivity.this,"发布失败",Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_item_add);
        mContext = BlogSubmitActivity.this;

        findView();
        setListener();
    }

    private void setListener() {
        back_imageView.setOnClickListener(this);
        select_btn.setOnClickListener(this);
        Release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BlogName = et_blogname.getText().toString().trim();
                BlogContent = et_blogcontent.getText().toString().trim();
                if(selectlablenum!=2){
                    Toast.makeText(getApplicationContext(),"请为您的帖子选择两个标签！",Toast.LENGTH_SHORT).show();
                } else if (picPath.equals("")){
                    Toast.makeText(getApplicationContext(),"别忘记添加图片哦！",Toast.LENGTH_SHORT).show();
                }else {
                    upLoadAvatar(picPath);
                    pd = ProgressDialog.show(mContext, null, "正在上传图片，请稍候...");
                }
            }
        });
        //标签相关
        LabelListener labelListener = new LabelListener();
        Tag01.setOnClickListener(labelListener);
        Tag02.setOnClickListener(labelListener);
        Tag03.setOnClickListener(labelListener);
        Tag04.setOnClickListener(labelListener);
        Tag05.setOnClickListener(labelListener);
        Tag06.setOnClickListener(labelListener);
        Tag07.setOnClickListener(labelListener);
    }

    private void findView() {
        select_btn = (Button)findViewById(R.id.select_photo);//选择图片按钮
        Release = (TextView)findViewById(R.id.Release);//发布按钮
        //标签相关
        Tag01 = (Button)findViewById(R.id.Tag01);
        Tag02 = (Button)findViewById(R.id.Tag02);
        Tag03 = (Button)findViewById(R.id.Tag03);
        Tag04 = (Button)findViewById(R.id.Tag04);
        Tag05 = (Button)findViewById(R.id.Tag05);
        Tag06 = (Button)findViewById(R.id.Tag06);
        Tag07 = (Button)findViewById(R.id.Tag07);

        add_imageView = (ImageView)findViewById(R.id.add_photo);
        back_imageView = (ImageView)findViewById(R.id.back);

        et_blogname = (EditText)findViewById(R.id.et_blogname);
        et_blogcontent = (EditText)findViewById(R.id.et_blogcontent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.select_photo:// 添加图片点击事件
                // 从页面底部弹出一个窗体，选择拍照还是从相册选择已有图片
                menuWindow = new SelectPicPopupWindow(mContext, itemsOnClick);
                menuWindow.showAtLocation(findViewById(R.id.activity_friend_item_add),
                        Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
        }
    }

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // 隐藏弹出窗口
            menuWindow.dismiss();

            switch (v.getId()) {
                case R.id.takePhotoBtn:// 拍照
                    takePhoto();
                    break;
                case R.id.pickPhotoBtn:// 相册选择图片
                    pickPhoto();
                    break;
                case R.id.cancelBtn:// 取消
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 拍照获取图片
     */
    private void takePhoto() {
        // 执行拍照前，应该先判断SD卡是否存在
        String SDState = Environment.getExternalStorageState();
        if (SDState.equals(Environment.MEDIA_MOUNTED)) {

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            /***
             * 需要说明一下，以下操作使用照相机拍照，拍照后的图片会存放在相册中的
             * 这里使用的这种方式有一个好处就是获取的图片是拍照后的原图
             * 如果不使用ContentValues存放照片路径的话，拍照后获取的图片为缩略图不清晰
             */
            ContentValues values = new ContentValues();
            photoUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(intent, SELECT_PIC_BY_TACK_PHOTO);
        } else {
            Toast.makeText(this, "内存卡不存在", Toast.LENGTH_LONG).show();
        }
    }

    /***
     * 从相册中取图片
     */
    private void pickPhoto() {
        Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
        // 如果要限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
        pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(pickIntent, SELECT_PIC_BY_PICK_PHOTO);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 点击取消按钮
        if(resultCode == RESULT_CANCELED){
            return;
        }

        // 可以使用同一个方法，这里分开写为了防止以后扩展不同的需求
        switch (requestCode) {
            case SELECT_PIC_BY_PICK_PHOTO:// 如果是直接从相册获取
                doPhoto(requestCode, data);
                break;
            case SELECT_PIC_BY_TACK_PHOTO:// 如果是调用相机拍照时
                doPhoto(requestCode, data);
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * 选择图片后，获取图片的路径
     *
     * @param requestCode
     * @param data
     */
    private void doPhoto(int requestCode, Intent data) {

        System.out.print("requestCode::"+requestCode);
        // 从相册取图片，有些手机有异常情况，请注意
        if (requestCode == SELECT_PIC_BY_PICK_PHOTO) {
            if (data == null) {
                Toast.makeText(this, "选择图片文件出错", Toast.LENGTH_LONG).show();
                return;
            }
            photoUri = data.getData();
            if (photoUri == null) {
                Toast.makeText(this, "选择图片文件出错", Toast.LENGTH_LONG).show();
                return;
            }
        }

        String[] pojo = { MediaStore.MediaColumns.DATA };

        Cursor cursor = mContext.getContentResolver().query(photoUri, pojo, null, null, null);
        if (cursor != null) {
            int columnIndex = cursor.getColumnIndexOrThrow(pojo[0]);
            cursor.moveToFirst();
            picPath = cursor.getString(columnIndex);

            // 4.0以上的版本会自动关闭 (4.0--14;; 4.0.3--15)
            if (Integer.parseInt(Build.VERSION.SDK) < 14) {
                cursor.close();
            }
        }

        // 如果图片符合要求将其上传到服务器
        if (picPath != null && (	picPath.endsWith(".png") ||
                picPath.endsWith(".PNG") ||
                picPath.endsWith(".jpg") ||
                picPath.endsWith(".JPG"))) {

            System.out.println("图片路径！！"+picPath);
            BitmapFactory.Options option = new BitmapFactory.Options();
            // 压缩图片:表示缩略图大小为原始图片大小的几分之一，1为原图
            option.inSampleSize = 1;
            // 根据图片的SDCard路径读出Bitmap
            Bitmap bm = BitmapFactory.decodeFile(picPath, option);
            // 显示在图片控件上
            add_imageView.setImageBitmap(bm);
        } else {
            Toast.makeText(this, "选择图片文件不正确", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * 头像上传
     */
    public void upLoadAvatar(String filePath){
        File file = new File(filePath);
        if (file.exists() && file.length() > 0) {
            AsyncHttpClient as = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            try {
                params.put("uploadfile", file);
                params.put("name",BlogName);
                params.put("label",postLableMes());
                params.put("account",HttpUtil.uac);
                params.put("content",BlogContent);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            as.post(Utils.upload_blUrl, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, byte[] bytes) {
                    System.out.println("图片上传成功！！！");
                    try {
                        String res = new String(bytes,"UTF-8");
                        Toast.makeText(mContext,"发布成功！！！"+res,Toast.LENGTH_SHORT).show();
                        BlogSubmitActivity.this.finish();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    pd.dismiss();
                }

                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                    System.out.println("图片上传失败！！！");
                    Toast.makeText(mContext,"发布失败！！！",Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            });
        }else {
            System.out.println("文件不存在！！！");
            pd.dismiss();
        }
    }

    /**
     * 判断标签点击
     */
    private String postLableMes(){
        String end = "";
        for(int i=0;i<lable.length;i++){
            end = end + lable[i];
        }
        Log.i("label",end);
        return end;
    }

    /**
     * 辅助类
     */
    private class LabelListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(selectlablenum>=2&&(!v.isSelected())){
                Toast.makeText(BlogSubmitActivity.this,"请选择两个标签",Toast.LENGTH_SHORT).show();
                return;
            }
            switch (v.getId()){
                case R.id.Tag01:
                    if(lable[0].equals("1")) {
                        Tag01.setSelected(false);
                        lable[0] = "0";
                        selectlablenum--;
                        Log.i("button","gray");
                    }
                    else{
                        Tag01.setSelected(true);
                        lable[0] = "1";
                        selectlablenum++;
                        Log.i("button","yellow");
                    }
                    break;
                case R.id.Tag02:
                    if(lable[1].equals("1")) {
                        Tag02.setSelected(false);
                        lable[1] = "0";
                        selectlablenum--;
                    }
                    else{
                        Tag02.setSelected(true);
                        lable[1] = "1";
                        selectlablenum++;
                    }
                    break;
                case R.id.Tag03:
                    if(lable[2].equals("1")) {
                        Tag03.setSelected(false);
                        lable[2] = "0";
                        selectlablenum--;
                    }
                    else{
                        Tag03.setSelected(true);
                        lable[2] = "1";
                        selectlablenum++;
                    }
                    break;
                case R.id.Tag04:
                    if(lable[3].equals("1")) {
                        Tag04.setSelected(false);
                        lable[3] = "0";
                        selectlablenum--;
                    }
                    else{
                        Tag04.setSelected(true);
                        lable[3] = "1";
                        selectlablenum++;
                    }
                    break;
                case R.id.Tag05:
                    if(lable[4].equals("1")) {
                        Tag05.setSelected(false);
                        lable[4] = "0";
                    }
                    else{
                        Tag05.setSelected(true);
                        lable[4] = "1";
                        selectlablenum++;
                    }
                    break;
                case R.id.Tag06:
                    if(lable[5].equals("1")) {
                        Tag06.setSelected(false);
                        lable[5] = "0";
                    }
                    else{
                        Tag06.setSelected(true);
                        lable[5] = "1";
                        selectlablenum++;
                    }
                    break;
                case R.id.Tag07:
                    if(lable[6].equals("1")) {
                        Tag07.setSelected(false);
                        lable[6] = "0";
                    }
                    else{
                        Tag07.setSelected(true);
                        lable[6] = "1";
                        selectlablenum++;
                    }
                    break;
            }
        }
    }
}
