package com.cc.develop.third.client.service.impl;

import com.cc.develop.third.client.constant.ThirdClientConstant;
import com.cc.develop.third.client.service.LoaderFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * com.cc.develop.third.client.service.impl
 *
 * @author changchen
 * @email java@mail.com
 * @date 2021-04-02 13:34:12
 */
@Slf4j
@Service
public class LoaderFileServiceImpl implements LoaderFileService {

    @Override
    public String loaderFilePath(MultipartFile file) {

        //读取 file 写入到 文件中
        String originalFilename = file.getOriginalFilename();
        System.out.println("文件的全名+文件类型" + originalFilename);
        //当前项目的路径
        String projectPath = System.getProperty("user.dir");
        StringBuffer pathSbf = new StringBuffer(projectPath);

        //判断 是什么文件
        if (originalFilename.endsWith(ThirdClientConstant.CLASS_FILE_TYPE)){
            //判断 是否有class的目录 如果没有 则创建
            pathSbf.append(ThirdClientConstant.CLASS_FILE_DIR);
        }else if (originalFilename.endsWith(ThirdClientConstant.JAR_FILE_TYPE)){
            pathSbf.append(ThirdClientConstant.JAR_FILE_DIR);
        }else {
            //创建
            pathSbf.append(ThirdClientConstant.OTHER_FILE_DIR);
        }
        File localFile = new File(pathSbf.toString());
        if (!localFile.exists()){
            //如果目录不存在 则进行创建
            localFile.mkdir();
        }

        //文件写入到指定目录下
        try {
            FileInputStream fis = (FileInputStream) file.getInputStream();
            FileOutputStream fos = new FileOutputStream(new File(pathSbf.toString()+"/"+originalFilename));

            byte[] buffer = new byte[1024];
            int len;//记录每次read()到数组中数据的个数
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            //将class文件加载到内存中

        } catch (Exception e) {
            e.printStackTrace();
        }
        pathSbf.append("/").append(originalFilename);
        // 如果是 jar文件 则读取到里面的class然后再加入到内存中 如果是class写入后加载到内存 入手是class则不操作
        if (pathSbf.toString().endsWith(ThirdClientConstant.JAR_FILE_TYPE)){
            //走jar的逻辑 根据指定目录 解压jar 自定义jar类加载
            this.loadJarFile(pathSbf.toString());
        }

        return pathSbf.toString();
    }

    /**
     * 如果是jar文件直接写入到class目录下
     * @param jarPath
     */
    private void loadJarFile(String jarPath) {
        //直接解压jar文件,然后递归查询class文件,以及对应的.jar文件,并加载jar文件进行读取
        try {
            JarFile jarFile = new JarFile(jarPath);
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry jarEntry = entries.nextElement();
                String name = jarEntry.getName();
                if (name.endsWith(ThirdClientConstant.CLASS_FILE_TYPE)){
                    dealClass(name, jarFile, jarEntry);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void dealClass(String name, JarFile jarFile, JarEntry jarEntry) {
        String className = name.replace(".class", "").replaceAll("/", ".");
        if (!StringUtils.isEmpty(className)){
            //将class文件统一写入到指定目录下
            String projectPath = System.getProperty("user.dir");
            StringBuffer sbf = new StringBuffer(projectPath);
            sbf.append("/").append(ThirdClientConstant.CLASS_FILE_DIR);
            //类名 补齐 + .class 后写入到 class目录下
            //直接写入 到
            String substring = className.substring(className.lastIndexOf(".")+1);
            sbf.append("/").append(substring).append(ThirdClientConstant.CLASS_FILE_TYPE);
            try {
                InputStream input = jarFile.getInputStream(jarEntry);
                FileOutputStream fos = new FileOutputStream(new File(sbf.toString()));
                byte[] buffer = new byte[1024];
                int len;//记录每次read()到数组中数据的个数
                while ((len = input.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        String property = System.getProperty("user.dir");
        System.out.println(property);
        StringBuffer sbf = new StringBuffer(property);

        sbf.append(ThirdClientConstant.CLASS_FILE_DIR);

        String str = "com.cc.develop.third.client.constant.ThirdClientConstant";

        String substring = str.substring(str.lastIndexOf(".")+1);
        System.out.println(substring);

    }

}
