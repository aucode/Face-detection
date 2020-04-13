package top.au.ai.aip.auth.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * @Project Face-detection
 * @Description: ProcessingResult
 * @Author Du Yi Code
 * @Explain 处理人脸识别类
 * @Date 2020-04-11 22:03
 */
public class ProcessingResult {
    private static JSONObject jsonFaceData;
    private static JSONObject jsonResult;
    private static JSONObject jsonObject;
    public static JSON ProcessingFaceData(String faceData){
        jsonFaceData = JSONObject.parseObject(faceData);
        jsonResult = JSONObject.parseObject(jsonFaceData.getString("result"));
        String face_list = jsonResult.getString("face_list");
        face_list = face_list.replaceAll("\\[", "").replaceAll("\\]", "");
        jsonObject = JSONObject.parseObject(face_list);

        JSONObject jsondata = new JSONObject();
        jsondata.put("是人脸的百分比",jsonObject.getDoubleValue("face_probability")*100+"%");
        jsondata.put("年龄",jsonObject.getString("age"));
        jsondata.put("美丑分",jsonObject.getString("beauty"));

        //性别
        String gender = jsonObject.getString("gender");
        if (null !=gender){
            jsondata.put("性别", JSONObject.parseObject(gender).getString("type").equals("male") ? "男":"女");
        }
        //表情
        String expression = jsonObject.getString("expression");
        if (null !=expression){
            String expressionStr = JSONObject.parseObject(gender).getString("type");
            if (expressionStr.equals("none")) jsondata.put("表情", "不笑");
            else if (expressionStr.equals("smile")) jsondata.put("表情", "微笑");
            else if (expressionStr.equals("laugh")) jsondata.put("表情", "大笑");
        }
        // 人脸在图片中的位置
        String location = jsonObject.getString("location");
//        System.out.println(location);
//        System.out.println(jsonFaceData);
        if (null != location){
            JSONObject jsonObject = JSONObject.parseObject(location);
            JSONObject jsonLocation = new JSONObject();
            jsonLocation.put("人脸区域离上边界的距离",jsonObject.getString("top"));
            jsonLocation.put("人脸区域离左边界的距离",jsonObject.getString("left"));
            jsonLocation.put("人脸区域的宽度",jsonObject.getString("width"));
            jsonLocation.put("人脸区域的高度",jsonObject.getString("height"));
            jsonLocation.put("人脸框相对于竖直方向的顺时针旋转角，[-180,180]",jsonObject.getString("rotation"));
            jsondata.put("人脸在图片中的位置", jsonLocation);
        }

        System.out.println(jsondata);
        return jsondata;
    }
}
