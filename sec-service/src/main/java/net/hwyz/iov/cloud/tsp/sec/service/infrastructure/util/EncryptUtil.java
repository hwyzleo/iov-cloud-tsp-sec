package net.hwyz.iov.cloud.tsp.sec.service.infrastructure.util;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
     * 默认密钥（Hex）
     */
    @Value("${biz.default-sk-hex}")
    private String defaultSkHex;

    /**
     * 通过证书的非对称加密
     *
     * @param certInfo  证书信息
     * @param plaintext Hex明文
     * @return Hex密文
     */
    public String asymmetricEncryptByCert(String certInfo, String plaintext) {
        if ("default".equals(certInfo)) {
            return symmetricEncryptByDefaultSk(plaintext);
        }
        // TODO 调用证书进行非对称加密
        return null;
    }

    /**
     * 基于默认密钥的对称加密
     * AES/CBC/PKCS5Padding
     *
     * @param plaintext Hex明文
     * @return HexIV + Hex密文
     */
    public String symmetricEncryptByDefaultSk(String plaintext) {
        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, HexUtil.decodeHex(defaultSkHex));
        String encryptHex = aes.encryptHex(plaintext);
        String ivHex = HexUtil.encodeHexStr(aes.getCipher().getIV());
        return ivHex + encryptHex;
    }

}
