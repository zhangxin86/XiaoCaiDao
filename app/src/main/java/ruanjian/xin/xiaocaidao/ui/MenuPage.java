package ruanjian.xin.xiaocaidao.ui;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.zip.Inflater;

import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.adapter.BaiDuRefreshListView;
import ruanjian.xin.xiaocaidao.adapter.LvAdapter;
import ruanjian.xin.xiaocaidao.domain.Caipu;

public class MenuPage extends Fragment implements BaiDuRefreshListView.OnBaiduRefreshListener{

    private BaiDuRefreshListView lv;
    private ArrayList<Caipu> cp = new ArrayList<Caipu>();
    private LvAdapter myadapter;


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
        getView(v);
        getData();
        setAdapter();

        return v;


    }
    public void onClick(){
        //经典菜谱点击事件，进行页面跳转
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //需链接到内容页
            }
        });
    }

    private void getView(View v){
        lv = (BaiDuRefreshListView)v.findViewById(R.id.lv);
    }

    public void setAdapter(){
        myadapter = new LvAdapter(cp,getActivity());
        lv.setAdapter(myadapter);
        lv.setOnBaiduRefreshListener(this);
    }


    @Override
    public void onRefresh(){//刷新方法
        new Thread(     new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    cp.add(0,(new Caipu(0l,"茄汁莲藕排骨","排骨，莲藕，茄汁，盐","5000人赞过",R.drawable.ic)));
                    mHandler.sendEmptyMessage(REFRESH_COMPLETE);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void getData() {//数据样例
        cp.add(new Caipu(1l,"茄汁莲藕排骨","排骨，莲藕，茄汁，盐","5431人赞过",R.drawable.ic));
        cp.add(new Caipu(2l,"茄汁莲藕排骨","排骨，莲藕，茄汁，盐","5431人赞过",R.drawable.ic));
        cp.add(new Caipu(3l,"茄汁莲藕排骨","排骨，莲藕，茄汁，盐","5431人赞过",R.drawable.ic));
        cp.add(new Caipu(4l,"茄汁莲藕排骨","排骨，莲藕，茄汁，盐","5431人赞过",R.drawable.ic));
        cp.add(new Caipu(5l,"茄汁莲藕排骨","排骨，莲藕，茄汁，盐","5431人赞过",R.drawable.ic));
    }
}
