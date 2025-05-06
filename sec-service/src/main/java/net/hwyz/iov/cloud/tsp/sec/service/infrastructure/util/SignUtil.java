package net.hwyz.iov.cloud.tsp.sec.service.infrastructure.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 签名工具类
 *
 * @author hwyz_leo
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SignUtil {

    /**
     * P7A签名认证
     *
     * @param signature 签名
     * @return 证书信息
     */
    public String p7AttachedVerify(String signature) {
        // TODO 调用签名服务器验证签名，如果失败则抛出异常
        return null;
    }

}
