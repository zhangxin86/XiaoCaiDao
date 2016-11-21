package ruanjian.xin.xiaocaidao.domain;

/**
 * Created by xin on 2016/11/17.
 */

public class Parts {
    public long Id = -1;
    public String Name = "拔丝煎饺";
    public String Mark = "传统与变革起头并进，就是好吃!!";
    public Parts(){}

    public Parts(long id, String name, String mark) {
        Id = id;
        Name = name;
        Mark = mark;
    }

    @Override
    public String toString() {
        return "Parts{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Mark='" + Mark + '\'' +
                '}';
    }
}
