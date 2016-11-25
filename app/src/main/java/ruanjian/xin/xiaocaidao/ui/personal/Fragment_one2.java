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
import ruanjian.xin.xiaocaidao.adapter.Myadapter2;
import ruanjian.xin.xiaocaidao.domain.Name;

/**
 * Created by 你的账户 on 2016/11/23.
 */

public class Fragment_one2 extends Fragment {
    private ListView listView;
    private Myadapter2 myadapter2;
    private List<Name> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_one2, container, false);
        getData();
        listView = (ListView) v.findViewById(R.id.fragment_one_list);
        myadapter2 = new Myadapter2(getActivity(), list);
        listView.setAdapter(myadapter2);
        return v;
    }

    private void getData() {
        list.add(new Name(0, "小妖精"));
        list.add(new Name(0, "小妖精"));
        list.add(new Name(0, "小妖精"));
        list.add(new Name(0, "小妖精"));
        list.add(new Name(0, "小妖精"));
        list.add(new Name(0, "小妖精"));
        list.add(new Name(0, "小妖精"));
        list.add(new Name(0, "小妖精"));
        list.add(new Name(0, "小妖精"));
        list.add(new Name(0, "小妖精"));
        list.add(new Name(0, "小妖精"));
        list.add(new Name(0, "小妖精"));
    }
}
