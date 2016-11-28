package ruanjian.xin.xiaocaidao.domain;

import android.content.Context;

/**
 * Created by 你的账户 on 2016/11/24.
 */

public class Name1 {
  private long id;
    private String name;
    private String name1;

    public Name1(long id, String name, String name1) {
        this.id = id;
        this.name = name;
        this.name1 = name1;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }
}
