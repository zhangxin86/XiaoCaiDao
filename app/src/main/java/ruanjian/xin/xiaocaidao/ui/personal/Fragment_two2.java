package ruanjian.xin.xiaocaidao.adapter;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;




import java.util.ArrayList;
import java.util.List;

import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.domain.Name;
import ruanjian.xin.xiaocaidao.domain.Name2;

/**
 * Created by 你的账户 on 2016/11/23.
 */

public class Fragment_two2 extends Fragment {
    private Myadapter4 myadapter4;
    private List<Name2> list = new ArrayList<>();
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_two2,container,false);
        getData();
        listView = (ListView)v.findViewById(R.id.fragment_two_list);
        myadapter4 = new Myadapter4(getActivity(),list);
        listView.setAdapter(myadapter4);
        return  v;
    }
    private void getData(){
        list.add(new Name2(0,"红烧狮子头","值得一试####","评价 55"));
        list.add(new Name2(0,"红烧狮子头","值得一试####","评价 55"));
        list.add(new Name2(0,"红烧狮子头","值得一试####","评价 55"));
        list.add(new Name2(0,"红烧狮子头","值得一试####","评价 55"));
        list.add(new Name2(0,"红烧狮子头","值得一试####","评价 55"));
        list.add(new Name2(0,"红烧狮子头","值得一试####","评价 55"));
        list.add(new Name2(0,"红烧狮子头","值得一试####","评价 55"));
        list.add(new Name2(0,"红烧狮子头","值得一试####","评价 55"));
        list.add(new Name2(0,"红烧狮子头","值得一试####","评价 55"));
        list.add(new Name2(0,"红烧狮子头","值得一试####","评价 55"));
        list.add(new Name2(0,"红烧狮子头","值得一试####","评价 55"));
        list.add(new Name2(0,"红烧狮子头","值得一试####","评价 55"));
    }
}
