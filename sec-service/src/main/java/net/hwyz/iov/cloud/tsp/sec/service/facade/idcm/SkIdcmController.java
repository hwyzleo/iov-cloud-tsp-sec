package net.hwyz.iov.cloud.tsp.sec.service.facade.idcm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.enums.ClientType;
import net.hwyz.iov.cloud.tsp.sec.api.contract.request.SecretKeyRequest;
import net.hwyz.iov.cloud.tsp.sec.api.contract.response.SecretKeyResponse;
import net.hwyz.iov.cloud.tsp.sec.api.feign.idcm.SkIdcmApi;
import net.hwyz.iov.cloud.tsp.sec.service.application.service.SkAppService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 密钥相关信息娱乐模块接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/idcm/sk")
public class SkIdcmController implements SkIdcmApi {

    private final SkAppService skAppService;

    /**
     * 申请通讯密钥
     *
     * @param vin      车架号
     * @param clientId 客户端ID
     * @param request  密钥请求
     * @return 密钥响应
     */
    @Override
    public SecretKeyResponse applyCommSk(String vin, String clientId, SecretKeyRequest request) {
        logger.info("车辆[{}]信息娱乐模块[{}]申请密钥", vin, clientId);
        return skAppService.generateVehicleClientCommSk(vin, clientId, ClientType.IDCM, request);
    }
}
