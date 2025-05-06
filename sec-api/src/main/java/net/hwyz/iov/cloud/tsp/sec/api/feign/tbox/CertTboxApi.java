package net.hwyz.iov.cloud.tsp.sec.api.feign.tbox;

import net.hwyz.iov.cloud.tsp.sec.api.contract.request.CertificateSigningRequest;
import net.hwyz.iov.cloud.tsp.sec.api.contract.response.CertificateResponse;

/**
 * 证书车联终端接口
 *
 * @author hwyz_leo
 */
public interface CertTboxApi {

    /**
     * 申请证书
     *
     * @param vin      车架号
     * @param clientId 客户端ID
     * @param csr      证书签名请求
     * @return 证书
     */
    CertificateResponse apply(String vin, String clientId, CertificateSigningRequest csr);

    /**
     * 更新证书
     *
     * @param vin      车架号
     * @param clientId 客户端ID
     * @param csr      证书签名请求
     * @return 证书
     */
    CertificateResponse renew(String vin, String clientId, CertificateSigningRequest csr);

}
