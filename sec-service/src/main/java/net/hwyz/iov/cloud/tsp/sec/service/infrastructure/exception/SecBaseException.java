package net.hwyz.iov.cloud.tsp.sec.service.infrastructure.exception;


import net.hwyz.iov.cloud.framework.common.exception.BaseException;

/**
 * 安全服务基础异常
 *
 * @author hwyz_leo
 */
public class SecBaseException extends BaseException {

    private static final int ERROR_CODE = 203000;

    public SecBaseException(String message) {
        super(ERROR_CODE, message);
    }

    public SecBaseException(int errorCode) {
        super(errorCode);
    }

    public SecBaseException(int errorCode, String message) {
        super(errorCode, message);
    }

}
