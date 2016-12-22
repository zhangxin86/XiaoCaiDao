package ruanjian.xin.xiaocaidao.ui.personal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ruanjian.xin.xiaocaidao.ui.Friend.Client_hot_detail;
import ruanjian.xin.xiaocaidao.Controller.ApplicationController;
import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.adapter.Adapter_post_main;
import ruanjian.xin.xiaocaidao.domain.BlogItem;
import ruanjian.xin.xiaocaidao.utils.HttpUtil;
import ruanjian.xin.xiaocaidao.utils.Utils;


/**
 * 选项：个人中心
 * 子选项：我的帖子
 * Created by 你的账户 on 2016/11/23.
 */

public class Person_post extends AppCompatActivity {
    private Button btn_Post;//主贴按钮
    private Button btn_Repost;//回帖按钮
    private LinearLayout linearLayout;
    private ListView LvMyPost;
    private Adapter_post_main myAdapter;

    private ProgressDialog pDialog;

    private static String[] Labs = {"早餐","午餐","晚餐","热菜","凉菜","酱料","食材"};
    private static String[] Ls = {"",""};
    private List<BlogItem> data = new ArrayList<>();

    private static String userName;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_post);

        userName = getIntent().getStringExtra("userName");//得到用户名称

        initview();
        setListViewListener();//为listView设置监听事件
    }
    private void initview(){
        linearLayout = (LinearLayout)findViewById(R.id.ll);
        //Listview
        LvMyPost = (ListView)findViewById(R.id.Lv_myBlog);
        getMyBlogData();
        myAdapter = new Adapter_post_main(this,data);
        LvMyPost.setAdapter(myAdapter);
    }

    private void setListViewListener(){
        LvMyPost.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //跳转到评价详情页面
                //parent.getId();
                Log.e("帖子详情跳转Id",""+data.get(position).getId());

                Intent intent = new Intent();
                intent.putExtra("blog_id",""+data.get(position).getId());
                intent.setClass(getApplicationContext(), Client_hot_detail.class);
                startActivity(intent);
            }
        });
    }
    public void getMyBlogData(){
        StringRequest req=new StringRequest(Request.Method.POST, Utils.URL+"blog/fetchMyBlogList", new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hidePDialog();
                    }
                },1000);

                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                System.out.println("blog jsonArrayMy:"+jsonArray.toString());



                try {
                    for (int i = 0;i<jsonArray.length();i++){
                        JSONObject obj = jsonArray.getJSONObject(i);     //取出每个对象
                        int thumb = obj.getInt("thumb");            //点赞数
                        String content = obj.getString("content");      //内容
                        String name = obj.getString("name");      //标题

                        String label = obj.getString("label");      //标签1 2
                        int count = 0;
                        for (int j=0;j<label.length();j++){
                            char tempChar = label.charAt(j);
                            if (tempChar == '1'){
                                System.out.println(Labs[j]);
                                Ls[count] = Labs[j];
                                count++;
                            }
                            if (count==2){
                                break;
                            }
                            if(j==label.length()&&Labs[j].equals("0")){
                                Ls[0]="";
                                Ls[1]="";
                            }
                        }

                        int Id = obj.getInt("id");         //得到此id，不在显示，备于界面跳转到对应帖子的界面

                        /*用户名，头像url(没用上在适配器中处理了)，图片，评论数（没用上），内容文字，  id    ，标签1（待用），标签2（待用），点赞数
                        *String account, String avatarUrl, String blogImg, String comment, String countent, Long id, String lab1, String lab2, String thumb*/
                        //BlogItem blog = new BlogItem(userName,userImg,imgsrc,thumb,content,(long)Id,"清淡","养生",thumb);
                        BlogItem blog = new BlogItem(userName,"","",name,content,(long)Id,Ls[0],Ls[1],thumb);
                        data.add(blog);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                myAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("CaipuMenuPge","error:"+volleyError.getMessage());
                hidePDialog();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("account", HttpUtil.uac);
                return map;
            }
        };
        ApplicationController.getInstance().addToRequestQueue(req);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    public void hidePDialog(){
        if(pDialog!=null)
            pDialog.dismiss();
        pDialog=null;
    }

}
