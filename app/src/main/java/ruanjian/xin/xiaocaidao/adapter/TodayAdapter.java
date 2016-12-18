package ruanjian.xin.xiaocaidao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

import ruanjian.xin.xiaocaidao.Controller.ApplicationController;
import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.domain.Parts;
import ruanjian.xin.xiaocaidao.domain.TodayItem;

/**
 * Created by xin on 2016/11/17.
 */

public class TodayAdapter extends BaseAdapter{
    private Context context;
    private List<TodayItem> data = new ArrayList<>();

    private ImageView menuImg;
    private TextView TvmenuName;
    private TextView TvmenuDes;

    private ImageLoader imageLoader;
    private LayoutInflater inflater;

    public TodayAdapter(Context context, List<TodayItem> data) {
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (imageLoader == null)
            imageLoader = ApplicationController.getInstance().getImageLoader();
        if( convertView==null ){
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_today,null);
        }
        menuImg = (ImageView)convertView.findViewById(R.id.menuImg);
        TvmenuName = (TextView)convertView.findViewById(R.id.TvmenuName);
        TvmenuDes = (TextView)convertView.findViewById(R.id.TvmenuDes);

        menuImg.setImageResource(data.get(position).getMenuImg());
        TvmenuName.setText(data.get(position).getMenuName());
        TvmenuDes.setText(data.get(position).getMenuDes());


        return convertView;
    }
}
