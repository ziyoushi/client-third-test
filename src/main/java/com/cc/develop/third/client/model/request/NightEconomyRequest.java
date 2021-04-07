package com.cc.develop.third.client.model.request;

import lombok.Data;

import java.util.Date;

/**
 * com.cc.develop.third.client.model.request
 *
 * @author changchen
 * @email java@mail.com
 * @date 2021-03-22 16:05:00
 */
@Data
public class NightEconomyRequest {
    private String method;
    private String sign;
    private Date timestamp = new Date();
    private String version = "1.0";
    private String custId;
    private BizContent biz_content;
}

