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
import ruanjian.xin.xiaocaidao.domain.Name;

/**
 * Created by 你的账户 on 2016/11/23.
 */

public class Adapter_focus_focus extends BaseAdapter {
    private Context context;
    private List<Name> list = new ArrayList<>();

    public Adapter_focus_focus(Context context, List<Name> list) {
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_focus_focus_item,null);
        }
        TextView textView =(TextView)convertView.findViewById(R.id.fragment_one_list_tv2);
        textView.setText(list.get(position).getName());
        return convertView;
    }
}
