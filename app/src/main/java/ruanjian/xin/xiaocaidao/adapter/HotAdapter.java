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
import ruanjian.xin.xiaocaidao.domain.Parts;

/**
 * Created by lhm on 2016/11/22.
 */

public class HotAdapter extends BaseAdapter {

    private Context context;
    private List<Parts> data = new ArrayList<>();

    public HotAdapter(Context context, List<Parts> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).Id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if( convertView==null ){
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_comment,null);
        }
        return convertView;
    }
}
