package com.cc.develop.third.client.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * com.cc.develop.third.client.service
 *
 * @author changchen
 * @email java@mail.com
 * @date 2021-01-02 14:01:26
 */
public interface IParkingService {

    /**
     * 导入
     * @param file
     * @return
     */
    String importExcelData(MultipartFile file);

    /**
     * 获取本地文件中的数据
     * @return
     */
    List<String> getLocalFileData();

}
