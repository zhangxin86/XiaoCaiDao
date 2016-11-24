package ruanjian.xin.xiaocaidao.adapter;



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

import ruanjian.xin.xiaocaidao.domain.Name1;


/**
 * Created by 你的账户 on 2016/11/23.
 */

public class Fragment_two1 extends Fragment {

    private Myadapter3 myadapter3;
    private List<Name1>list= new ArrayList<>();
    private ListView listView;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_two1, container, false);
        getdata();
        listView= (ListView) v.findViewById(R.id.fragment_two_list);

        myadapter3 = new Myadapter3(getActivity(),list);
        listView.setAdapter(myadapter3);
        return v;
    }
    private void getdata(){
        list.add(new Name1(0,"红烧狮子头","50人做过"));
        list.add(new Name1(0,"红烧狮子头","50人做过"));
        list.add(new Name1(0,"红烧狮子头","50人做过"));
        list.add(new Name1(0,"红烧狮子头","50人做过"));
        list.add(new Name1(0,"红烧狮子头","50人做过"));
        list.add(new Name1(0,"红烧狮子头","50人做过"));
        list.add(new Name1(0,"红烧狮子头","50人做过"));
        list.add(new Name1(0,"红烧狮子头","50人做过"));
        list.add(new Name1(0,"红烧狮子头","50人做过"));
        list.add(new Name1(0,"红烧狮子头","50人做过"));
    }

}
