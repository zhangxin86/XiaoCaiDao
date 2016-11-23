package ruanjian.xin.xiaocaidao.domain;

/**
 * Created by Administrator on 2016/11/22.
 */
public class Caipu {
    private Long id;
    private String Name;
    private String Material;//材料
    private String Total;//赞的人数
    private int img;//主图
//
    public Caipu(Long id, String name, String material, String total, int img
    ) {
        this.id = id;
        Name = name;
        Material = material;
        Total = total;
        this.img = img;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
