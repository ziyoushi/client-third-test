package com.cc.develop.third.client.controller;

import com.alibaba.fastjson.JSONObject;
import com.cc.develop.third.client.service.IParkingService;
import com.cc.develop.third.client.util.HttpClientUtil;
import com.cc.develop.third.client.util.SignMD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * com.cc.develop.third.client.controller
 *
 * @author changchen
 * @email java@mail.com
 * @date 2020-12-22 10:19:34
 */
@RestController
@RequestMapping("/park")
public class ParkingController {

    @Autowired
    private IParkingService parkingService;

    /**
     * 根据 使用明文加密
     * @return
     */
    @PostMapping("/queryParkingImageUrl/{parkingSerialNo}")
    public String queryParkingImageUrl(@PathVariable String parkingSerialNo){

        String url = "http://127.0.0.1:81/parking/queryParkingFlowImageUrlBySerialNo";

        /**
         * public static JSONObject httpPostReturnCodeWithParam(String url, String param, String signature, String timestamp) {
         *         //封装post请求
         */
        String appId = "44444444";
        String appKey = "44444444KEY";
        long timestamp = System.currentTimeMillis();
        String sha1String = SignMD5Util.getSha1String(appId, appKey, String.valueOf(timestamp));

        JSONObject jsonObject = HttpClientUtil.httpPostReturnCodeWithParam(url, parkingSerialNo, sha1String, String.valueOf(timestamp));

        return jsonObject.toString();
    }

    /**
     * 将导入的文件写入到本地文件中
     * @param file
     * @return
     */
    @PostMapping("importScanExcelData")
    public String importScanExcelData(@RequestParam("file") MultipartFile file){

        String data = parkingService.importExcelData(file);

        return data;
    }

    /**
     * 查看本地文件中的数据
     * @return
     */
    @GetMapping("/queryLocalFileData")
    public List<String> queryLocalFileData(){
        List<String> list = parkingService.getLocalFileData();
        return list;
    }

}
