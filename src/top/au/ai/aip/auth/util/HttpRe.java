package top.au.ai.aip.auth.util;

import com.baidu.aip.util.Base64Util;
import net.sf.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * @Project face
 * @Description: HttpRe
 * @Author AU
 * @Explain
 * @Date 2020-04-01 10:08
 */
public class HttpRe {

    private static String Token = "24.cfeb91d5877abf961b35feaeff4bcfd9.2592000.1588297294.282335-18979408";
    public static void request() throws IOException {
        //设置图片
        byte[] bytes1 = HttpRe.readFileByBytes("C:\\Users\\Administrator.WIN-IHVBI18K8J9\\Pictures\\Camera Roll\\0.jpg");
        String image1 = Base64Util.encode(bytes1);

        String url = "https://aip.baidubce.com/rest/2.0/face/v3/detect";
        try {
            String str = "age,mask,beauty,emotion,face_shape,gender";
            Map<String, Object> map = new HashMap<>();
            map.put("image", image1);
            map.put("face_field", str);

            map.put("image_type", "BASE64");

            String param = HttpRe.toJson(map);
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = Token;

            String result = HttpUtil.post(url, accessToken, "application/json", param);

            System.out.println("识别结果："+result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
     */
    public static byte[] readFileByBytes(String fileName) throws IOException {
        FileInputStream in = new FileInputStream(fileName);
        byte[] bytes = null;
        try {

            ByteArrayOutputStream out = new ByteArrayOutputStream(1024);

            byte[] temp = new byte[1024];

            int size = 0;

            while ((size = in.read(temp)) != -1) {
                out.write(temp, 0, size);
            }

            in.close();

            bytes = out.toByteArray();

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return bytes;
    }
    public static String toJson(Map<String,Object> map){
        Set<String> keys = map.keySet();
        String key = "";
        Object value = "";
        StringBuffer jsonBuffer = new StringBuffer();
        jsonBuffer.append("{");
        for(Iterator<String> it = keys.iterator();it.hasNext();){
            key =  (String)it.next();
            value = map.get(key);
            jsonBuffer.append("\""+key+"\":\""+value+"\"");
            if(it.hasNext()){
                jsonBuffer.append(",");
            }
        }
        jsonBuffer.append("}");
        return jsonBuffer.toString();
    }
//    public static String toJson2(Map<String,String> map){
//        Set<Map.Entry<String, String>> entrys = map.entrySet();
//        Map.Entry<String, String> entry = null;
//        String key = "";
//        String value = "";
//        StringBuffer jsonBuffer = new StringBuffer();
//        jsonBuffer.append("{");
//        for(Iterator<Map.Entry<String, String>> it = entrys.iterator(); it.hasNext();){
//            entry =  (Map.Entry<String, String>)it.next();
//            key = entry.getKey();
//            value = entry.getValue();
//            jsonBuffer.append(key+":"+value);
//            if(it.hasNext()){
//                jsonBuffer.append(",");
//            }
//        }
//        jsonBuffer.append("}");
//        return jsonBuffer.toString();
//    }
}
