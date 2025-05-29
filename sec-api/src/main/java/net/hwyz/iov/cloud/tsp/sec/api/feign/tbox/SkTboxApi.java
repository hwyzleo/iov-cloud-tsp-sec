package net.hwyz.iov.cloud.tsp.sec.api.feign.tbox;

import net.hwyz.iov.cloud.framework.common.bean.Response;
import net.hwyz.iov.cloud.tsp.sec.api.contract.request.SecretKeyRequest;
import net.hwyz.iov.cloud.tsp.sec.api.contract.response.SecretKeyResponse;

/**
 * 密钥相关车联终端接口
 *
 * @author hwyz_leo
 */
public interface SkTboxApi {

    /**
     * 申请通讯密钥
     *
     * @param vin      车架号
     * @param clientId 客户端ID
     * @param request  密钥请求
     * @return 密钥响应
     */
    Response<SecretKeyResponse> applyCommSk(String vin, String clientId, SecretKeyRequest request);

}
