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
import ruanjian.xin.xiaocaidao.domain.Name2;


/**
 * Created by 你的账户 on 2016/11/24.
 */

public class Adapter_collect_post extends BaseAdapter {
    private Context context;
    private List<Name2> list = new ArrayList<>();

    public Adapter_collect_post(Context context, List<Name2> list) {
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_collect_post_item, null);
        }
        TextView textView=(TextView)convertView.findViewById(R.id.fragment_one_list_tv4);
        textView.setText(list.get(position).getName1());
        TextView textView1=(TextView)convertView.findViewById(R.id.fragment_one_list_tv4_1);
        textView1.setText(list.get(position).getName2());
        TextView textView2=(TextView)convertView.findViewById(R.id.fragment_one_list_tv4_2);
        textView2.setText(list.get(position).getName3());
        return convertView;
    }
}
