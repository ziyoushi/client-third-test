package com.cc.develop.third.client.model;

import lombok.Data;

/**
 * com.cc.develop.third.client.model
 *
 * @author changchen
 * @email java@mail.com
 * @date 2021-01-02 14:07:35
 */
@Data
public class SeedUserScanExcelData {

    /**
     * 车主姓名
     */
    private String carOwnerName;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 开始时间
     */
    private String startDatetime;
    /**
     * 截至时间
     */
    private String endDatetime;
    /**
     * 种子用户类型
     */
    private String userType;
    /**
     * 车牌颜色
     */
    private String carPlateColor;
    /**
     * 车牌号码
     */
    private String carPlateNo;
    /**
     * 停车场名称
     */
    private String parkingName;
    /**
     * 停车场code
     */
    private String parkingCode;
}
