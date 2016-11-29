package ruanjian.xin.xiaocaidao.ui.Friend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.adapter.NewAdapter;
import ruanjian.xin.xiaocaidao.domain.Parts;

/**
 * Created by lhm on 2016/11/22.
 */

public class NewFriend extends Activity{
    private NewAdapter newAdapter;
    private ListView listView;
    private List<Parts> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        getData();
        findView();
        setAdapter();
        setListener();

    }

    private void setListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(NewFriend.this,NewFriendDetail.class);
                startActivity(i);
            }
        });
    }

    private void setAdapter() {
        newAdapter = new NewAdapter(this,data);
        listView.setAdapter(newAdapter);
    }

    private void findView() {
        listView = (ListView)findViewById(R.id.new_listView);
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
