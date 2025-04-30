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

    /** TBOX密钥 **/
    TBOX_SK,
    /** TBOX防盗安全常量 **/
    TBOX_IMMO,
    /** BTM防盗安全常量 **/
    BTM_IMMO,
    /** CCU防盗安全常量 **/
    CCU_IMMO,
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
