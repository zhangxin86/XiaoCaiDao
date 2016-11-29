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
import ruanjian.xin.xiaocaidao.adapter.Myadapter2;
import ruanjian.xin.xiaocaidao.domain.Name;

/**
 * Created by 你的账户 on 2016/11/23.
 */

public class Fragment_focus_focus extends Fragment implements BaiDuRefreshListView.OnBaiduRefreshListener {
    private BaiDuRefreshListView listView;
    private Myadapter2 myadapter2;
    private List<Name> list = new ArrayList<>();

    private final static int REFRESH_COMPLETE = 0;
    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    listView.setOnRefreshComplete();
                    myadapter2.notifyDataSetChanged();
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
        View v = inflater.inflate(R.layout.fragment_focus_focus, container, false);
        getView(v);
        getData();
        setAdapter();
        return v;


        /*
        getData();
        listView = (ListView) v.findViewById(R.id.fragment_one_list);
        myadapter2 = new Myadapter2(getActivity(), list);
        listView.setAdapter(myadapter2);
        return v;
        */
    }
    private void getView(View v){
        listView= (BaiDuRefreshListView)v.findViewById(R.id.fragment_one_list);
    }

    public void setAdapter(){
        myadapter2 =new Myadapter2(getActivity(),list);
        listView.setAdapter(myadapter2);
        listView.setOnBaiduRefreshListener(this);
    }


    @Override
    public void onRefresh(){//刷新方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    list.add(new Name(0,"小妖精"));
                    mHandler.sendEmptyMessage(REFRESH_COMPLETE);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void getData() {
        list.add(new Name(0, "小妖精"));
        list.add(new Name(0, "小妖精"));
        list.add(new Name(0, "小妖精"));
        list.add(new Name(0, "小妖精"));
        list.add(new Name(0, "小妖精"));
        list.add(new Name(0, "小妖精"));
        list.add(new Name(0, "小妖精"));
        list.add(new Name(0, "小妖精"));
        list.add(new Name(0, "小妖精"));
        list.add(new Name(0, "小妖精"));
        list.add(new Name(0, "小妖精"));
        list.add(new Name(0, "小妖精"));
    }
}
