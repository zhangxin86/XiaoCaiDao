package ruanjian.xin.xiaocaidao.domain;

/**
 * Created by Administrator on 2016/11/22.
 */
public class Caipu {//经典菜谱
    private String Name;
    private String Material;//材料
    private String imgURL;//图片地址
    private String ID;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Caipu(){}
    public Caipu(String name, String material, String URL
    ) {
        Name = name;
        Material = material;
        imgURL = URL;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String material) {
        Material = material;
    }
}
