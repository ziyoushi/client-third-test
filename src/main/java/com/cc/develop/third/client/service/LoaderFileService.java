package com.cc.develop.third.client.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * com.cc.develop.third.client.service
 *
 * @author changchen
 * @email java@mail.com
 * @date 2021-04-02 13:32:32
 * 上传jar、class以及其他文件 并返回上传路径地址
 */
public interface LoaderFileService {
    /**
     * 文件上传处理里
     * @param file
     * @return
     */
    String loaderFilePath(MultipartFile file);

}
