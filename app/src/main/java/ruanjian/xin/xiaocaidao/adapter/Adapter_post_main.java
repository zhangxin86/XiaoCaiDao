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
import ruanjian.xin.xiaocaidao.domain.BlogItem;
import ruanjian.xin.xiaocaidao.domain.Name2;


/**
 * Created by 你的账户 on 2016/11/24.
 */

public class Adapter_post_main extends BaseAdapter {
    private Context context;
    private List<BlogItem> list = new ArrayList<>();

    private TextView TvTitle;
    private TextView TvContent;
    private TextView TvThumb;
    private TextView TvLab1;
    private TextView TvLab2;

    public Adapter_post_main(Context context, List<BlogItem> list) {
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_post_main_item, null);
        }

        TvTitle=(TextView)convertView.findViewById(R.id.tv_blogtitle);
        TvContent = (TextView)convertView.findViewById(R.id.tv_blogcontent);
        TvThumb = (TextView)convertView.findViewById(R.id.tv_blogthumb);
        TvLab1 = (TextView)convertView.findViewById(R.id.tv_lab1);
        TvLab2 = (TextView)convertView.findViewById(R.id.tv_lab2);

        TvTitle.setText(list.get(position).getTitle());
        TvContent.setText(list.get(position).getCountent());
        TvLab1.setText(list.get(position).getLab1());
        TvLab2.setText(list.get(position).getLab2());
        TvThumb.setText(list.get(position).getThumb()+"");

        return convertView;
    }
}
