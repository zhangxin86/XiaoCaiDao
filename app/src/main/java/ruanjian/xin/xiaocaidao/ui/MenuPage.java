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
import android.widget.EditText;
import android.widget.Toast;

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
import ruanjian.xin.xiaocaidao.adapter.MenuAdapter;
import ruanjian.xin.xiaocaidao.domain.Caipu;

import static ruanjian.xin.xiaocaidao.utils.Utils.JUHE_KEY;
import static ruanjian.xin.xiaocaidao.utils.Utils.JUHE_URL;
/**
 * All rights Reserved, Designed By liyuxuna
 * @Title: 	SelectPicPopupWindow.java
 * @Package sun.geoffery.uploadpic
 * @Description:MenuPage
 * @author:	liyuxuan
 * @date:	2016年12月12日 上午1:21:01
 * @version	V1.0
 */
public class MenuPage extends Fragment implements BaiDuRefreshListView.OnBaiduRefreshListener{

    public String temp = "菜";//第一次要显示listview的菜名内容
    private String menuName;//纪录点击事件时某个item的Name
    private String mId;//纪录点击事件时某个item的ID

    /*有关json请求*/
    public JSONObject result;//json返回结果
    public JSONArray data;
    public String id;//菜品id
    public String name;//菜品名称
    public String ingredients;//主料
    public String burden;//辅料
    public JSONArray albums;//成品图片集合
    public String img;//成品图

    private BaiDuRefreshListView lv;
    private ArrayList<Caipu> cp;
    private MenuAdapter myadapter;

    private ProgressDialog pDialog;

    private final static int REFRESH_COMPLETE = 0;


    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                /*循环更新listview的内容*/
                case REFRESH_COMPLETE:
                    lv.setOnRefreshComplete();
                    if (temp.equals("菜")){
                        fetchCaipu("肉");
                        Toast.makeText(getActivity(),"肉类",Toast.LENGTH_SHORT).show();
                        temp = "肉";
                    }else if(temp.equals("肉")){
                        fetchCaipu("蛋");
                        Toast.makeText(getActivity(),"蛋类",Toast.LENGTH_SHORT).show();
                        temp = "蛋";
                    }else if(temp.equals("蛋")){
                        fetchCaipu("鱼");
                        Toast.makeText(getActivity(),"鱼类",Toast.LENGTH_SHORT).show();
                        temp = "鱼";
                    }else if(temp.equals("鱼")){
                        fetchCaipu("米");
                        Toast.makeText(getActivity(),"米",Toast.LENGTH_SHORT).show();
                        temp = "米";
                    }else if(temp.equals("米")){
                        fetchCaipu("水煮");
                        Toast.makeText(getActivity(),"水煮",Toast.LENGTH_SHORT).show();
                        temp = "水煮";
                    }else if(temp.equals("水煮")){
                        fetchCaipu("清蒸");
                        Toast.makeText(getActivity(),"清蒸",Toast.LENGTH_SHORT).show();
                        temp = "清蒸";
                    }else if(temp.equals("清蒸")){
                        fetchCaipu("菜");
                        Toast.makeText(getActivity(),"菜",Toast.LENGTH_SHORT).show();
                        temp = "菜";
                    }else {
                        fetchCaipu("菜");
                        temp="菜";
                    }
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

        EditText searchmenu = (EditText) getActivity().findViewById(R.id.search_menu);

        if (searchmenu.length()!=0){
            temp = searchmenu.getText().toString();
        }
        cp = new ArrayList<Caipu>();
        pDialog=new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();


        fetchCaipu(temp);
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
                menuName = cp.get(position-1).getName();
                mId = cp.get(position-1).getID();

                Intent intent = new Intent();
                intent.setClass(getActivity(),XiangqingPage.class);
                intent.putExtra("menuName",menuName);
                intent.putExtra("id",mId);
                startActivity(intent);
                //需链接到内容页
            }
        });
    }

    private void getView(View v){
        lv = (BaiDuRefreshListView)v.findViewById(R.id.lvBaiduRefresh);
    }

    public void setAdapter(){
        myadapter = new MenuAdapter(cp,getActivity());
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
                    cp.clear();

                    mHandler.sendEmptyMessage(REFRESH_COMPLETE);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /*
    *拉取菜谱列表
    * */
    private void fetchCaipu(final String caipuName) {

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
                hidePDialog();
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
