package ruanjian.xin.xiaocaidao.ui.Login;

/**
 * Created by 贾紫璇 on 2016/11/17.
 */
public class UserData {
    private String username;//用户名
    private String userpwd;//用户密码
    public int pwdresetFlag=0;//

    //获取用户名
    public String getUsername(){
        return username;
    }
    //设置用户名
    public void setUsername(String username){//输入用户名
        this.username=username;
    }
    //获取用户密码
    public String getUserpwd(){
        return userpwd;
    }
    //设置用户密码
    public  void setUserpwd(String userpwd){//输入用户密码
        this.userpwd=userpwd;
    }

    public UserData(String username, String userpwd){
        super();
        this.username=username;
        this.userpwd=userpwd;
    }

}
