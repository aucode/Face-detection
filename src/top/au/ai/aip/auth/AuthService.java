package top.au.ai.aip.auth;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import top.au.ai.aip.auth.util.HttpRequest;
import top.au.ai.aip.auth.util.HttpUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @Project face
 * @Description: AuthService
 * @Author AU
 * @Explain
 * @Date 2020-04-01 9:26
 */
public class AuthService {
    public static void main(String[] args) throws IOException {
//        String content ="result=0&faillist=&balance=18&linkid=4F7DDE3557374894B7A2B056D5CD1FEE&description=发送短信成功";
//        content = content.replace("=","\":\"");
//        System.out.println(content);
//        content = content.replace("&","\",\"");
//        System.out.println(content);
//        content = "{\"" + content +"\"}";
//        System.out.println(content);
//        JSONObject object = JSON.parseObject(content);
//        System.out.println(object.toJSONString());
        String authToken = HttpUtil.getAuth();
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/detect";
        String flieNameUrl = "C:\\Users\\Administrator.WIN-IHVBI18K8J9\\Pictures\\Camera Roll\\0.jpg";
        String contentTypeStr = "application/json";
        String requestRule = "age,mask,beauty,emotion,face_shape,gender";
        HttpRequest.request(flieNameUrl,authToken,url,contentTypeStr,requestRule);
    }
}