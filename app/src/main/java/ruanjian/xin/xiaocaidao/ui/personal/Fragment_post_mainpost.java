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
import ruanjian.xin.xiaocaidao.adapter.Myadapter5;
import ruanjian.xin.xiaocaidao.domain.Name2;


/**
 * Created by 你的账户 on 2016/11/23.
 */

public class Fragment_post_mainpost extends Fragment implements BaiDuRefreshListView.OnBaiduRefreshListener{
    private Myadapter5 myadapter5;
    private BaiDuRefreshListView listView;
    private List<Name2> list = new ArrayList<Name2>();


    private final static int REFRESH_COMPLETE = 0;
    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    listView.setOnRefreshComplete();
                    myadapter5.notifyDataSetChanged();
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
        View v=inflater.inflate(R.layout.fragment_post_mainpost,container,false);
        getView(v);
        getData();
        setAdapter();
        return v;
        /*
        getData();
        listView= (ListView)v.findViewById(R.id.fragment_three_list);
        myadapter5 = new Myadapter5(getActivity(),list);
        listView.setAdapter(myadapter5);
        return v;
        */
    }
    private void getView(View v){
        listView = (BaiDuRefreshListView)v.findViewById(R.id.fragment_three_list);
    }
    private void setAdapter(){
        myadapter5 = new Myadapter5(getActivity(),list);
        listView.setAdapter(myadapter5);
        listView.setOnBaiduRefreshListener(this);
    }
    public void onRefresh(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);
                    list.add(new Name2(0,"超好的的丸子","爆浆######","评价 55"));
                    mHandler.sendEmptyMessage(REFRESH_COMPLETE);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void getData(){
        list.add(new Name2(0,"超好的的丸子","爆浆######","评价 55"));
        list.add(new Name2(0,"超好的的丸子","爆浆######","评价 55"));
        list.add(new Name2(0,"超好的的丸子","爆浆######","评价 55"));
        list.add(new Name2(0,"超好的的丸子","爆浆######","评价 55"));
        list.add(new Name2(0,"超好的的丸子","爆浆######","评价 55"));
        list.add(new Name2(0,"超好的的丸子","爆浆######","评价 55"));
        list.add(new Name2(0,"超好的的丸子","爆浆######","评价 55"));

        list.add(new Name2(0,"超好的的丸子","爆浆######","评价 55"));

    }
}
