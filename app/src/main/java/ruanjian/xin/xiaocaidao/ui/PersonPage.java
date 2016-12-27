package ruanjian.xin.xiaocaidao.ui;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.domain.XCRoundImageView;
import ruanjian.xin.xiaocaidao.ui.personal.Person_collect;
import ruanjian.xin.xiaocaidao.ui.personal.Person_fouse;
import ruanjian.xin.xiaocaidao.ui.personal.Person_post;
import ruanjian.xin.xiaocaidao.ui.personal.Person_setting;
import ruanjian.xin.xiaocaidao.utils.HttpUtil;
import ruanjian.xin.xiaocaidao.utils.Utils;

/**
 * All rights Reserved, Designed By liyuxuna
 * @Title: 	SelectPicPopupWindow.java
 * @Package sun.geoffery.uploadpic
 * @Description:个人中心
 * @author:	liyuxuan
 * @date:	2016年12月12日 上午1:21:01
 * @version	V1.0
 */

public class PersonPage extends Fragment {
    private XCRoundImageView person1;
    private HttpURLConnection urlcon;
    private static View v;
    public static String PicPath;
    public static String PicName;//文件名

    private HttpUtil httpUtil = new HttpUtil();

    private TextView Tvactivity_person_pageUserName;//用户昵称
    private TextView Tvactivity_person_pageFans;//粉丝数
    private TextView Tvactivity_person_pageFocus;//关注数
    private LinearLayout mFollows;//我的关注选项
    private LinearLayout mSave;//我的收藏选项
    private LinearLayout mPost;//我的帖子选项
    private LinearLayout mSet;//设置选项
    //LinearLayout mAbout;//关于我们选项

    private final int IMG_CHANGE = 1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String filePath = Environment.getExternalStorageDirectory() + "/JiaXT/" + "myHead" + "/";
            person1.setImageURI(Uri.fromFile(new File(filePath, PicName)));
            Bundle b = msg.getData();
            PicName = PicPath.substring(32);
            Tvactivity_person_pageFans.setText(b.getString("fans"));
            Tvactivity_person_pageFocus.setText(b.getString("follows"));
            super.handleMessage(msg);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_person_page, container, false);
        person1 = (XCRoundImageView) v.findViewById(R.id.personal_1);

        Tvactivity_person_pageUserName = (TextView) v.findViewById(R.id.Tvactivity_person_pageUserName);
        Tvactivity_person_pageFans = (TextView) v.findViewById(R.id.Tvactivity_person_pageFans);
        Tvactivity_person_pageFocus = (TextView) v.findViewById(R.id.Tvactivity_person_pageFocus);
        mFollows = (LinearLayout) v.findViewById(R.id.Ll_Follows);
        mSave = (LinearLayout) v.findViewById(R.id.Ll_save);
        mPost = (LinearLayout) v.findViewById(R.id.Ll_Post);
        mSet = (LinearLayout) v.findViewById(R.id.Ll_Setting);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.Ll_Follows:
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), Person_fouse.class);
                        startActivity(intent);
                        break;
                    case R.id.Ll_save:
                        Intent intent1 = new Intent();
                        intent1.setClass(getActivity(), Person_collect.class);
                        startActivity(intent1);
                        break;
                    case R.id.Ll_Post:
                        Intent intent2 = new Intent();
                        intent2.setClass(getActivity(), Person_post.class);
                        intent2.putExtra("userName",Tvactivity_person_pageUserName.getText().toString());
                        startActivity(intent2);
                        break;
                    case R.id.Ll_Setting:
                        Intent intent3 = new Intent();
                        intent3.setClass(getActivity(), Person_setting.class);
                        startActivityForResult(intent3, IMG_CHANGE);
                        break;
                    default:
                        break;
                }
            }
        };
        mFollows.setOnClickListener(listener);
        mSave.setOnClickListener(listener);
        mPost.setOnClickListener(listener);
        mSet.setOnClickListener(listener);
//        Judgement();
        //mAbout.setOnClickListener(listener);
        return v;
    }


    public static boolean fileIsExists() {
        try {
            String filePath = Environment.getExternalStorageDirectory() + "/JiaXT/" + "myHead" + "/";
            File f = new File(filePath, PicName);
            if (!f.exists()) {
                return false;
            }

        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case IMG_CHANGE:
                if (fileIsExists()) {
                    String dateFolder = new SimpleDateFormat("yyyyMMdd", Locale.CHINA)
                            .format(new Date());
                    String filePath = Environment.getExternalStorageDirectory() + "/JiaXT/" + "myHead" + "/";
                    person1.setImageURI(Uri.fromFile(new File(filePath,PicName)));
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*
    * 当设置完后返回时调用  !未完成，还待开发
    * */
    @Override
    public void onResume() {

        System.out.println("ONResume:!!!!");
        setMessage();
        super.onResume();
    }

    /**
     * 得到图片路径
     */
    public String getAvatarFilePath() {
        //时间戳 设置日期到day 待改善 会出现今天设置头像明天找不到的后果
        String filePath = Environment.getExternalStorageDirectory() + "/JiaXT/" + "myHead" + "/";

        return filePath;
    }

    public void setMessage() {
        Tvactivity_person_pageUserName.setText(HttpUtil.una);
        new Thread() {
            @Override
            public void run() {
                httpUtil.setValue(httpUtil.FINDORCHECK_AC, HttpUtil.uac);
                PicPath = httpUtil.HttpRequest_post(Utils.find_imUrl);
                PicName = PicPath.substring(32);
                httpUtil.setValue(httpUtil.FINDORCHECK_AC, httpUtil.uac);
                String fans = httpUtil.HttpRequest_post(Utils.find_faUrl);
                String follows = httpUtil.HttpRequest_post(Utils.find_foUrl);
                Message message = new Message();
                Bundle bundle = new Bundle();
                if(!fileIsExists()){
                    download();
                }
                bundle.putString("fans", fans);
                bundle.putString("follows", follows);
                message.setData(bundle);
                handler.sendMessage(message);
                super.run();
            }
        }.start();
    }

    public void download() {
        String urlStr=PicPath;
        String path= "/JiaXT/" + "myHead" + "/";
        String fileName=PicName;
        OutputStream output=null;
        try {
                /*
                 * 通过URL取得HttpURLConnection
                 * 要网络连接成功，需在AndroidMainfest.xml中进行权限配置
                 * <uses-permission android:name="android.permission.INTERNET" />
                 */
            URL url=new URL(urlStr);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            //取得inputStream，并将流中的信息写入SDCard

                /*
                 * 写前准备
                 * 1.在AndroidMainfest.xml中进行权限配置
                 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
                 * 取得写入SDCard的权限
                 * 2.取得SDCard的路径： Environment.getExternalStorageDirectory()
                 * 3.检查要保存的文件上是否已经存在
                 * 4.不存在，新建文件夹，新建文件
                 * 5.将input流中的信息写入SDCard
                 * 6.关闭流
                 */
            String SDCard=Environment.getExternalStorageDirectory()+"";
            String pathName=SDCard+"/"+path+"/"+fileName;//文件存储路径

            File file=new File(pathName);
            InputStream input=conn.getInputStream();
            if(file.exists()){
                System.out.println("exits");
                return;
            }else{
                String dir=SDCard+"/"+path;
                new File(dir).mkdir();//新建文件夹
                file.createNewFile();//新建文件
                output=new FileOutputStream(file);
                //读取大文件
                byte[] buffer=new byte[1024];
                int len;
                while((len = input.read(buffer))!=-1){
                    output.write(buffer,0,len);
                }
                output.flush();
                output.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
