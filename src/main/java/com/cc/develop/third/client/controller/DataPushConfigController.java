package com.cc.develop.third.client.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * com.cc.develop.third.client.controller
 *
 * @author changchen
 * @email java@mail.com
 * @date 2021-01-19 11:28:13
 * 数据推送配置
 */
@RestController
@RequestMapping("/dataPushConfig")
public class DataPushConfigController {

    // 配置信息 crud

    //todo 直接将json数据存到数据库中 每次请求使用
    public static void main(String[] args) {
        //读取json
        String str = "{\n" +
                "  \n" +
                "  \"cust_id\": \"8010000010376\",\n" +
                "  \"method\": \"fly.park.upload.parkinfo\",\n" +
                "  \"version\": \"1.0\"\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(str);

        String javaBeanName = "JsonTestBean";
        Map<String,Object> map = (Map)jsonObject;
        //遍历map
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            String value = map.get(key).toString();
            boolean json = isJson(value);
            if (json){
                //使用jsonArray 转换
                JSONArray jsonArray = JSONObject.parseArray(value);
                Object subObj = jsonArray.get(0);
            }else {
                //不是 json 判断值的类型 todo 后面需要调试
                String inputType = getInputType(value);
                if (!StringUtils.isEmpty(inputType)){
                    switch (inputType){
                        case "string":
                            //todo 添加到文件中

                            break;
                        case "date":
                            break;
                    }
                }

            }

        }
        File sourceFile = null;
        StringBuffer sourcePath =new  StringBuffer("src/main/java/com/cc/develop/third/client/model/");
        FileWriter fileWriter = null;
        try {
            sourcePath.append(javaBeanName).append(".java");

            sourceFile = new File(sourcePath.toString());

            fileWriter = new FileWriter(sourceFile,true);
            String packageInfo = "package com.cc.develop.third.client.model;\n" +
                    "\n" +
                    "import lombok.Data;\n" +
                    "\n" +
                    "import java.io.Serializable;\n";
            fileWriter.write(packageInfo);
            fileWriter.write("@Data \n");
            fileWriter.write("public class ");
            fileWriter.write(javaBeanName);
            fileWriter.write(" implements Serializable {\n");
            //todo 属性参数

            fileWriter.write("}");
        }catch (Exception e){

        }finally {
            if (fileWriter != null){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


        // JavaStringCompiler compiler = new JavaStringCompiler();

    }

    /**
     * 判断字符串是否是json
     * @param string
     * @return
     */
    public static boolean isJson(String string){
        if(StringUtils.isEmpty(string)){
            return false;
        }
        boolean isJsonObject = true;
        boolean isJsonArray = true;
        try {
            JSONObject.parseObject(string);
        } catch (Exception e) {
            isJsonObject = false;
        }
        try {
            JSONObject.parseArray(string);
        } catch (Exception e) {
            isJsonArray = false;
        }
        //不是json格式
        if(!isJsonObject && !isJsonArray){
            return false;
        }
        return true;
    }

    // 会存在一定的偏差

    /**
     * 根据输入判断该类型
     * @param input
     * @return 返回的类型为定义好的字符串
     */
    public static String getInputType(Object input){
        String output = "";
        if (input instanceof String){
            output = "string";
        }

        return output;
    }

    //操作创建一个class 读取文件创建对象
    public String createBeanByFile(){
        return null;
    }

    public String generateJavaBeanByCondition(String jsonStr,String typeName){
        //todo 根据json字符串 和类名 生成新的Java类
        return null;
    }

    //根据类名 查询类 todo 动态加载 jar

}
