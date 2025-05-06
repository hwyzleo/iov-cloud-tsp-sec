package net.hwyz.iov.cloud.tsp.sec.service.domain.contract.enums;

import lombok.AllArgsConstructor;

import java.util.Arrays;

/**
 * 车辆密钥类型枚举类
 *
 * @author hwyz_leo
 */
@AllArgsConstructor
public enum VehicleSkType {

    /** 车联终端密钥 **/
    TBOX_SK,
    /** 车联终端通讯密钥 **/
    TBOX_COMM_SK,
    /** 车联终端防盗安全常量 **/
    TBOX_IMMO,
    /** BTM防盗安全常量 **/
    BTM_IMMO,
    /** 中央计算平台通讯密钥 **/
    CCP_COMM_SK,
    /** 中央计算平台防盗安全常量 **/
    CCP_IMMO,
    /** 信息娱乐模块通讯密钥 **/
    IDCM_COMM_SK,
    /** 智驾模块通讯密钥 **/
    ADCM_COMM_SK,
    /** IBCM防盗安全常量 **/
    IBCM_IMMO,
    /** MDCU防盗安全常量 **/
    MDCU_IMMO;

    public static VehicleSkType valOf(String val) {
        return Arrays.stream(VehicleSkType.values())
                .filter(vehicleSkType -> vehicleSkType.name().equals(val))
                .findFirst()
                .orElse(null);
    }

}
