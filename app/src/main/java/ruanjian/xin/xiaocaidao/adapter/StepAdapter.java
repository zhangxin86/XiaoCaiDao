package ruanjian.xin.xiaocaidao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import ruanjian.xin.xiaocaidao.Controller.ApplicationController;
import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.domain.StepItem;

/**
 * Created by liyuxuan on 2016/11/27.
 */

public class StepAdapter extends BaseAdapter {
    private ArrayList<StepItem> steps = new ArrayList<StepItem>();
    private Context context;
    private LayoutInflater inflater;
    private ImageLoader imageLoader;

    private NetworkImageView stepsImage;
    private TextView tvSteps;

    public StepAdapter(){super();}

    public StepAdapter(Context context, ArrayList<StepItem> steps){
        this.steps = steps;
        this.context = context;
    }

    @Override
    public int getCount() {
        return steps.size();
    }

    @Override
    public Object getItem(int position) {
        return steps.get(position);
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
        if(convertView == null)
            convertView = inflater.inflate(R.layout.layout_stepitem,null);
        if (imageLoader == null)
            imageLoader = ApplicationController.getInstance().getImageLoader();

        tvSteps = (TextView)convertView.findViewById(R.id.tv_item_step);
        stepsImage = (NetworkImageView)convertView.findViewById(R.id.net_item_img);

        stepsImage.setImageUrl(steps.get(position).getStepImg(),imageLoader);
        tvSteps.setText(steps.get(position).getStepTitle());


        return convertView;
    }
}
