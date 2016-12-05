package ruanjian.xin.xiaocaidao.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ruanjian.xin.xiaocaidao.Controller.ApplicationController;
import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.adapter.StepAdapter;
import ruanjian.xin.xiaocaidao.domain.StepItem;
import ruanjian.xin.xiaocaidao.utils.Calculator;

import static ruanjian.xin.xiaocaidao.utils.Utils.JUHE_KEY;
import static ruanjian.xin.xiaocaidao.utils.Utils.JUHE_URL;

/**
 * Created by liyuxuan on 2016/11/27.
 */

public class XiangqingPage extends Activity {

    public JSONObject result;//返回结果
    public JSONArray data;
    public JSONObject obj;
    public String title;//菜谱名称标题
    public String tags;//标签
    public String imtro;//描述
    public String ingredients;//主料
    public String burden;//辅料
    public String album;//成品图url
    public JSONArray steps;//步骤对象数组
    public JSONObject stepobj;//步骤对象



    private String menuName;//上一个页面穿来的菜肴名称
    private String mId;//上一个页面传来的菜品id

    private TextView tvName;//名称控件
    private TextView tvTags;//标签控件
    private ImageView ivImg;//主图控件
    private TextView tvInfo;//描述控件
    private TextView tvIngredients;//主料控件
    private TextView tvBurden;//辅料控件

    private ListView lvSteps;
    private StepAdapter myadapter;
    private ArrayList<StepItem> mSteps = new ArrayList<>();

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menudetail);

        menuName=getIntent().getStringExtra("menuName");//得到要传入的要显示的菜品名称数据
        mId = getIntent().getStringExtra("id");

        tvName = (TextView) findViewById(R.id.tv_menuName);
        tvTags = (TextView)findViewById(R.id.tv_tags);
        ivImg = (ImageView)findViewById(R.id.iv_menuImg);
        tvInfo = (TextView)findViewById(R.id.tv_info);
        tvIngredients = (TextView)findViewById(R.id.tv_ingredients);
        tvBurden = (TextView)findViewById(R.id.tv_burden);

        pDialog=new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        fetchSteps(menuName,mId);


        lvSteps = (ListView)findViewById(R.id.lv_steps);
        myadapter = new StepAdapter(getApplicationContext(),mSteps);
        lvSteps.setAdapter(myadapter);

    }

    public void fetchSteps(final String menuName,final String mId) {

        AsyncHttpClient httpclient = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("key",JUHE_KEY);
        params.add("menu",menuName);
        httpclient.get(getApplicationContext(),JUHE_URL,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                System.out.println(response.toString());

                try {
                    result = response.getJSONObject("result");
                    data = result.getJSONArray("data");
                    /*找到对应id的菜*/
                    int i=0;
                    for (i=0;i<data.length();i++){
                        JSONObject tempObj = data.getJSONObject(i);
                        if (tempObj.getString("id").equals(mId)){
                            break;
                        }
                    }
                    obj = data.getJSONObject(i);
                    title = obj.getString("title");//菜品名称标题
                    tvName.setText(title);
                    tags = obj.getString("tags");//标签
                    tvTags.setText(tags);
                    imtro = obj.getString("imtro");//描述
                    tvInfo.setText(imtro);
                    ingredients = obj.getString("ingredients");//主料
                    tvIngredients.setText(ingredients);
                    burden = obj.getString("burden");//辅料
                    tvBurden.setText(burden);
                    album = obj.getJSONArray("albums").getString(0);//成品图
                    new AsyncHttpClient().get(album, new AsyncHttpResponseHandler(){
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            if(statusCode==200){
                                BitmapFactory factory=new BitmapFactory();
                                Bitmap bitmap=factory.decodeByteArray(responseBody, 0, responseBody.length);
                                ivImg.setImageBitmap(bitmap);
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers,
                                              byte[] responseBody, Throwable error) {
                            error.printStackTrace();
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(getApplicationContext(),"出错儿了",Toast.LENGTH_SHORT).show();
            }
        });

        StringRequest req=new StringRequest(Request.Method.POST,JUHE_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hidePDialog();
                    }
                },1000);

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    result=jsonObject.getJSONObject("result");
                    data = result.getJSONArray("data");
                    int i=0;
                    /*找到对应id的菜*/
                    for (i=0;i<data.length();i++){
                        JSONObject tempObj = data.getJSONObject(i);
                        if (tempObj.getString("id").equals(mId)){
                            break;
                        }
                    }
                    JSONObject menu = data.getJSONObject(i);
                    steps = menu.getJSONArray("steps");//步骤对象数组
                    for (int j=0;j<steps.length();j++){
                        stepobj = steps.getJSONObject(j);
                        String stepImg = stepobj.getString("img");
                        System.out.println("图片url："+stepImg);
                        String step = stepobj.getString("step");
                        StepItem stepItem = new StepItem();
                        stepItem.setStepImg(stepImg);
                        stepItem.setStepTitle(step);

                        mSteps.add(stepItem);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                myadapter.notifyDataSetChanged();
                Calculator.setListViewHeightBasedOnChildren(lvSteps,200);
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
                map.put("key",JUHE_KEY);
                map.put("menu",menuName);
                return map;
            }
        };
        ApplicationController.getInstance().addToRequestQueue(req);
    }

    public void hidePDialog(){
        if(pDialog!=null)
            pDialog.dismiss();
        pDialog=null;
    }

}
