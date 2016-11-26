package ruanjian.xin.xiaocaidao.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.domain.Name1;


/**
 * Created by 你的账户 on 2016/11/24.
 */

public class Myadapter3 extends BaseAdapter {
    private Context context;
    private List<Name1> list = new ArrayList<>();


    public Myadapter3(Context context, List<Name1> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_item_3,null);
        }
        TextView textView = (TextView)convertView.findViewById(R.id.fragment_one_list_tv3);
        textView.setText(list.get(position).getName());
        TextView textView1 = (TextView)convertView.findViewById(R.id.fragment_one_list_tv3_1);
        textView1.setText(list.get(position).getName1());
        return convertView;
    }
}
