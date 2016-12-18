package ruanjian.xin.xiaocaidao.domain;

import android.view.LayoutInflater;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by liyuxuan on 2016/12/15.
 */

public class CommentItem {
    private String avatarUrl;
    private String userName;
    private String content;


    public CommentItem(){}
    public CommentItem(String avatarUrl, String content, String userName) {
        this.avatarUrl = avatarUrl;
        this.content = content;
        this.userName = userName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
