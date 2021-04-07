package com.cc.develop.third.client.controller;

import com.alibaba.fastjson.JSONObject;
import com.cc.develop.third.client.model.request.NightEconomyRequest;
import com.cc.develop.third.client.model.urbanbrain.UrbanBrainRequest;
import com.cc.develop.third.client.service.IReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * com.cc.develop.third.client.controller
 *
 * @author changchen
 * @email java@mail.com
 * @date 2021-03-22 11:24:14
 * 推送接收方测试
 */
@RestController
@RequestMapping("/receiver")
public class ReceiverController {

    @Autowired
    private IReceiverService receiverService;

    @PostMapping("/response")
    public JSONObject receiverResponse(HttpServletRequest request){
        String signature = request.getHeader("Signature");
        String timeStamp = request.getHeader("TimeStamp");
        //第三方 appId appKey
        //  "appId": "66666666",
        //  "appKey": "66666666KEY",
        String appId = "66666666";
        String appKey = "66666666KEY";

        JSONObject obj = receiverService.receiverData(appId,appKey,timeStamp,signature);

        return obj;

    }
    /**
     * 模拟姑苏八点半 余位推送 带验签
     * @return
     */
    @PostMapping("/nightEconomyResponse")
    public JSONObject receiverNightEconomyResponse(@RequestBody String str){
        NightEconomyRequest nightEconomyRequest = JSONObject.parseObject(str, NightEconomyRequest.class);

        JSONObject obj = receiverService.receiverNightEconomyResponse(nightEconomyRequest);

        return obj;
    }

    /**
     * 模拟 姑苏八点半 流水
     * @param str
     * @return
     */
    @PostMapping("/receiverNightEconomyRemainderResponse")
    public JSONObject receiverNightEconomyRemainderResponse(@RequestBody String str){
        NightEconomyRequest nightEconomyRequest = JSONObject.parseObject(str, NightEconomyRequest.class);

        JSONObject obj = receiverService.receiverNightEconomyRemainderResponse(nightEconomyRequest);

        return obj;
    }

    /**
     * 模拟城市大脑 流水
     * @param str
     * @return
     */
    @PostMapping("/receiverUrbanBrainFlowResponse")
    public JSONObject receiverUrbanBrainFlowResponse(String str){
        UrbanBrainRequest urbanBrainRequest = JSONObject.parseObject(str, UrbanBrainRequest.class);
        JSONObject object = receiverService.receiverUrbanBrainFlowResponse(urbanBrainRequest);
        return object;
    }

    /**
     * 模拟城市大脑 余位
     * @param str
     * @return
     */
    @PostMapping("/receiverUrbanBrainRemainderResponse")
    public JSONObject receiverUrbanBrainRemainderResponse(String str){
        UrbanBrainRequest urbanBrainRequest = JSONObject.parseObject(str, UrbanBrainRequest.class);
        JSONObject object = receiverService.receiverUrbanBrainRemainderResponse(urbanBrainRequest);
        return object;
    }

}
