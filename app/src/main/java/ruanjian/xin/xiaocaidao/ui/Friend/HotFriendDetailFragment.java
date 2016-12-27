package ruanjian.xin.xiaocaidao.ui.Friend;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ruanjian.xin.xiaocaidao.Controller.ApplicationController;
import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.adapter.FriendCommentAdapter;
import ruanjian.xin.xiaocaidao.domain.BlogItem;
import ruanjian.xin.xiaocaidao.domain.CircleNetworkImage;
import ruanjian.xin.xiaocaidao.domain.CommentItem;
import ruanjian.xin.xiaocaidao.domain.Parts;
import ruanjian.xin.xiaocaidao.domain.StepItem;
import ruanjian.xin.xiaocaidao.utils.Calculator;
import ruanjian.xin.xiaocaidao.utils.HttpUtil;
import ruanjian.xin.xiaocaidao.utils.Utils;

import static ruanjian.xin.xiaocaidao.utils.Utils.JUHE_KEY;
import static ruanjian.xin.xiaocaidao.utils.Utils.JUHE_URL;

public class HotFriendDetailFragment extends Fragment {    //热门帖子详情页

    private HttpUtil httpUtil = new HttpUtil();
    private Button btn_focus;
    private static String follows;
    private static String blogId;

    private boolean flag_praise = false;
    private boolean flag_collect = false;

    private CircleNetworkImage avatarImg;       //头像Img控件
    private TextView TvUserName;           //作者用户名称
    private TextView TvContent;           //帖子内容
    private TextView TvTitle;           //帖子题目
    private TextView TvThumb;           //点赞数目
    private TextView TvCollect;         //收藏一下
    private NetworkImageView blogImg;   //帖子图片
    private ImageView img_praise;       //点赞图片
    private ImageView img_collect;      //收藏图片

    private FriendCommentAdapter commentAdapter;
    private ListView Lv_comment;
    private List<CommentItem> ComData = new ArrayList<>();

