package net.hwyz.iov.cloud.tsp.sec.api.contract.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 证书响应
 *
 * @author hwyz_leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CertificateResponse {

    /**
     * PEM格式的PKCS#7数字证书及证书链
     */
    private String p7b;

}
