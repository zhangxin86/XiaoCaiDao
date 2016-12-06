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
import ruanjian.xin.xiaocaidao.adapter.Adapter_post_repost;
import ruanjian.xin.xiaocaidao.domain.Name2;


/**
 * Created by 你的账户 on 2016/11/23.
 */

public class Fragment_post_repost extends Fragment implements BaiDuRefreshListView.OnBaiduRefreshListener {
    private List<Name2> list = new ArrayList<>();
    private BaiDuRefreshListView listView;
    private Adapter_post_repost adapterpostrepost;

    private final static int REFRESH_COMPLETE = 0;
    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    listView.setOnRefreshComplete();
                    adapterpostrepost.notifyDataSetChanged();
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
        View v=  inflater.inflate(R.layout.fragment_post_repost,container,false);
        /*
        getData();
        listView = (ListView)v.findViewById(R.id.fragment_three_list);
        adapterpostrepost= new Adapter_post_repost(getActivity(),list);
        listView.setAdapter(adapterpostrepost);
        return  v;
        */
        getView(v);
        getData();
        setAdapter();
        return  v;
    }
    private void getView(View v){
        listView=(BaiDuRefreshListView)v.findViewById(R.id.fragment_three_list);
    }
    private void setAdapter(){
        adapterpostrepost = new Adapter_post_repost(getActivity(),list);
        listView.setAdapter(adapterpostrepost);
        listView.setOnBaiduRefreshListener(this);
    }
    public void onRefresh(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    list.add(new Name2(0,"楼主好人啊","keyi试试","55"));
                    mHandler.sendEmptyMessage(REFRESH_COMPLETE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void getData(){
        list.add(new Name2(0,"楼主好人啊","keyi试试","55"));
        list.add(new Name2(0,"楼主好人啊","keyi试试","55"));
        list.add(new Name2(0,"楼主好人啊","keyi试试","55"));
        list.add(new Name2(0,"楼主好人啊","keyi试试","55"));
        list.add(new Name2(0,"楼主好人啊","keyi试试","55"));
        list.add(new Name2(0,"楼主好人啊","keyi试试","55"));
        list.add(new Name2(0,"楼主好人啊","keyi试试","55"));
        list.add(new Name2(0,"楼主好人啊","keyi试试","55"));
        list.add(new Name2(0,"楼主好人啊","keyi试试","55"));
        list.add(new Name2(0,"楼主好人啊","keyi试试","55"));
    }
}
