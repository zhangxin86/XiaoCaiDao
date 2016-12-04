package ruanjian.xin.xiaocaidao.ui;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.domain.XCRoundImageView;
import ruanjian.xin.xiaocaidao.ui.personal.Person_about;
import ruanjian.xin.xiaocaidao.ui.personal.Person_collect;
import ruanjian.xin.xiaocaidao.ui.personal.Person_fouse;
import ruanjian.xin.xiaocaidao.ui.personal.Person_post;
import ruanjian.xin.xiaocaidao.ui.personal.Person_setting;

public class PersonPage extends Fragment {
    private XCRoundImageView person1;
    private static View v;

    LinearLayout mFollows;//我的关注选项
    LinearLayout mSave;//我的收藏选项
    LinearLayout mPost;//我的帖子选项
    LinearLayout mSet;//设置选项
    LinearLayout mAbout;//关于我们选项

    private final int IMG_CHANGE = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_person_page,container,false);
        person1 = (XCRoundImageView)v.findViewById(R.id.personal_1) ;


        mFollows = (LinearLayout)v.findViewById(R.id.Ll_Follows);
        mSave = (LinearLayout)v.findViewById(R.id.Ll_save);
        mPost = (LinearLayout)v.findViewById(R.id.Ll_Post);
        mSet = (LinearLayout)v.findViewById(R.id.Ll_Setting);
        mAbout = (LinearLayout)v.findViewById(R.id.Ll_about);

        /*如果头像本机已经自己保存过，就加载本机对应头像的路径的图片*/
        if (fileIsExists()){
            String dateFolder = new SimpleDateFormat("yyyyMMdd", Locale.CHINA)
                    .format(new Date());
            String filePath = Environment.getExternalStorageDirectory() + "/JiaXT/" + dateFolder + "/";

            person1.setImageURI(Uri.fromFile(new File(filePath,"temphead.jpg")));
        }
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.Ll_Follows:
                            Intent intent = new Intent();
                            intent.setClass(getActivity(),Person_fouse.class);
                            startActivity(intent);
                            break;

                        case R.id.Ll_save:
                            Intent intent1 = new Intent();
                            intent1.setClass(getActivity(),Person_collect.class);
                            startActivity(intent1);
                            break;
                        case R.id.Ll_Post:
                            Intent intent2 = new Intent();
                            intent2.setClass(getActivity(),Person_post.class);
                            startActivity(intent2);
                            break;

                        case R.id.Ll_Setting:
                            Intent intent3 = new Intent();
                            intent3.setClass(getActivity(),Person_setting.class);
                            startActivityForResult(intent3,IMG_CHANGE);
                            break;
                        case R.id.Ll_about:
                            Intent intent4 = new Intent();
                            intent4.setClass(getActivity(),Person_about.class);
                            startActivity(intent4);
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
        mAbout.setOnClickListener(listener);
        return v;
    }


    public static boolean fileIsExists(){
        try{
            String dateFolder = new SimpleDateFormat("yyyyMMdd", Locale.CHINA)
                    .format(new Date());
            String filePath = Environment.getExternalStorageDirectory() + "/JiaXT/" + dateFolder + "/";
            File f=new File(filePath,"temphead.jpg");
            if(!f.exists()){
                return false;
            }

        }catch (Exception e) {
            // TODO: handle exception
            return false;
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode){
            case IMG_CHANGE:
                if (fileIsExists()){
                    String dateFolder = new SimpleDateFormat("yyyyMMdd", Locale.CHINA)
                            .format(new Date());
                    String filePath = Environment.getExternalStorageDirectory() + "/JiaXT/" + dateFolder + "/";

                    person1.setImageURI(Uri.fromFile(new File(filePath,"temphead.jpg")));
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
        String filePath  = getAvatarFilePath();
        person1.setImageURI(Uri.fromFile(new File(filePath,"temphead.jpg")));

        super.onResume();
    }
    /**
     *得到图片路径
     */
    public String getAvatarFilePath(){
        //时间戳 设置日期到day 待改善 会出现今天设置头像明天找不到的后果
        String dateFolder = new SimpleDateFormat("yyyyMMdd", Locale.CHINA)
                .format(new Date());
        String filePath = Environment.getExternalStorageDirectory() + "/JiaXT/" + dateFolder + "/";

        return filePath;
    }

}
