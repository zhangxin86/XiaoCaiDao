package ruanjian.xin.xiaocaidao.ui.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.adapter.MenuAdapter;
import ruanjian.xin.xiaocaidao.domain.Caipu;
import ruanjian.xin.xiaocaidao.ui.XiangqingPage;

public class ThreeMeals extends AppCompatActivity {

    private MenuAdapter myadapter;
    private ArrayList<Caipu> cp;
    private Object data;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_meals);

        getData();
        findView();
        setAdapter();
        setListener();

    }

    private void setListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(ThreeMeals.this,XiangqingPage.class);
                startActivity(intent);
            }
        });
    }

    private void setAdapter() {
        myadapter = new MenuAdapter(cp,this);
        listView.setAdapter(myadapter);
    }

    private void findView() {
        listView = (ListView)findViewById(R.id.three_meals_listview);
    }

    public void getData() {
        Caipu caipu = new Caipu();
        cp.add(caipu);
    }
}
