package ruanjian.xin.xiaocaidao.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.ui.personal.Person_about;
import ruanjian.xin.xiaocaidao.ui.personal.Person_collect;
import ruanjian.xin.xiaocaidao.ui.personal.Person_fouse;
import ruanjian.xin.xiaocaidao.ui.personal.Person_post;
import ruanjian.xin.xiaocaidao.ui.personal.Person_setting;

public class PersonPage extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_person_page,container,false);
        LinearLayout tv1;
        LinearLayout tv2;
        LinearLayout tv3;
        LinearLayout tv4;
        LinearLayout tv5;
            tv1 = (LinearLayout)v.findViewById(R.id.activity_tv1);
            tv2 = (LinearLayout)v.findViewById(R.id.activity_tv2);
            tv3 = (LinearLayout)v.findViewById(R.id.activity_tv3);
            tv4 = (LinearLayout)v.findViewById(R.id.activity_tv4);
            tv5 = (LinearLayout)v.findViewById(R.id.activity_tv5);
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
                        case R.id.activity_tv5:
                            Intent intent4 = new Intent();
                            intent4.setClass(getActivity(),Person_about.class);
                            startActivity(intent4);
                            break;
                        default:
                            break;
                    }
                }
            };
            tv1.setOnClickListener(listener);
            tv2.setOnClickListener(listener);
            tv3.setOnClickListener(listener);
            tv4.setOnClickListener(listener);
            tv5.setOnClickListener(listener);
        return v;
    }
}
