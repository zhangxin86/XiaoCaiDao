package ruanjian.xin.xiaocaidao.ui.Friend;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ruanjian.xin.xiaocaidao.Client_hot_detail;
import ruanjian.xin.xiaocaidao.Controller.ApplicationController;
import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.adapter.CommentAdapter;
import ruanjian.xin.xiaocaidao.adapter.NewAdapter;
import ruanjian.xin.xiaocaidao.domain.BlogItem;
import ruanjian.xin.xiaocaidao.domain.Parts;
import ruanjian.xin.xiaocaidao.utils.Utils;

/**
 * Created by lhm on 2016/11/22.
 */

public class NewFriend extends Activity{    //最新贴
    private CommentAdapter hotAdapter;
    private ListView listView;
    private List<BlogItem> data = new ArrayList<>();

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot);

        getData2();
        findView();
        setAdapter();
        setListener();

    }

    private void setListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("帖子详情跳转Id",""+data.get(position).getId());

                Intent intent = new Intent();
                intent.putExtra("blog_id",""+data.get(position).getId());
                intent.setClass(getApplicationContext(), Client_hot_detail.class);
                startActivity(intent);
            }
        });
    }

    private void setAdapter() {
        hotAdapter = new CommentAdapter(this,data);
        listView.setAdapter(hotAdapter);
    }

    private void findView() {
        listView = (ListView)findViewById(R.id.hot_listView);
    }


    public void getData() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(getApplicationContext(), Utils.URL+"blog/fetchHotBlogList",new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                System.out.println(response.toString());            //调试输出返回数组
                try {
                    for (int i = 0;i<response.length();i++){
                        JSONObject obj = response.getJSONObject(i);     //取出每个对象
                        String user = obj.getString("edit_user");       //取出用户名称，拿到适配器中处理后显示，由此找到 头像 和 昵称
                        String imgsrc = obj.getString("img_src");       //取出图片url
                        System.out.println("imgsrc");
                        int thumb = obj.getInt("thumb");            //点赞数
                        String content = obj.getString("content");      //内容

                        int Id = obj.getInt("id");         //得到此id，不在显示，备于界面跳转到对应帖子的界面

                        /*用户名，头像url(没用上在适配器中处理了)，图片，评论数（没用上），内容文字，  id    ，标签1（待用），标签2（待用），点赞数
                        *String account, String avatarUrl, String blogImg, String comment, String countent, Long id, String lab1, String lab2, String thumb*/
                        BlogItem blog = new BlogItem(user,"",imgsrc,thumb,content,(long)Id,"清淡","养生",thumb);

                        data.add(blog);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                hotAdapter.notifyDataSetChanged();       //当内容更新时候，动态刷新ListView
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

    public void getData2(){
        StringRequest req=new StringRequest(Request.Method.POST,Utils.URL+"blog/fetchNewlyBlogList", new Response.Listener<String>() {

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

                System.out.println(jsonArray.toString());

                try {
                    for (int i = 0;i<jsonArray.length();i++){
                        JSONObject obj = jsonArray.getJSONObject(i);     //取出每个对象
                        String user = obj.getString("edit_user");       //取出用户名称，拿到适配器中处理后显示，由此找到 头像 和 昵称
                        String imgsrc = obj.getString("img_src");       //取出图片url
                        System.out.println("imgsrc");
                        int thumb = obj.getInt("thumb");            //点赞数
                        String content = obj.getString("content");      //内容
                        String userName = obj.getString("userName");    //userName
                        String userImg = obj.getString("userImg");      //头像url

                        int Id = obj.getInt("id");         //得到此id，不在显示，备于界面跳转到对应帖子的界面

                        /*用户名，头像url(没用上在适配器中处理了)，图片，评论数（没用上），内容文字，  id    ，标签1（待用），标签2（待用），点赞数
                        *String account, String avatarUrl, String blogImg, String comment, String countent, Long id, String lab1, String lab2, String thumb*/
                        BlogItem blog = new BlogItem(userName,userImg,imgsrc,thumb,content,(long)Id,"清淡","养生",thumb);

                        data.add(blog);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                hotAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("CaipuMenuPge","error:"+volleyError.getMessage());
                hidePDialog();
            }
        });
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
