package com.cc.develop.third.client.json;

import lombok.Data;

/**
 * com.cc.develop.third.client.json
 *
 * @author changchen
 * @email java@mail.com
 * @date 2021-03-02 15:48:29
 */
@Data
public class JsonBean {

    private Integer id;
    private String carPlateNo;
    private String carPlateColor;
    private String parkingName;
    private String parkingCode;
    private String validateStatus;
    private Long validateDate;
    private Long endDate;
    private String reason;
    private Integer updateUser;
    private String siteId;


    /**
     * {
     *     "id": 7989,
     *     "carPlateNo": "苏EPASS2",
     *     "carPlateColor": "001",
     *     "parkingName": "人民商场地下车库",
     *     "parkingCode": "PT012019",
     *     "validateStatus": "001",
     *     "validateDate": 1612022400000,
     *     "endDate": 1614528000000,
     *     "reason": "修改今天为月卡最后一天",
     *     "updateUser": 35,
     *     "siteId": "1"
     *   },
     */

}
