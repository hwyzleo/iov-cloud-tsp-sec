package net.hwyz.iov.cloud.tsp.sec.service.infrastructure.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 加密工具类
 *
 * @author hwyz_leo
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EncryptUtil {

    /**
     * 通过证书的非对称加密
     *
     * @param certInfo  证书信息
     * @param plaintext Hex明文
     * @return Hex密文
     */
    public String asymmetricEncryptByCert(String certInfo, String plaintext) {
        // TODO 调用证书进行非对称加密
        return null;
    }

}
