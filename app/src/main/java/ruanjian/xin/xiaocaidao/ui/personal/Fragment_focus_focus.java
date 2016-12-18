package ruanjian.xin.xiaocaidao.ui.personal;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import ruanjian.xin.xiaocaidao.Controller.ApplicationController;
import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.adapter.Adapter_focus_fans;
import ruanjian.xin.xiaocaidao.adapter.BaiDuRefreshListView;
import ruanjian.xin.xiaocaidao.domain.Name;
import ruanjian.xin.xiaocaidao.utils.Calculator;
import ruanjian.xin.xiaocaidao.utils.HttpUtil;
import ruanjian.xin.xiaocaidao.utils.Utils;

/**
 * 选项：我的关注
 * 子选项：关注
 * Created by 你的账户 on 2016/11/23.
 */

public class Fragment_focus_focus extends Fragment implements BaiDuRefreshListView.OnBaiduRefreshListener {
    private Adapter_focus_fans myadapter1;
    private List<Name> list = new ArrayList<Name>();
    private BaiDuRefreshListView listView;


    private final static int REFRESH_COMPLETE = 0;
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    listView.setOnRefreshComplete();
                    myadapter1.notifyDataSetChanged();
                    listView.setSelection(0);
                    break;
                default:
                    break;
            }
        }

        ;
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_focus_fans, container, false);
        getView(v);
        getData();
        setAdapter();
        return v;
    }

    private void getView(View v) {
        listView = (BaiDuRefreshListView) v.findViewById(R.id.fragment_one_list);
    }

    public void setAdapter() {
        myadapter1 = new Adapter_focus_fans(getActivity(), list);
        listView.setAdapter(myadapter1);
        listView.setOnBaiduRefreshListener(this);
    }


    @Override
    public void onRefresh() {//刷新方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    //list.add(new Name(0,"小妖精"));
                    mHandler.sendEmptyMessage(REFRESH_COMPLETE);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void getData() {
        list.clear();
        StringRequest req = new StringRequest(Request.Method.POST, Utils.URL+"fans/fetchFollowsList", new Response.Listener<String>() {

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

                System.out.println("fansList:" + jsonArray.toString());

                try {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);

                        String ComUsername = obj.getString("userName");
                        String ComUserImg = obj.getString("userImg");

                        Name commentItem = new Name(ComUserImg, ComUsername);
                        System.out.println("obj.toString:::" + obj.toString());

                        list.add(commentItem);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                myadapter1.notifyDataSetChanged();
                Calculator.setListViewHeightBasedOnChildren(listView, 0);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("CaipuMenuPge", "error:" + volleyError.getMessage());
                //hidePDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("account", HttpUtil.uac);
                return map;
            }
        };
        ApplicationController.getInstance().addToRequestQueue(req);
    }
}