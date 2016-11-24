package ruanjian.xin.xiaocaidao.ui.personal;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.adapter.Myadapter4;
import ruanjian.xin.xiaocaidao.adapter.Myadapter5;
import ruanjian.xin.xiaocaidao.domain.Name2;


/**
 * Created by 你的账户 on 2016/11/23.
 */

public class Fragment_three1 extends Fragment {
    private Myadapter5 myadapter5;
    private ListView listView;
    private List<Name2> list = new ArrayList<Name2>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_three1,container,false);
        getData();
        listView= (ListView)v.findViewById(R.id.fragment_three_list);
        myadapter5 = new Myadapter5(getActivity(),list);
        listView.setAdapter(myadapter5);
        return v;
    }
    private void getData(){
        list.add(new Name2(0,"超好的的丸子","爆浆######","评价 55"));
        list.add(new Name2(0,"超好的的丸子","爆浆######","评价 55"));
        list.add(new Name2(0,"超好的的丸子","爆浆######","评价 55"));
        list.add(new Name2(0,"超好的的丸子","爆浆######","评价 55"));
        list.add(new Name2(0,"超好的的丸子","爆浆######","评价 55"));
        list.add(new Name2(0,"超好的的丸子","爆浆######","评价 55"));
        list.add(new Name2(0,"超好的的丸子","爆浆######","评价 55"));

        list.add(new Name2(0,"超好的的丸子","爆浆######","评价 55"));

    }
}
