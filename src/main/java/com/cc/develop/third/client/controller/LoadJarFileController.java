package com.cc.develop.third.client.controller;

import com.alibaba.fastjson.JSONObject;
import com.cc.develop.third.client.constant.ThirdClientConstant;
import com.cc.develop.third.client.loader.CustomizeClassDemoLoader;
import com.cc.develop.third.client.service.LoaderFileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Method;

/**
 * com.cc.develop.third.client.controller
 *
 * @email java@mail.com
 * @date 2021-04-01 15:07:02
 * //测试 上传jar将jar中的class加载到内存中
 * 服务启动时 在执行class里面的方法
 */
@RestController
@RequestMapping("/load")
public class LoadJarFileController {

    @Autowired
    private LoaderFileService loaderFileService;

    @ApiOperation(value = "jar文件上传")
    @PostMapping("/importClassFile")
    public JSONObject importJarFile(@RequestParam(value = "file") MultipartFile file){

        /**
         * 思路一： 上传class 并写入到当前项目的路径下面 自定义类加载器会根据地址类名读取到这个类
         * 难点：如何从MultipartFile将文件流写入到本地文件中
         * 将上传的文件读取到 写入到本地文件
         * 如何将生成的class文件加载到内存中
         */
        String str = loaderFileService.loaderFilePath(file);

        JSONObject object = new JSONObject();
        object.put("test", "testAAAA");
        object.put("path",str);
        // 根据filePath + 全文件名 来判断 是
        //如果是 .class结尾的 直接 加载到内存中  创建class目录
        //如果是 .jar文件结尾的 将jar中的.class文件加载到jvm并 创建jar目录
        //如果是其他文件 创建other目录
        return object;
    }

    /**
     * 调用接口查看 这个类是否被加载进内存
     * @param className
     * @return
     * @throws Exception
     */
    @GetMapping("/invokeClassMethod")
    public String invokeClassMethod(@RequestParam("className") String className) throws Exception {

        StringBuffer classPath = new StringBuffer(System.getProperty("user.dir"));
        classPath.append(ThirdClientConstant.CLASS_FILE_DIR).append("/");
        //class文件路径指定当前项目下的class目录下
        CustomizeClassDemoLoader loader = new CustomizeClassDemoLoader(classPath.toString());
        Class<?> clazz = loader.loadClass(className);

        String result = "";
        if (clazz != null) {
            Object obj = clazz.newInstance();
            Method method = clazz.getDeclaredMethod("demoTest", String.class);
            result = (String) method.invoke(obj, "world,world");

        }

        return result;
    }

}