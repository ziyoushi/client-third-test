package com.cc.develop.third.client.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cc.develop.third.client.model.request.NightEconomyRequest;
import com.cc.develop.third.client.model.urbanbrain.UrbanBrainRequest;
import com.cc.develop.third.client.service.IReceiverService;
import com.cc.develop.third.client.util.CustomSignUtil;
import com.cc.develop.third.client.util.SignMD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

/**
 * com.cc.develop.third.client.service.impl
 *
 * @author changchen
 * @email java@mail.com
 * @date 2021-03-22 13:08:30
 */
@Slf4j
@Service
public class ReceiverServiceImpl implements IReceiverService {

    @Value("${fly.update.privateKey}")
    private String privateKey;

    @Value("${fly.update.custId}")
    private String custId;

    @Override
    public JSONObject receiverData(String appId, String appKey, String timeStamp, String signature) {

        JSONObject object = new JSONObject();
        String sha1String = SignMD5Util.getSha1String(appId, appKey, timeStamp);
        if (!StringUtils.isEmpty(sha1String) && !StringUtils.isEmpty(signature)){
            if (signature.equals(sha1String)){
                object.put("msg","success");
                object.put("code","200");
            }else {
                object.put("msg","failure");
                object.put("code","500");
            }
        }
        object.put("msg","error");
        object.put("code","405");

        return object;
    }

    @Override
    public JSONObject receiverNightEconomyResponse(NightEconomyRequest request) {
        return this.returnJsonData(request);
    }

    private JSONObject returnJsonData(NightEconomyRequest request){
        JSONObject object = new JSONObject();
        String sign = request.getSign();

        List<Object> biz_content = request.getBiz_content().getPark_code_list();

        String customSign = "";
        try {
            customSign = CustomSignUtil.sign(JSONObject.toJSONString(biz_content).getBytes(), privateKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        //判断 两个验签值 是否相同
        if (!StringUtils.isEmpty(sign) && !StringUtils.isEmpty(customSign)){
            if (sign.equals(customSign)){

                object.put("data","push is ok");
                object.put("msg","sign success");
                object.put("code","1000");
            }else {
                object.put("msg","sign error");
                object.put("code","10001");
            }
        }else {
            object.put("msg","error");
            object.put("code","500");
        }
        return object;
    }


    @Override
    public JSONObject receiverNightEconomyRemainderResponse(NightEconomyRequest nightEconomyRequest) {
        return this.returnJsonData(nightEconomyRequest);
    }

    @Override
    public JSONObject receiverUrbanBrainFlowResponse(UrbanBrainRequest urbanBrainRequest) {
        JSONObject jsonObject = new JSONObject();
        if (urbanBrainRequest != null){
            jsonObject.put("msg","success");
            jsonObject.put("status",200);


        }

        return null;
    }

    @Override
    public JSONObject receiverUrbanBrainRemainderResponse(UrbanBrainRequest urbanBrainRequest) {
        return null;
    }
}
