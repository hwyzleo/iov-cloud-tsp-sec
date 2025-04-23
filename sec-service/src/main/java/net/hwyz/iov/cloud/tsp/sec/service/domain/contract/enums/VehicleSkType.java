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
    TBOX_SK;

    public static VehicleSkType valOf(String val) {
        return Arrays.stream(VehicleSkType.values())
                .filter(vehicleSkType -> vehicleSkType.name().equals(val))
                .findFirst()
                .orElse(null);
    }

}
