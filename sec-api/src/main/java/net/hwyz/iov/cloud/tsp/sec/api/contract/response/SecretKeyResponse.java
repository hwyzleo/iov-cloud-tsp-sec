package net.hwyz.iov.cloud.tsp.sec.api.contract.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 密钥响应
 *
 * @author hwyz_leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecretKeyResponse {

    /**
     * 加密后的密钥
     */
    private String encryptedSk;

    /**
     * 密钥有效期
     */
    private Long expireTime;

}
