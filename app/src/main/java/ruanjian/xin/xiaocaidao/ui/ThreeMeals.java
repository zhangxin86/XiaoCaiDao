package ruanjian.xin.xiaocaidao.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

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
import java.util.Map;

import ruanjian.xin.xiaocaidao.Controller.ApplicationController;
import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.adapter.MenuAdapter;
import ruanjian.xin.xiaocaidao.domain.Caipu;

import static ruanjian.xin.xiaocaidao.utils.Utils.JUHE_KEY;
import static ruanjian.xin.xiaocaidao.utils.Utils.JUHE_URL;

public class ThreeMeals extends AppCompatActivity {

    public String tempName = null;
    private String menuName;//纪录点击事件时某个item的Name

    /*有关json请求*/
    public JSONObject result;//json返回结果
    public JSONArray data;
    public String id;//菜品id
    public String name;//菜品名称
    public String ingredients;//主料
    public String burden;//辅料
    public JSONArray albums;//成品图片集合
    public String img;//成品图

    private MenuAdapter myadapter;
    private ArrayList<Caipu> cp;
    private ListView listView;
    private String mId;

    private ProgressDialog pDialog;
    TextView Tv_ThreeMeals ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_meals);

        tempName = this.getIntent().getStringExtra("推荐");
        cp = new ArrayList<Caipu>();

        findView();
        setBarView();
        setAdapter();
        fetchCaipu(tempName,myadapter);

        onClick();

    }
    public void onClick(){
        //经典菜谱点击事件，进行页面跳转
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                menuName = cp.get(position).getName();
                mId = cp.get(position).getID();

                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),XiangqingPage.class);
                intent.putExtra("menuName",menuName);
                intent.putExtra("id",mId);
                startActivity(intent);
                //需链接到内容页
            }
        });
    }


    private void setAdapter() {
        myadapter = new MenuAdapter(cp,this);
        listView.setAdapter(myadapter);
    }

    private void findView() {
        Tv_ThreeMeals = (TextView)findViewById(R.id.Tvactivity_three_mealsThreeMeals);
        listView = (ListView)findViewById(R.id.three_meals_listview);
    }
    private void setBarView(){
        Tv_ThreeMeals.setText(tempName);
        if (tempName.equals("早餐")){
            Tv_ThreeMeals.setBackgroundColor(getResources().getColor(R.color.breakfast));
        }else if(tempName.equals("午餐")){
            Tv_ThreeMeals.setBackgroundColor(getResources().getColor(R.color.afternoon));
        }else {
            Tv_ThreeMeals.setBackgroundColor(getResources().getColor(R.color.dinner));
        }

    }


    /*
    *拉取菜谱列表
    * */
    private void fetchCaipu(final String caipuName,final MenuAdapter myadapter) {

        StringRequest req=new StringRequest(Request.Method.POST,JUHE_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //以后可以加上刷新逻辑
                    }
                },1000);

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                System.out.println(jsonObject.toString());

                try {
                    result=jsonObject.getJSONObject("result");
                    data = result.getJSONArray("data");
                    for (int i=0;i<data.length();i++){
                        JSONObject menu = data.getJSONObject(i);
                        id = menu.getString("id");//获得菜品id
                        name = menu.getString("title");//名称
                        ingredients = menu.getString("ingredients");//主料
                        burden = menu.getString("burden");//辅料
                        albums = menu.getJSONArray("albums");//成品图片集合
                        img = albums.getString(0);//成品图
                        Caipu caipu = new Caipu();
                        caipu.setImgURL(img);
                        caipu.setName(name);
                        caipu.setID(id);
                        caipu.setMaterial(ingredients+burden);

                        cp.add(caipu);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                myadapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("CaipuMenuPge","error:"+volleyError.getMessage());

            }
        }){
            @Override/*设置参数*/
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("key",JUHE_KEY);
                map.put("menu",caipuName);
                return map;
            }
        };
        ApplicationController.getInstance().addToRequestQueue(req);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    public void hidePDialog(){
        if(pDialog!=null)
            pDialog.dismiss();
        pDialog=null;
    }
}
