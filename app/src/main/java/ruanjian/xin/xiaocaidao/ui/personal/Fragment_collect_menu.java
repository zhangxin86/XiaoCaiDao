package ruanjian.xin.xiaocaidao.ui.personal;


import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.adapter.BaiDuRefreshListView;
import ruanjian.xin.xiaocaidao.adapter.Myadapter3;
import ruanjian.xin.xiaocaidao.domain.Name1;


/**
 * Created by 你的账户 on 2016/11/23.
 */

public class Fragment_collect_menu extends Fragment implements BaiDuRefreshListView.OnBaiduRefreshListener {

    private Myadapter3 myadapter3;
    private List<Name1>list= new ArrayList<>();
    private BaiDuRefreshListView listView;
    private final static int REFRESH_COMPLETE = 0;
    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    listView.setOnRefreshComplete();
                    myadapter3.notifyDataSetChanged();
                    listView.setSelection(0);
                    break;
                default:
                    break;
            }
        };
    };
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_collect_menu, container, false);
        /*
        getdata();
        listView= (ListView) v.findViewById(R.id.fragment_two_list);

        myadapter3 = new Myadapter3(getActivity(),list);
        listView.setAdapter(myadapter3);
        return v;
        */
        getView(v);
        getData();
        setAdapter();
        return v;
    }
    private void getView(View v){
        listView= (BaiDuRefreshListView)v.findViewById(R.id.fragment_two_list);
    }

    public void setAdapter(){
        myadapter3=new Myadapter3(getActivity(),list);
        listView.setAdapter(myadapter3);
        listView.setOnBaiduRefreshListener(this);
    }


    @Override
    public void onRefresh(){//刷新方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    list.add(new Name1(0,"红烧狮子头","50人做过"));
                    mHandler.sendEmptyMessage(REFRESH_COMPLETE);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void getData(){
        list.add(new Name1(0,"红烧狮子头","50人做过"));
        list.add(new Name1(0,"红烧狮子头","50人做过"));
        list.add(new Name1(0,"红烧狮子头","50人做过"));
        list.add(new Name1(0,"红烧狮子头","50人做过"));
        list.add(new Name1(0,"红烧狮子头","50人做过"));
        list.add(new Name1(0,"红烧狮子头","50人做过"));
        list.add(new Name1(0,"红烧狮子头","50人做过"));
        list.add(new Name1(0,"红烧狮子头","50人做过"));
        list.add(new Name1(0,"红烧狮子头","50人做过"));
        list.add(new Name1(0,"红烧狮子头","50人做过"));
    }

}
