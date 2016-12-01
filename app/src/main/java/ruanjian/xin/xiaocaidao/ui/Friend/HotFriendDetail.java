package ruanjian.xin.xiaocaidao.ui.Friend;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.adapter.FriendCommentAdapter;
import ruanjian.xin.xiaocaidao.domain.Parts;
import ruanjian.xin.xiaocaidao.utils.Calculator;

public class HotFriendDetail extends Fragment {

    private FriendCommentAdapter commentAdapter;
    private ListView Lv_comment;
    private List<Parts> data = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_hot_item_detail,container,false);

        getData();
        getView(v);
        setComment();
        return v;
    }

    private void getView(View v) {
        Lv_comment = (ListView)v.findViewById(R.id.comment_listView);
    }

    private void setComment() {
        commentAdapter = new FriendCommentAdapter(getActivity(),data);
        Lv_comment.setAdapter(commentAdapter);
        Calculator.setListViewHeightBasedOnChildren(Lv_comment,0);
    }

    private void getData(){
        data.add(new Parts());
        data.add(new Parts());
        data.add(new Parts());
    }
}
