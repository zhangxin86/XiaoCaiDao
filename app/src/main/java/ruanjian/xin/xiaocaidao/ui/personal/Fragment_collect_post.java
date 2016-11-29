package ruanjian.xin.xiaocaidao.adapter;

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
import ruanjian.xin.xiaocaidao.domain.Name2;

/**
 * Created by 你的账户 on 2016/11/23.
 */

public class Fragment_collect_post extends Fragment implements  BaiDuRefreshListView.OnBaiduRefreshListener{
    private Myadapter4 myadapter4;
    private List<Name2> list = new ArrayList<>();
    private BaiDuRefreshListView listView;

    private final static int REFRESH_COMPLETE = 0;
    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    listView.setOnRefreshComplete();
                    myadapter4.notifyDataSetChanged();
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
        View v= inflater.inflate(R.layout.fragment_collect_post,container,false);
        /*
        getData();
        listView = (ListView)v.findViewById(R.id.fragment_two_list);
        myadapter4 = new Myadapter4(getActivity(),list);
        listView.setAdapter(myadapter4);
        return  v;
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
        myadapter4 =new Myadapter4(getActivity(),list);
        listView.setAdapter(myadapter4);
        listView.setOnBaiduRefreshListener(this);
    }


    @Override
    public void onRefresh(){//刷新方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    list.add(new Name2(0,"红烧狮子头","值得一试####","评价 55"));
                    mHandler.sendEmptyMessage(REFRESH_COMPLETE);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void getData(){
        list.add(new Name2(0,"红烧狮子头","值得一试####","评价 55"));
        list.add(new Name2(0,"红烧狮子头","值得一试####","评价 55"));
        list.add(new Name2(0,"红烧狮子头","值得一试####","评价 55"));
        list.add(new Name2(0,"红烧狮子头","值得一试####","评价 55"));
        list.add(new Name2(0,"红烧狮子头","值得一试####","评价 55"));
        list.add(new Name2(0,"红烧狮子头","值得一试####","评价 55"));
        list.add(new Name2(0,"红烧狮子头","值得一试####","评价 55"));
        list.add(new Name2(0,"红烧狮子头","值得一试####","评价 55"));
        list.add(new Name2(0,"红烧狮子头","值得一试####","评价 55"));
        list.add(new Name2(0,"红烧狮子头","值得一试####","评价 55"));
        list.add(new Name2(0,"红烧狮子头","值得一试####","评价 55"));
        list.add(new Name2(0,"红烧狮子头","值得一试####","评价 55"));
    }
}
