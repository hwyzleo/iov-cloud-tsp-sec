package net.hwyz.iov.cloud.tsp.sec.api.contract.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 证书签名请求（P10）
 *
 * @author hwyz_leo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CertificateSigningRequest {

    /**
     * PEM格式的CSR数据集合
     */
    private String csr;

}
