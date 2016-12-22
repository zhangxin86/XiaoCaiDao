package ruanjian.xin.xiaocaidao.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import ruanjian.xin.xiaocaidao.Controller.ApplicationController;
import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.domain.BlogItem;
import ruanjian.xin.xiaocaidao.domain.CircleNetworkImage;
import ruanjian.xin.xiaocaidao.domain.Parts;

/**
 * Created by xin on 2016/11/17.
 */

public class CommentAdapter extends BaseAdapter {
    private Context context;
    private List<BlogItem> data = new ArrayList<>();
    private CircleNetworkImage avatarImg;//头像
    private NetworkImageView blogImg;//图片
    private TextView Tvaccount;//用户名
    private TextView Lab1;
    private TextView Lab2;
    private TextView Tvthumb;//点赞数
    private TextView Tvcomment;//评论数
    private TextView Tvcontent;//内容

    private LayoutInflater inflater;
    private ImageLoader imageLoader;

    public CommentAdapter(Context context, List<BlogItem> data) {
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
        return data.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null)
            convertView = inflater.inflate(R.layout.lv_comment,null);
        if (imageLoader == null)
            imageLoader = ApplicationController.getInstance().getImageLoader();

        avatarImg = (CircleNetworkImage)convertView.findViewById(R.id.cv_avatar);//须要再申请
        blogImg = (NetworkImageView) convertView.findViewById(R.id.Iv_blogimg);
        Tvaccount = (TextView)convertView.findViewById(R.id.Tvaccount);//须要再申请

        Lab1 = (TextView)convertView.findViewById(R.id.Tv_Lab1);
        Lab2 = (TextView)convertView.findViewById(R.id.Tv_Lab2);
        Tvcontent = (TextView)convertView.findViewById(R.id.Tv_content);
        Tvthumb = (TextView)convertView.findViewById(R.id.Tv_thumb);
        Tvcomment = (TextView)convertView.findViewById(R.id.Tvcomment);

        Tvaccount.setText(data.get(position).getAccount());
        Lab1.setText(data.get(position).getLab1());             //直接显示Lab1
        Lab2.setText(data.get(position).getLab2());             //直接显示Lab2
        Tvcontent.setText(data.get(position).getCountent());        //直接显示内容
        Tvthumb.setText(""+data.get(position).getThumb());      //直接显示点赞数
        Tvcomment.setText("");

        blogImg.setImageUrl(data.get(position).getBlogImg(),imageLoader);
        avatarImg.setImageUrl(data.get(position).getAvatarUrl(),imageLoader);
        RequestParams params2 = new RequestParams();
        params2.add("account",data.get(position).getAccount());
        RequestParams params = new RequestParams();
        params.add("account",data.get(position).getAccount());
        return convertView;
    }
}
