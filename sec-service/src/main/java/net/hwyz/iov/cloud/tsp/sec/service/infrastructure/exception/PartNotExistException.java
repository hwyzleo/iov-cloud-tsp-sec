package net.hwyz.iov.cloud.tsp.sec.service.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.enums.ClientType;

/**
 * 零部件不存在异常
 *
 * @author hwyz_leo
 */
@Slf4j
public class PartNotExistException extends SecBaseException {

    private static final int ERROR_CODE = 203001;

    public PartNotExistException(ClientType clientType, String sn) {
        super(ERROR_CODE);
        logger.warn("零部件[{}][{}]不存在", clientType, sn);
    }

}
