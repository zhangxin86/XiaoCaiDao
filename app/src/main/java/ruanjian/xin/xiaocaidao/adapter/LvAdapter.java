package ruanjian.xin.xiaocaidao.adapter;

/**
 * Created by Administrator on 2016/11/22.
 */
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import ruanjian.xin.xiaocaidao.Controller.ApplicationController;
import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.domain.Caipu;

public class LvAdapter extends BaseAdapter {
    private ArrayList<Caipu> caipus=new ArrayList<Caipu>();
    private Context context;
    private LayoutInflater inflater;
    private ImageLoader imageLoader;

    private NetworkImageView caipuImage;
    private TextView caipuName,caipuCailiao,movieRating;

    public LvAdapter(){
        super();
    }

    public LvAdapter(ArrayList<Caipu> caipus, Context context) {
        this.caipus = caipus;
        this.context = context;
    }

    @Override
    public int getCount() {
        return caipus.size();

    }

    @Override
    public Object getItem(int position) {
        return caipus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view == null)
            view = inflater.inflate(R.layout.lv_back01,null);
        if (imageLoader == null)
            imageLoader = ApplicationController.getInstance().getImageLoader();

        caipuName = (TextView)view.findViewById(R.id.tvcaipuName);
        caipuCailiao = (TextView)view.findViewById(R.id.tvCailiao);
        caipuImage = (NetworkImageView)view.findViewById(R.id.netimg);

        caipuImage.setImageUrl(caipus.get(i).getImgURL(),imageLoader);
        caipuCailiao.setText("材料："+(CharSequence)caipus.get(i).getMaterial());
        caipuName.setText((CharSequence)caipus.get(i).getName());

        return view;

    }
}