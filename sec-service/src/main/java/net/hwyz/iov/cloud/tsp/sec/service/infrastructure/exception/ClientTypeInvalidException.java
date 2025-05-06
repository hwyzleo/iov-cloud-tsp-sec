package net.hwyz.iov.cloud.tsp.sec.service.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.enums.ClientType;

/**
 * 客户端无效异常
 *
 * @author hwyz_leo
 */
@Slf4j
public class ClientTypeInvalidException extends SecBaseException {

    private static final int ERROR_CODE = 203002;

    public ClientTypeInvalidException(ClientType clientType) {
        super(ERROR_CODE);
        logger.warn("客户端[{}]无效", clientType);
    }

}
