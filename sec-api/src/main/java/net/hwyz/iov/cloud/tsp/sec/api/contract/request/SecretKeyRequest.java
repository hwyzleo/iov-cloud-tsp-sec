package net.hwyz.iov.cloud.tsp.sec.api.contract.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 密钥请求
 *
 * @author hwyz_leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecretKeyRequest {

    /**
     * 原文
     */
    private String plaintext;

    /**
     * 签名
     */
    private String signature;

}
