package ruanjian.xin.xiaocaidao.ui.Friend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ruanjian.xin.xiaocaidao.Client_hot_detail;
import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.adapter.HotAdapter;
import ruanjian.xin.xiaocaidao.domain.Parts;

/**
 * Created by lhm on 2016/11/22.
 */

public class HotFriend extends Activity {    //热门帖子

    private HotAdapter hotAdapter;
    private ListView listView;
    private List<Parts> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot);

        getData();
        findView();
        setAdapter();
        setListener();

    }

    private void setListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(HotFriend.this,Client_hot_detail.class);
                startActivity(i);
            }
        });
    }

    private void setAdapter() {
        hotAdapter = new HotAdapter(this,data);
        listView.setAdapter(hotAdapter);
    }

    private void findView() {
        listView = (ListView)findViewById(R.id.hot_listView);
    }


    private void getData() {
        data.add(new Parts());
        data.add(new Parts());
        data.add(new Parts());
        data.add(new Parts());
        data.add(new Parts());
        data.add(new Parts());
        data.add(new Parts());
        data.add(new Parts());
        data.add(new Parts());
    }
}
