package ruanjian.xin.xiaocaidao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.domain.Parts;
import ruanjian.xin.xiaocaidao.utils.SpicyUtil;

/**
 * Created by xin on 2016/11/17.
 */

public class SpicyAdapter extends BaseAdapter {
    private Context context;
    private List<Parts> data = new ArrayList<>();
    private SpicyUtil spicyUtil = new SpicyUtil();

    public SpicyAdapter(Context context, List<Parts> data) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.gv_spicy,null);
        }
        CircleImageView spicypic = (CircleImageView)convertView.findViewById(R.id.spicypic);
        TextView spicyname = (TextView)convertView.findViewById(R.id.spicyname);
        int type = position + 1;
        spicyname.setText(spicyUtil.getName(type));
        spicypic.setImageResource(spicyUtil.getImage(type));

        return convertView;
    }
}
