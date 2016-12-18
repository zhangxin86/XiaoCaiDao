package ruanjian.xin.xiaocaidao.utils;

/**
 * Created by xin on 2016/11/17.
 */

public class Utils {
    public static final String URL = "http://10.7.88.81:8008/";
    public static int flag = 0;
    public static String timeFloder;

    public static final String loginUrl = URL+"user/checkLogin";
    public static final String check_acUrl = URL+"user/checkAccount";
    public static final String sign_Url = URL+"user/createUser";
    public static final String set_imUrl = URL+"user/setImg";
    public static final String find_imUrl = URL+"user/findImg";
    public static final String find_imLenUrl = URL+"user/findImgLength";
    public static final String set_naUrl = URL+"user/setName";
    public static final String find_naUrl = URL+"user/findName";
    public static final String set_pwdUrl = URL+"user/setPassword";
    public static final String find_foUrl = URL+"user/findFollowNum";
    public static final String find_faUrl = URL+"user/findFansNum";
    public static final String upload_blUrl = URL+"blog/uploadBlog";
    public static final String upload_coUrl = URL+"comment/uploadComment";
    public static final String set_foUrl = URL+"fans/setFollows";
    public static final String JUHE_URL="http://apis.juhe.cn/cook/query.php";
    public static final String JUHE_KEY="8a638a095bc4142a169f2b76acafbbcd";

    public static final String JIFINAL_UPLOAD = "http://192.168.52.1:8080/blog/upload";
}
