package ruanjian.xin.xiaocaidao.ui.personal;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.domain.XCRoundImageView;
import ruanjian.xin.xiaocaidao.ui.PersonPage;
import ruanjian.xin.xiaocaidao.ui.SelectWindow.SelectPicPopupWindow;
import ruanjian.xin.xiaocaidao.utils.FileUtil;
import ruanjian.xin.xiaocaidao.utils.NetUtil;

import static ruanjian.xin.xiaocaidao.utils.Utils.JIFINAL_UPLOAD;

/**
 * Created by 你的账户 on 2016/11/23.
 */

public class Person_setting extends AppCompatActivity implements View.OnClickListener {
    private Context mContext;
    private XCRoundImageView avatarImg;// 头像图片
    private LinearLayout avater;//头像修改

    private SelectPicPopupWindow menuWindow; // 自定义的头像编辑弹出框
    // 上传服务器的路径【一般不硬编码到程序中】
    private String imgUrl = "";
    private static final String IMAGE_FILE_NAME = "avatarImage.jpg";// 头像文件名称
    private String urlpath;			// 图片本地路径
    private String resultStr = "";	// 服务端返回结果集
    private static ProgressDialog pd;// 等待进度圈

    private static final int REQUESTCODE_PICK = 0;		// 相册选图标记
    private static final int REQUESTCODE_TAKE = 1;		// 相机拍照标记
    private static final int REQUESTCODE_CUTTING = 2;	// 图片裁切标记
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_setting);

        mContext = Person_setting.this;

        initViews();
    }
    private void initViews(){
        avatarImg = (XCRoundImageView)findViewById(R.id.avatarImg);
        avater = (LinearLayout)findViewById(R.id.avatar);

        avater.setOnClickListener(this);

        /*如果头像本机已经自己保存过*/
        if (PersonPage.fileIsExists()){
            String dateFolder = new SimpleDateFormat("yyyyMMdd", Locale.CHINA)
                    .format(new Date());
            String filePath = Environment.getExternalStorageDirectory() + "/JiaXT/" + dateFolder + "/";

            avatarImg.setImageURI(Uri.fromFile(new File(filePath,"temphead.jpg")));
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.avatar:// 更换头像点击事件
                menuWindow = new SelectPicPopupWindow(mContext, itemsOnClick);
                menuWindow.showAtLocation(findViewById(R.id.setting),
                        Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                break;


            default:
                break;
        }
    }

    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                // 拍照
                case R.id.takePhotoBtn:
                    Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //下面这句指定调用相机拍照后的照片存储的路径
                    takeIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME)));
                    startActivityForResult(takeIntent, REQUESTCODE_TAKE);
                    break;
                // 相册选择图片
                case R.id.pickPhotoBtn:
                    Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
                    // 如果要限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
                    pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    startActivityForResult(pickIntent, REQUESTCODE_PICK);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUESTCODE_PICK:// 直接从相册获取
                try {
                    startPhotoZoom(data.getData());
                } catch (NullPointerException e) {
                    e.printStackTrace();// 用户点击取消操作
                }
                break;
            case REQUESTCODE_TAKE:// 调用相机拍照
                File temp = new File(Environment.getExternalStorageDirectory() + "/" + IMAGE_FILE_NAME);
                startPhotoZoom(Uri.fromFile(temp));
                break;
            case REQUESTCODE_CUTTING:// 取得裁剪后的图片
                if (data != null) {
                    setPicToView(data);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 裁剪图片方法实现
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, REQUESTCODE_CUTTING);
    }

    /**
     * 保存裁剪之后的图片数据
     * @param picdata
     */
    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        if (extras != null) {
            // 取得SDCard图片路径做显示
            Bitmap photo = extras.getParcelable("data");
            Drawable drawable = new BitmapDrawable(null, photo);
            //保存图片到本机
            urlpath = FileUtil.saveFile(mContext, "temphead.jpg", photo);
            avatarImg.setImageDrawable(drawable);

            pd = ProgressDialog.show(mContext, null, "正在上传图片，请稍候...");
            /*上传头像到服务器*/
            upLoadAvatar();

            pd.dismiss();
        }
    }
    /**
     * 头像上传
     * ps:图片路径或者名称须要改动！！！！！！！！！！！
     */
    public void upLoadAvatar(){
        String dateFolder = new SimpleDateFormat("yyyyMMdd", Locale.CHINA)
                .format(new Date());
        String filePath = Environment.getExternalStorageDirectory() + "/JiaXT/" + dateFolder + "/";
        System.out.println("filePath:"+filePath);
        File file = new File(filePath,"temphead.jpg");
        if (file.exists() && file.length() > 0) {
            AsyncHttpClient as = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            try {
                params.put("uploadfile", file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            as.post(JIFINAL_UPLOAD, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, byte[] bytes) {
                    try {
                        String res = new String(bytes,"UTF-8");
                        Toast.makeText(mContext,"头像上传成功！！！"+res,Toast.LENGTH_SHORT).show();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    pd.dismiss();
                }

                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                    System.out.println("头像上传失败！！！");
                }
            });
        }else {
            System.out.println("文件不存在！！！");
        }
    }
}
