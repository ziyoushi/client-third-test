package com.cc.develop.third.client.service;

import com.alibaba.fastjson.JSONObject;
import com.cc.develop.third.client.model.request.NightEconomyRequest;
import com.cc.develop.third.client.model.urbanbrain.UrbanBrainRequest;

/**
 * com.cc.develop.third.client.service
 *
 * @author changchen
 * @email java@mail.com
 * @date 2021-03-22 13:03:54
 */
public interface IReceiverService {

    /**
     * 姑苏 余位 推送 验签
     * @param appId
     * @param appKey
     * @param timeStamp
     * @param signature
     * @return
     */
    JSONObject receiverData(String appId, String appKey, String timeStamp, String signature);

    /**
     * 姑苏八点半 余位 推送 + 验签
     * @param request
     * @return
     */
    JSONObject receiverNightEconomyResponse(NightEconomyRequest request);

    /**
     * 姑苏八点半 流水 +验签
     * @param nightEconomyRequest
     * @return
     */
    JSONObject receiverNightEconomyRemainderResponse(NightEconomyRequest nightEconomyRequest);

    /**
     * 城市大脑 流水 无验签
     * @param urbanBrainRequest
     * @return
     */
    JSONObject receiverUrbanBrainFlowResponse(UrbanBrainRequest urbanBrainRequest);

    /**
     * 城市大脑 余位 无验签
     * @param urbanBrainRequest
     * @return
     */
    JSONObject receiverUrbanBrainRemainderResponse(UrbanBrainRequest urbanBrainRequest);
}
