package ruanjian.xin.xiaocaidao.domain;

import android.content.Context;

/**
 * Created by 你的账户 on 2016/11/24.
 */

public class Name {
  private long id;
    private String name;

    public Name(long id, String name) {
        this.id = id;
        this.name = name;
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
}
