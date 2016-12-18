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
import ruanjian.xin.xiaocaidao.domain.CommentItem;
import ruanjian.xin.xiaocaidao.domain.Parts;

/**
 * Created by lhm on 2016/11/28.
 */

public class FriendCommentAdapter extends BaseAdapter{
    private Context context;
    private List<CommentItem> data = new ArrayList<>();

    private CircleNetworkImage avatarImg;       //头像
    private TextView TvuserName;        //用户名
    private TextView TvContent;         //评论内容

    private LayoutInflater inflater;
    private ImageLoader imageLoader;

    public FriendCommentAdapter(Context context, List<CommentItem> data) {
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
        if( convertView==null ){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_comment_item,null);
        }
        if (inflater == null)
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (imageLoader == null)
            imageLoader = ApplicationController.getInstance().getImageLoader();

        avatarImg = (CircleNetworkImage)convertView.findViewById(R.id.cv_com_avater);
        TvuserName = (TextView)convertView.findViewById(R.id.tv_com_name);
        TvContent = (TextView)convertView.findViewById(R.id.tv_com_content);

        System.out.println("用户头像信息获取："+data.get(position).getAvatarUrl());
        avatarImg.setImageUrl(data.get(position).getAvatarUrl(),imageLoader);
        TvuserName.setText(data.get(position).getUserName());
        TvContent.setText(data.get(position).getContent());

        return convertView;
    }
}
