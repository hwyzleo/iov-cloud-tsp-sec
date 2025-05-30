package net.hwyz.iov.cloud.tsp.sec.service.facade.ccp;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.bean.Response;
import net.hwyz.iov.cloud.framework.common.enums.ClientType;
import net.hwyz.iov.cloud.tsp.sec.api.contract.request.SecretKeyRequest;
import net.hwyz.iov.cloud.tsp.sec.api.contract.response.SecretKeyResponse;
import net.hwyz.iov.cloud.tsp.sec.api.feign.ccp.SkCcpApi;
import net.hwyz.iov.cloud.tsp.sec.service.application.service.SkAppService;
import org.springframework.web.bind.annotation.*;

/**
 * 密钥相关中央计算平台接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/ccp/sk")
public class SkCcpController implements SkCcpApi {

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
    @PostMapping("/applyCommSk")
    public Response<SecretKeyResponse> applyCommSk(@RequestHeader String vin, @RequestHeader String clientId,
                                                   @RequestBody @Valid SecretKeyRequest request) {
        logger.info("车辆[{}]中央计算平台[{}]申请密钥", vin, clientId);
        return new Response<>(skAppService.generateVehicleClientCommSk(vin, clientId, ClientType.CCP, request));
    }
}
