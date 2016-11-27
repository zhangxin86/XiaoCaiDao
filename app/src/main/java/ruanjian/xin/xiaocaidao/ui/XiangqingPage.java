package ruanjian.xin.xiaocaidao.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

/**
 * Created by liyuxuan on 2016/11/27.
 */



public class XiangqingPage extends Activity {

    private TextView tvName;//名称
    private TextView tvTags;//标签
    private ImageView ivImg;//主图
    private TextView tvInfo;//描述
    private TextView tvIngredients;//主料
    private TextView tvBurden;//辅料
    private LinearLayout llSteps;//步骤的LinearLayout

    private ListView lvSteps;
    private StepAdapter myadapter;
    private ArrayList<StepItem> mSteps = new ArrayList<StepItem>();

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing_page);

        tvName = (TextView) findViewById(R.id.tv_menuName);
        tvTags = (TextView)findViewById(R.id.tv_tags);
        ivImg = (ImageView)findViewById(R.id.iv_menuImg);
        tvInfo = (TextView)findViewById(R.id.tv_info);
        tvIngredients = (TextView)findViewById(R.id.tv_ingredients);
        tvBurden = (TextView)findViewById(R.id.tv_burden);
        llSteps = (LinearLayout)findViewById(R.id.ll_steps);


        AsyncHttpClient httpclient = new AsyncHttpClient();
        String Url = "http://apis.juhe.cn/cook/query.php";
        RequestParams params = new RequestParams();
        params.add("key","90e8a667333aa3c83bbfdcabbd0fa620");
        params.add("menu","红烧肉");
        params.add("rn","1");
        httpclient.get(getApplicationContext(),Url,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                System.out.println(response.toString());

                try {
                    JSONObject result = response.getJSONObject("result");
                    JSONArray data = result.getJSONArray("data");
                    for (int i=0;i<data.length();i++){
                        JSONObject obj = data.getJSONObject(i);
                        String title = obj.getString("title");//菜品名称标题
                        tvName.setText(title);
                        String tags = obj.getString("tags");//标签
                        tvTags.setText(tags);
                        String imtro = obj.getString("imtro");//描述
                        tvInfo.setText(imtro);
                        String ingredients = obj.getString("ingredients");//主料
                        tvIngredients.setText(ingredients);
                        String burden = obj.getString("burden");//辅料
                        tvBurden.setText(burden);
                        String album = obj.getJSONArray("albums").getString(0);//成品图
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

                       // JSONArray steps = obj.getJSONArray("steps");
                       /* for (int j=0;j<steps.length();j++){
                            JSONObject stepobj = steps.getJSONObject(j);
                            String step = stepobj.getString("step");
                            TextView tvStep = new TextView(getApplicationContext());
                            tvStep.setText(step);
                            llSteps.addView(tvStep);
                            String stepImgs = stepobj.getString("img");
                            new AsyncHttpClient().get(stepImgs, new AsyncHttpResponseHandler(){
                                @Override
                                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                    if(statusCode==200){
                                        BitmapFactory factory=new BitmapFactory();
                                        Bitmap bitmap=factory.decodeByteArray(responseBody, 0, responseBody.length);

                                        ImageView ivStep = new ImageView(getApplicationContext());

                                        ivStep.setImageBitmap(bitmap);
                                        llSteps.addView(ivStep);
                                    }
                                }

                                @Override
                                public void onFailure(int statusCode, Header[] headers,
                                                      byte[] responseBody, Throwable error) {
                                    error.printStackTrace();
                                }
                            });
                        }*/


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(getApplicationContext(),"出事儿了",Toast.LENGTH_SHORT).show();
            }
        });

        fetchSteps();
        lvSteps = (ListView)findViewById(R.id.lv_steps);
        myadapter = new StepAdapter(getApplicationContext(),mSteps);
        lvSteps.setAdapter(myadapter);

    }

    private void fetchSteps() {

        StringRequest req=new StringRequest(Request.Method.POST,"http://apis.juhe.cn/cook/query.php", new Response.Listener<String>() {

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

                System.out.println("try之前："+jsonObject.toString());

                try {
                    JSONObject result=jsonObject.getJSONObject("result");
                    JSONArray data = result.getJSONArray("data");
                    JSONObject menu = data.getJSONObject(0);
                    JSONArray steps = menu.getJSONArray("steps");//步骤对象数组
                    for (int j=0;j<steps.length();j++){
                        System.out.println("循环打印"+j);
                        JSONObject stepobj = steps.getJSONObject(j);
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
                map.put("key","90e8a667333aa3c83bbfdcabbd0fa620");
                map.put("menu","红烧肉");
                return map;
            }
        };
        ApplicationController.getInstance().addToRequestQueue(req);
    }

    public void hidePDialog(){
        if(pDialog!=null)
            pDialog.dismiss();
        pDialog=null;
    }}
