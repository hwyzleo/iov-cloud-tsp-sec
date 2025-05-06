package net.hwyz.iov.cloud.tsp.sec.service.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.enums.ClientType;

/**
 * 车辆零部件不匹配异常
 *
 * @author hwyz_leo
 */
@Slf4j
public class VehiclePartNotMatchException extends SecBaseException {

    private static final int ERROR_CODE = 203003;

    public VehiclePartNotMatchException(ClientType clientType, String sn, String oldVin, String newVin) {
        super(ERROR_CODE);
        logger.warn("零部件[{}][{}]原绑定车辆[{}]与当前车辆[{}]不匹配", clientType, sn, oldVin, newVin);
    }

}
