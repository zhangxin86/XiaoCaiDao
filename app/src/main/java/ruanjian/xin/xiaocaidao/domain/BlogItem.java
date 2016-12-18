package ruanjian.xin.xiaocaidao.domain;

/**
 * Created by liyuxuan on 2016/12/13.
 */

public class BlogItem {
    private long Id;
    private String avatarUrl;//头像
    private String account ;//用户名
    private String lab1;//标签1
    private String lab2;//标签2
    private String blogImg;//帖子图片
    private String countent;//帖子内容
    private int thumb;//点赞数目
    private int comment;//评论数

    public BlogItem(){

    };

    public BlogItem(String account, String avatarUrl, String blogImg, int comment, String countent, long id, String lab1, String lab2, int thumb) {
        this.account = account;
        this.avatarUrl = avatarUrl;
        this.blogImg = blogImg;
        this.comment = comment;
        this.countent = countent;
        Id = id;
        this.lab1 = lab1;
        this.lab2 = lab2;
        this.thumb = thumb;
    }

    public String getAccount() {

        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getBlogImg() {
        return blogImg;
    }

    public void setBlogImg(String blogImg) {
        this.blogImg = blogImg;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public String getCountent() {
        return countent;
    }

    public void setCountent(String countent) {
        this.countent = countent;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getLab1() {
        return lab1;
    }

    public void setLab1(String lab1) {
        this.lab1 = lab1;
    }

    public String getLab2() {
        return lab2;
    }

    public void setLab2(String lab2) {
        this.lab2 = lab2;
    }

    public int getThumb() {
        return thumb;
    }

    public void setThumb(int thumb) {
        this.thumb = thumb;
    }
}
