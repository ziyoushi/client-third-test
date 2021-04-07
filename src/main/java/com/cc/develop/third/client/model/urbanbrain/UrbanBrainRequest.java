package com.cc.develop.third.client.model.urbanbrain;

import lombok.Data;

import java.util.List;

/**
 * com.cc.develop.third.client.model.urbanbrain
 *
 * @author changchen
 * @email java@mail.com
 * @date 2021-03-26 14:51:50
 */
@Data
public class UrbanBrainRequest {
    private String orgCode = "00005";
    /**
     * 001出入场 002点位
     */
    private String busiCode;
    private List<Object> data;

}