    //有关ImageLoader
    private ImageLoader imageLoader;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){
                btn_focus.setText("已关注");
                btn_focus.setSelected(false);
                Toast.makeText(getActivity(),"关注成功",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity(),"已关注",Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    };
    private Handler praise = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==-1){
                Toast.makeText(getActivity(),"连接超时",Toast.LENGTH_SHORT).show();
            }else{
                if(!flag_praise){
                    img_praise.setImageResource(R.drawable.like_sel);
                    flag_praise = true;
                }
                TvThumb.setText(msg.what+"人赞过");
            }
            super.handleMessage(msg);
        }
    };
    private Handler collect = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==-1){
                Toast.makeText(getActivity(),"连接超时",Toast.LENGTH_SHORT).show();
            }else if(msg.what==0){
                if(!flag_collect){
                    img_collect.setImageResource(R.drawable.collect_sel);
                    flag_collect = true;
                    Toast.makeText(getActivity(),"收藏成功",Toast.LENGTH_SHORT).show();
                    TvCollect.setText("已收藏");
                }
            }else{
                if(!flag_collect){
                    img_collect.setImageResource(R.drawable.collect_sel);
                    flag_collect = true;
                    TvCollect.setText("已收藏");
                }
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_hot_item_detail,container,false);

        Bundle bundle = getArguments();//从activity传过来的Bundle

        blogId = bundle.getString("blog_id");
        Log.e("帖子详情id",blogId);

        getView(v);
        setView();      //显示
        isCollected();
        setComment();
        return v;
    }

    private void getView(View v) {
        btn_focus = (Button)v.findViewById(R.id.btn_focus);
        commentListener listener = new commentListener();
        btn_focus.setOnClickListener(listener);
        avatarImg = (CircleNetworkImage)v.findViewById(R.id.cv_comment_user_pic) ;
        TvUserName = (TextView)v.findViewById(R.id.Tv_userName);
        TvContent = (TextView)v.findViewById(R.id.Tv_comment_content);
        TvTitle = (TextView)v.findViewById(R.id.Tv_comment_title);
        TvThumb = (TextView)v.findViewById(R.id.Tv_comment_thumb);
        TvCollect = (TextView)v.findViewById(R.id.tv_collect);
        blogImg = (NetworkImageView)v.findViewById(R.id.Iv_comment_blogimg);
        img_praise = (ImageView)v.findViewById(R.id.img_praise);
        img_praise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        httpUtil.setValue(HttpUtil.SET_TH,blogId);
                        Log.i("blogid",blogId);
                        if(!flag_praise) {
                            String end = httpUtil.HttpRequest_post(Utils.set_thUrl);
                            if (end == null) {
                                praise.sendEmptyMessage(-1);
                            }
                            //Log.i("blogid",end);
                            praise.sendEmptyMessage(Integer.parseInt(end));
                        }
                        super.run();
                    }
                }.start();
            }
        });
        img_collect = (ImageView)v.findViewById(R.id.img_collect);
        img_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        httpUtil.setValue(HttpUtil.SET_CO,httpUtil.uac,blogId);
                        Log.i("blogid",blogId);
                        if(!flag_collect) {
                            String end = httpUtil.HttpRequest_post(Utils.set_coUrl);
                            if (end == null) {
                                collect.sendEmptyMessage(-1);
                            }
                            //Log.i("blogid",end);
                            collect.sendEmptyMessage(0);
                        }
                        super.run();
                    }
                }.start();
            }
        });
        if (imageLoader == null)
            imageLoader = ApplicationController.getInstance().getImageLoader();
        Lv_comment = (ListView)v.findViewById(R.id.comment_listView);

    }

    private void setView(){
        getBlogData();
        getCommentData();
    }

    public void setComment() {
        commentAdapter = new FriendCommentAdapter(getActivity(),ComData);
        Lv_comment.setAdapter(commentAdapter);
    }

    public void isCollected(){
        new Thread(){
            @Override
            public void run() {
                httpUtil.setValue(HttpUtil.SET_TH,blogId);
                Log.i("blogid",blogId);
                String end = httpUtil.HttpRequest_post(Utils.check_coUrl);
                if (end.equals("1")) {
                    collect.sendEmptyMessage(1);
                }
                super.run();
            }
        }.start();
    }


    public void getBlogData() {
        StringRequest req = new StringRequest(Request.Method.POST, Utils.URL+"blog/findBlog", new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //hidePDialog();
                    }
                }, 1000);

                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                System.out.println("blog jsonArray:" + jsonArray.toString());

                try {

                    JSONObject obj = jsonArray.getJSONObject(0);     //取出每个对象
                    String user = obj.getString("edit_user");       //取出用户 账户，
                    String imgsrc = obj.getString("img_src");       //取出图片url
                    System.out.println("imgsrc");
                    int thumb = obj.getInt("thumb");            //点赞数
                    String title = obj.getString("name");       //title
                    String content = obj.getString("content");      //内容
                    String userName = obj.getString("userName");    //userName
                    String userImg = obj.getString("userImg");      //头像url

                    follows = user;//得到帖子账户account

                    avatarImg.setImageUrl(userImg,imageLoader);
                    TvUserName.setText(userName);
                    TvTitle.setText(title);
                    TvContent.setText(content);
                    TvThumb.setText(""+thumb+"人赞过");
                    blogImg.setImageUrl(imgsrc,imageLoader);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("CaipuMenuPge", "error:" + volleyError.getMessage());
                //hidePDialog();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("id",blogId);
                return map;
            }
        };
        ApplicationController.getInstance().addToRequestQueue(req);
    }

    public void getCommentData(){
        ComData.clear();
        StringRequest req=new StringRequest(Request.Method.POST, Utils.URL+"comment/findCommentList",new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //hidePDialog();
                    }
                },1000);

                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                System.out.println("findCommentList jsonArray:" + jsonArray.toString());
                try {
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject obj = jsonArray.getJSONObject(i);
                        String ComContent = obj.getString("content");
                        String ComUsername = obj.getString("userName");
                        String ComUserImg = obj.getString("userImg");

                        CommentItem commentItem = new CommentItem(ComUserImg,ComContent,ComUsername);
                        System.out.println("obj.toString:::"+obj.toString());

                        ComData.add(commentItem);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                commentAdapter.notifyDataSetChanged();
                Calculator.setListViewHeightBasedOnChildren(Lv_comment,0);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("HotFriendDetailFragment","error:"+volleyError.getMessage());
                //hidePDialog();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("blog_id",blogId);
                return map;
            }
        };
        ApplicationController.getInstance().addToRequestQueue(req);
    }
    private class commentListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(HttpUtil.uac.equals(follows)){ return; }
            new Thread(){
                @Override
                public void run() {
                    httpUtil.setValue(HttpUtil.SET_FO, HttpUtil.uac,follows);
                    String end = httpUtil.HttpRequest_post(Utils.set_foUrl);
                    if(end.equals("1")){
                        handler.sendEmptyMessage(1);
                    }else{
                        handler.sendEmptyMessage(0);
                    }
                    super.run();
                }
            }.start();
        }
    }
}
