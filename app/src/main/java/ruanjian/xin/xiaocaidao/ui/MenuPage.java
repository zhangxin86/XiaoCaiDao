package ruanjian.xin.xiaocaidao.ui;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

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
import ruanjian.xin.xiaocaidao.adapter.BaiDuRefreshListView;
import ruanjian.xin.xiaocaidao.adapter.LvAdapter;
import ruanjian.xin.xiaocaidao.domain.Caipu;

public class MenuPage extends Fragment implements BaiDuRefreshListView.OnBaiduRefreshListener{



    private BaiDuRefreshListView lv;
    private ArrayList<Caipu> cp;
    private LvAdapter myadapter;

    private static final String url="http://apis.juhe.cn/cook/query.php";
    private ProgressDialog pDialog;

    private final static int REFRESH_COMPLETE = 0;
    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    lv.setOnRefreshComplete();
                    myadapter.notifyDataSetChanged();
                    lv.setSelection(0);
                    break;
                default:
                    break;
            }
        };
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_menu_page,container,false);
        cp = new ArrayList<Caipu>();
        fetchCaipu();
        getView(v);
        setAdapter();
        onClick();

        return v;


    }
    public void onClick(){
        //经典菜谱点击事件，进行页面跳转
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent();
                intent.setClass(getActivity(),XiangqingPage.class);
                startActivity(intent);
                //需链接到内容页
            }
        });
    }

    private void getView(View v){
        lv = (BaiDuRefreshListView)v.findViewById(R.id.lvBaiduRefresh);
    }

    public void setAdapter(){
        myadapter = new LvAdapter(cp,getActivity());
        lv.setAdapter(myadapter);
        lv.setOnBaiduRefreshListener(this);
    }


    @Override
    public void onRefresh(){//刷新方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    cp.add(new Caipu("茄汁莲藕排骨","排骨，莲藕，茄汁，盐","http://pic13.nipic.com/20110312/1263154_111916318000_2.jpg"));
                    mHandler.sendEmptyMessage(REFRESH_COMPLETE);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void fetchCaipu() {

        StringRequest req=new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {

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

                System.out.println(jsonObject.toString());

                try {
                    JSONObject result=jsonObject.getJSONObject("result");
                    JSONArray data = result.getJSONArray("data");
                    for (int i=0;i<data.length();i++){
                        JSONObject menu = data.getJSONObject(i);
                        String name = menu.getString("title");//名称
                        String ingredients = menu.getString("ingredients");//主料
                        String burden = menu.getString("burden");//辅料
                        JSONArray albums = menu.getJSONArray("albums");//成品图片集合
                        String img = albums.getString(0);//成品图片
                        JSONArray steps = menu.getJSONArray("steps");//步骤对象数组
                        Caipu caipu = new Caipu();
                        caipu.setImgURL(img);
                        caipu.setName(name);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hidePDialog();
    }

    public void hidePDialog(){
        if(pDialog!=null)
            pDialog.dismiss();
        pDialog=null;
    }
}
