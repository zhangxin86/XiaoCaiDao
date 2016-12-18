package ruanjian.xin.xiaocaidao.utils;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/8.
 */
public class HttpUtil {
    public static String uac = "";
    public static String una = "";
    public final static int LOGINORSIGN = 0;
    public final static int FINDORCHECK_AC = 1;
    public final static int SET_CO = 2;
    public final static int SET_NA = 3;
    public final static int SET_PW = 4;
    public final static int SET_BL = 5;
    public final static int SET_FO = 6;
    private String urlContent = null;
    ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
    URI u;

    public void setValue(int type, Object... args) {
        //添加参数的代码
        NameValuePair pair1;
        NameValuePair pair2;
        NameValuePair pair3;
        NameValuePair pair4;
        switch (type) {
            case LOGINORSIGN:
                pairs.clear();
                pair1 = new BasicNameValuePair("account", (String) args[0]);
                pair2 = new BasicNameValuePair("password", (String) args[1]);
                pairs.add(pair1);
                pairs.add(pair2);
                break;
            case FINDORCHECK_AC:
                pairs.clear();
                pair1 = new BasicNameValuePair("account", (String) args[0]);
                pairs.add(pair1);
                break;
            case SET_CO://未完成
                pairs.clear();
                pair1 = new BasicNameValuePair("account", (String) args[0]);
                pair2 = new BasicNameValuePair("blog_id", (String) args[1]);
                pair3 = new BasicNameValuePair("content", (String) args[2]);
                pairs.add(pair1);
                pairs.add(pair2);
                pairs.add(pair3);
                break;
            case SET_NA:
                pairs.clear();
                pair1 = new BasicNameValuePair("account", (String) args[0]);
                pair2 = new BasicNameValuePair("name",(String)args[1]);
                pairs.add(pair1);
                pairs.add(pair2);
                break;
            case SET_PW:
                pairs.clear();
                pair1 = new BasicNameValuePair("account", (String) args[0]);
                pair2 = new BasicNameValuePair("password",(String)args[1]);
                pairs.add(pair1);
                pairs.add(pair2);
                break;
            case SET_BL:
                pairs.clear();
                pair1 = new BasicNameValuePair("name", (String) args[0]);//帖子名
                pair2 = new BasicNameValuePair("label",(String)args[1]);//标签
                pair3 = new BasicNameValuePair("account",(String)args[2]);//账号
                pair4 = new BasicNameValuePair("content",(String)args[3]);//内容
                pairs.add(pair1);
                pairs.add(pair2);
                pairs.add(pair3);
                pairs.add(pair4);
                break;
            case SET_FO:
                pairs.clear();
                pair1 = new BasicNameValuePair("account", (String) args[0]);//点击关注用户账号
                pair2 = new BasicNameValuePair("follows",(String)args[1]);//被关注的账号名
                pairs.add(pair1);
                pairs.add(pair2);
                break;
        }
    }

    public String HttpRequest_post(String url) {
        try {
            u = new URI(url);
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(u);
            //创建代表请求体的对象（注意，是请求体）
            HttpEntity requestEntity = new UrlEncodedFormEntity(pairs, "UTF-8");
            //将请求体放置在请求对象当中
            httppost.setEntity(requestEntity);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String content = httpclient.execute(httppost, responseHandler);
            //content = new String(content.getBytes("ISO-8859-1"), "UTF-8");
            //目标页面编码为UTF-8,没这个会乱码
            urlContent = content;
            Log.i("httpend",urlContent);
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            return urlContent;
        }
    }
}

