package ruanjian.xin.xiaocaidao.domain;

/**
 * Created by 你的账户 on 2016/11/24.
 */

public class Name2  {
    private long id;

    private String name1;
    private String name2;
    private String name3;

    public Name2(long id, String name1, String name2, String name3) {
        this.id = id;
        this.name1 = name1;
        this.name2 = name2;
        this.name3 = name3;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }
}
