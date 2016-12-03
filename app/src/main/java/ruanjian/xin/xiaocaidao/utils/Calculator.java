package ruanjian.xin.xiaocaidao.utils;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by xin on 2016/11/30.
 * 说明：此类完成动态计算ListView和GridView的高度
 */

public class Calculator {
    public static void setListViewHeightBasedOnChildren(ListView listView,int marginBottom){
        ListAdapter listAdapter = listView.getAdapter();
        if( listAdapter==null ){
            return;
        }
        int totalHeight = 0;
        Log.i("tag","数量为："+listAdapter.getCount());
        for(int i=0;i<listAdapter.getCount();i++){
            View listItem = listAdapter.getView(i,null,listView);
            listItem.measure(0,0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1))+marginBottom;
        Log.i("tag","高度为："+params.height);
        listView.setLayoutParams(params);
    }

}
