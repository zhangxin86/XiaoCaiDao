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

/**
 * Created by 你的账户 on 2016/11/24.
 */

public class Fragment_one1 extends Fragment {
    private Myadapter1 myadapter1;
    private List<Name> list = new ArrayList<Name>();
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_one1,container,false);
        //listView = (ListView)container.findViewById(R.id.fragment_one_list);
        listView =(ListView) v.findViewById(R.id.fragment_one_list);
        getData();
        myadapter1 =new Myadapter1(getActivity(),list);
        if(listView==null) Log.i("tag","list null");
        if(myadapter1==null) Log.i("tag","null");
        listView.setAdapter(myadapter1);
        return v;
    }


    private void getData() {
        list.add(new Name(0,"小妖精"));
        list.add(new Name(0,"小妖精"));
        list.add(new Name(0,"小妖精"));
        list.add(new Name(0,"小妖精"));
        list.add(new Name(0,"小妖精"));
        list.add(new Name(0,"小妖精"));
        list.add(new Name(0,"小妖精"));
        list.add(new Name(0,"小妖精"));
        list.add(new Name(0,"小妖精"));
        list.add(new Name(0,"小妖精"));
        list.add(new Name(0,"小妖精"));
        list.add(new Name(0,"小妖精"));

    }
}