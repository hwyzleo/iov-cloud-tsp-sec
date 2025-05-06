package net.hwyz.iov.cloud.tsp.sec.api.feign.ccp;

import net.hwyz.iov.cloud.tsp.sec.api.contract.request.SecretKeyRequest;
import net.hwyz.iov.cloud.tsp.sec.api.contract.response.SecretKeyResponse;

/**
 * 密钥相关中央计算平台接口
 *
 * @author hwyz_leo
 */
public interface SkCcpApi {

    /**
     * 申请通讯密钥
     *
     * @param vin      车架号
     * @param clientId 客户端ID
     * @param request  密钥请求
     * @return 密钥响应
     */
    SecretKeyResponse applyCommSk(String vin, String clientId, SecretKeyRequest request);

}
