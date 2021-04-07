package com.cc.develop.third.client.loader;

import com.cc.develop.third.client.constant.ThirdClientConstant;

import java.io.*;

/**
 * com.cc.develop.third.client.loader
 *
 * @author changchen
 * @email java@mail.com
 * @date 2021-04-01 16:33:07
 */
public class CustomizeClassDemoLoader extends ClassLoader{
    private String byteCodePath;

    public CustomizeClassDemoLoader(String byteCodePath) {
        this.byteCodePath = byteCodePath;
    }

    public CustomizeClassDemoLoader(ClassLoader parent, String byteCodePath) {
        super(parent);
        this.byteCodePath = byteCodePath;
    }

    /**
     * className 类名 不包含全路径
     *
     * @param className
     * @return
     * @throws ClassNotFoundException
     * class--> 字节数组
     * 需要带 .class
     */
    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        BufferedInputStream bis = null;
        ByteArrayOutputStream baos = null;
        try {
            //获取字节码文件的完整路径
            String fileName = byteCodePath + className + ThirdClientConstant.CLASS_FILE_TYPE;
            //获取一个输入流
            bis = new BufferedInputStream(new FileInputStream(fileName));
            //获取一个输出流
            baos = new ByteArrayOutputStream();
            //具体读入数据并写出的过程
            int len;
            byte[] data = new byte[1024];
            while ((len = bis.read(data)) != -1) {
                baos.write(data, 0, len);
            }
            //获取内存中的完整的字节数组的数据
            byte[] byteCodes = baos.toByteArray();
            //调用defineClass()，将字节数组的数据转换为Class的实例。
            Class clazz = defineClass(null, byteCodes, 0, byteCodes.length);
            return clazz;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
