package ruanjian.xin.xiaocaidao.domain;

import android.content.Context;

/**
 * Created by 你的账户 on 2016/11/24.
 */

public class Name {
    private String name;
    private String avatarUrl;

    public Name(String avatarUrl, String name) {
        this.name = name;
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
