package ruanjian.xin.xiaocaidao.ui;

import android.app.Fragment;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TabHost;

import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.ui.Friend.BlogSubmitActivity;
import ruanjian.xin.xiaocaidao.ui.Friend.HotFriend;
import ruanjian.xin.xiaocaidao.ui.Friend.NewFriend;

public class FriendPage extends Fragment {
    private View v;
    private LocalActivityManager manager;
    private TabHost tabHost;
    private ImageView imageView;
    private RadioGroup radioderGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_friend_page,container,false);

        manager = new LocalActivityManager(getActivity(),true);
        manager.dispatchCreate(savedInstanceState);

        findView();
        tabHost.setup(manager);
        tabHost.addTab(tabHost.newTabSpec("hot").setIndicator("remen")
                .setContent(new Intent(getActivity(), HotFriend.class)));
        tabHost.addTab(tabHost.newTabSpec("new").setIndicator("zuixin")
                .setContent(new Intent(getActivity(), NewFriend.class)));


        setListener();

        return v;
    }

    private void findView() {
        tabHost = (TabHost)v.findViewById(R.id.tabhost);
        imageView = (ImageView)v.findViewById(R.id.friend_Item_add);
        radioderGroup = (RadioGroup) v.findViewById(R.id.main_radio);
    }

    private void setListener() {
        imageView.setOnClickListener(new OnClickListener());
        radioderGroup.setOnCheckedChangeListener(new OnCheckListener());
    }

    private class OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(),BlogSubmitActivity.class);
            startActivity(intent);
        }
    }

    private class OnCheckListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch(checkedId){
                case R.id.button_hot:
                    tabHost.setCurrentTabByTag("hot");
                    break;
                case R.id.button_new:
                    tabHost.setCurrentTabByTag("new");
                    break;
            }
        }
    }
}
