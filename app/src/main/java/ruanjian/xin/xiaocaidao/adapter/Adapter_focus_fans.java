package ruanjian.xin.xiaocaidao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import ruanjian.xin.xiaocaidao.Controller.ApplicationController;
import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.domain.CircleNetworkImage;
import ruanjian.xin.xiaocaidao.domain.Name;


/**
 * Created by 你的账户 on 2016/11/24.
 */

public class Adapter_focus_fans extends BaseAdapter {
    private Context context;
    private List<Name> list = new ArrayList<>();

    private TextView TvUserName;
    private CircleNetworkImage avatarImg;

    private LayoutInflater inflater;
    private ImageLoader imageLoader;

    public Adapter_focus_fans(Context context, List<Name> list) {
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

        if (inflater == null)
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (imageLoader == null)
            imageLoader = ApplicationController.getInstance().getImageLoader();
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_focus_fans_item,null);
        }

        TvUserName = (TextView)convertView.findViewById(R.id.fragment_one_list_tv1);
        avatarImg = (CircleNetworkImage) convertView.findViewById(R.id.cv_fans_avater);

        TvUserName.setText(list.get(position).getName());
        avatarImg.setImageUrl(list.get(position).getAvatarUrl(),imageLoader);

        return convertView;
    }
}
