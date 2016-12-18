package ruanjian.xin.xiaocaidao.domain;

import ruanjian.xin.xiaocaidao.R;

/**
 * Created by liyuxuan on 2016/12/16.
 */

public class TodayItem {
    private int menuImg;
    private String menuName;
    private String menuDes;

    public TodayItem(){}
    public TodayItem(String menuDes, int menuImg, String menuName) {
        this.menuDes = menuDes;
        this.menuImg = menuImg;
        this.menuName = menuName;
    }

    public String getMenuDes() {
        return menuDes;
    }

    public void setMenuDes(String menuDes) {
        this.menuDes = menuDes;
    }

    public int getMenuImg() {
        return menuImg;
    }

    public void setMenuImg(int menuImg) {
        this.menuImg = menuImg;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
