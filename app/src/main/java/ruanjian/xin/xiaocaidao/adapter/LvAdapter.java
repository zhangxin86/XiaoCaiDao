package ruanjian.xin.xiaocaidao.adapter;

/**
 * Created by Administrator on 2016/11/22.
 */
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.domain.Caipu;

public class LvAdapter extends BaseAdapter {
    private ArrayList<Caipu> caipu=new ArrayList<Caipu>();
    private Context context;

    public LvAdapter(ArrayList<Caipu> caipu, Context context) {
        this.caipu = caipu;
        this.context = context;
    }

    @Override
    public int getCount() {
        return caipu.size();

    }

    @Override
    public Object getItem(int position) {
        return caipu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.layout_list,null);

        ImageView img=(ImageView) view.findViewById(R.id.img);
        img.setImageResource(caipu.get(i).getImg());
        TextView TvName = (TextView) view.findViewById(R.id.TvName);
        TvName.setText(caipu.get(i).getName());
        TextView Mt= (TextView) view.findViewById(R.id.Mt);
        Mt.setText(caipu.get(i).getMaterial());
        TextView Total = (TextView) view.findViewById(R.id.dianzan);
        Total.setText(caipu.get(i).getTotal());

        return view;
    }
}