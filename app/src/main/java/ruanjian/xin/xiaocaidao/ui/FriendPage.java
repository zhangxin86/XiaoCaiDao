package ruanjian.xin.xiaocaidao.ui;

import android.app.Fragment;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import ruanjian.xin.xiaocaidao.R;
import ruanjian.xin.xiaocaidao.ui.Friend.FriendItemAdd;
import ruanjian.xin.xiaocaidao.ui.Friend.HotFriend;
import ruanjian.xin.xiaocaidao.ui.Friend.NewFriend;

public class FriendPage extends Fragment {
    private View v;
    private LocalActivityManager manager;
    private TabHost tabHost;
    private ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_friend_page,container,false);

        manager = new LocalActivityManager(getActivity(),true);
        manager.dispatchCreate(savedInstanceState);

        findView();
        tabHost.setup(manager);
        tabHost.addTab(tabHost.newTabSpec("hot").setIndicator("热门")
                .setContent(new Intent(getActivity(), HotFriend.class)));
        tabHost.addTab(tabHost.newTabSpec("new").setIndicator("最新")
                .setContent(new Intent(getActivity(), NewFriend.class)));
        tabHost.setCurrentTab(0);
        updateTab(tabHost);    //初始化Tab的颜色，和字体的颜色
        setListener();

        return v;
    }

    private void findView() {
        tabHost = (TabHost)v.findViewById(R.id.tabhost);
        imageView = (ImageView)v.findViewById(R.id.friend_Item_add);
    }

    private void setListener() {
        tabHost.setOnTabChangedListener(new OnTabChangedListener());   // 选择监听器
        imageView.setOnClickListener(new OnClickListener());
    }

    class OnTabChangedListener implements TabHost.OnTabChangeListener {
        @Override
        public void onTabChanged(String tabId) {
            tabHost.setCurrentTabByTag(tabId);
            System.out.println("tabid " + tabId);
            System.out.println("curreny after: " + tabHost.getCurrentTabTag());
            updateTab(tabHost);
        }
    }

    private class OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(),FriendItemAdd.class);
            startActivity(intent);
        }
    }

    /**
     * 更新Tab标签的颜色，和字体的颜色
     * @param tabHost
     */
    private void updateTab(final TabHost tabHost) {
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            View view = tabHost.getTabWidget().getChildAt(i);
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextSize(16);
            tv.setTypeface(Typeface.SERIF, 2); // 设置字体和风格
            if (tabHost.getCurrentTab() == i) {//选中
                view.setBackgroundDrawable(getResources().getDrawable(R.drawable.friend_tab_back_click));//选中后的背景
                tv.setTextColor(this.getResources().getColorStateList(
                        R.color.white));
            } else {//不选中
                view.setBackgroundDrawable(getResources().getDrawable(R.drawable.friend_tab_back_none));//非选择的背景
                tv.setTextColor(this.getResources().getColorStateList(
                        R.color.tab_select));
            }
        }
    }
}
