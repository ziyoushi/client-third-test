package com.cc.develop.third.client.model;

import com.alibaba.fastjson.JSONObject;
import com.cc.develop.third.client.json.JsonBean;

import java.lang.reflect.Method;

/**
 * com.cc.develop.third.client.model
 *
 * @author changchen
 * @email java@mail.com
 * @date 2021-03-02 15:32:05
 */
public class TransformDataTest {

    public Object transForm(String cus,String str){
        System.out.println(cus);
        System.out.println("str="+str);
        JsonBean jsonBean = JSONObject.parseObject(cus, JsonBean.class);

        return jsonBean;
    }

    public static void main(String[] args) throws Exception{
        Class<?> clazz = Class.forName("com.cc.develop.third.client.model.TransformDataTest");
        Object o = clazz.newInstance();
        Method transForm = clazz.getDeclaredMethod("transForm", String.class, String.class);

        String string = "  {\n" +
                "    \"id\": 7989,\n" +
                "    \"carPlateNo\": \"苏EPASS2\",\n" +
                "    \"carPlateColor\": \"001\",\n" +
                "    \"parkingName\": \"人民商场地下车库\",\n" +
                "    \"parkingCode\": \"PT012019\",\n" +
                "    \"validateStatus\": \"001\",\n" +
                "    \"validateDate\": 1612022400000,\n" +
                "    \"endDate\": 1614528000000,\n" +
                "    \"reason\": \"修改今天为月卡最后一天\",\n" +
                "    \"updateUser\": 35,\n" +
                "    \"siteId\": \"1\"\n" +
                "  }";

        Object invoke = transForm.invoke(o, string, "ddd");

        System.out.println(invoke);

    }

}
