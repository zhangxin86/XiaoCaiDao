package ruanjian.xin.xiaocaidao.utils;

/**
 * Created by xin on 2016/11/17.
 */

public class Utils {
    public static final String URL = "http://172.27.35.1:8008/";
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
    public static final String check_ipUrl = URL+"user/checkIp";
    public static final String set_thUrl = URL+"blog/setThumb";
    public static final String set_coUrl = URL+"collection/setCollection";
    public static final String check_coUrl = URL+"collection/checkCollection";


    public static final String JUHE_URL="http://apis.juhe.cn/cook/query.php";
    public static final String JUHE_KEY="7e9e4f6575ebf1859111d364c36b3f47";

    public static final String JIFINAL_UPLOAD = "http://192.168.52.1:8080/blog/upload";
}
