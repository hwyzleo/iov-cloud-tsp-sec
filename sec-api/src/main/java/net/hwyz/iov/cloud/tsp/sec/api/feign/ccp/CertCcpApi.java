package net.hwyz.iov.cloud.tsp.sec.api.feign.ccp;

import net.hwyz.iov.cloud.tsp.sec.api.contract.request.CertificateSigningRequest;
import net.hwyz.iov.cloud.tsp.sec.api.contract.response.CertificateResponse;

/**
 * 证书中央计算平台接口
 *
 * @author hwyz_leo
 */
public interface CertCcpApi {

    /**
     * 申请证书
     *
     * @param vin 车架号
     * @param sn  设备序列号
     * @param csr 证书签名请求
     * @return 证书
     */
    CertificateResponse apply(String vin, String sn, CertificateSigningRequest csr);

    /**
     * 更新证书
     *
     * @param vin 车架号
     * @param sn  设备序列号
     * @param csr 证书签名请求
     * @return 证书
     */
    CertificateResponse renew(String vin, String sn, CertificateSigningRequest csr);

}
