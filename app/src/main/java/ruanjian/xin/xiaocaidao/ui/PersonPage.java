package ruanjian.xin.xiaocaidao.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.ui.personal.Person_fouse;
import ruanjian.xin.xiaocaidao.ui.personal.Person_collect;
import ruanjian.xin.xiaocaidao.ui.personal.Person_post;
import ruanjian.xin.xiaocaidao.ui.personal.Person_setting;

public class PersonPage extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_person_page,container,false);
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;

            tv1 = (TextView)v.findViewById(R.id.activity_tv1);
            tv2 = (TextView)v.findViewById(R.id.activity_tv2);
            tv3 = (TextView)v.findViewById(R.id.activity_tv3);
            tv4 = (TextView)v.findViewById(R.id.activity_tv4);

            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.activity_tv1:
                            Intent intent = new Intent();
                            intent.setClass(getActivity(),Person_fouse.class);
                            startActivity(intent);
                            break;

                        case R.id.activity_tv2:
                            Intent intent1 = new Intent();
                            intent1.setClass(getActivity(),Person_collect.class);
                            startActivity(intent1);
                            break;
                        case R.id.activity_tv3:
                            Intent intent2 = new Intent();
                            intent2.setClass(getActivity(),Person_post.class);
                            startActivity(intent2);
                            break;

                        case R.id.activity_tv4:
                            Intent intent3 = new Intent();
                            intent3.setClass(getActivity(),Person_setting.class);
                            startActivity(intent3);
                            break;

                        default:
                    }
                }
            };
            tv1.setOnClickListener(listener);
            tv2.setOnClickListener(listener);
            tv3.setOnClickListener(listener);
            tv4.setOnClickListener(listener);
        return v;
    }
}
