package net.hwyz.iov.cloud.tsp.sec.service.facade.ccp;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.bean.Response;
import net.hwyz.iov.cloud.framework.common.enums.ClientType;
import net.hwyz.iov.cloud.tsp.sec.api.contract.request.CertificateSigningRequest;
import net.hwyz.iov.cloud.tsp.sec.api.contract.response.CertificateResponse;
import net.hwyz.iov.cloud.tsp.sec.api.feign.ccp.CertCcpApi;
import net.hwyz.iov.cloud.tsp.sec.service.application.service.CertAppService;
import org.springframework.web.bind.annotation.*;

/**
 * 证书相关中央计算平台接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/ccp/cert")
public class CertCcpController implements CertCcpApi {

    private final CertAppService certAppService;

    /**
     * 申请证书
     *
     * @param vin      车架号
     * @param clientId 客户端ID
     * @param csr      证书签名请求
     * @return 证书
     */
    @Override
    @PostMapping("/apply")
    public Response<CertificateResponse> apply(@RequestHeader String vin, @RequestHeader String clientId,
                                               @RequestBody @Valid CertificateSigningRequest csr) {
        logger.info("车辆[{}]中央计算平台[{}]申请证书", vin, clientId);
        return new Response<>(certAppService.apply(vin, clientId, ClientType.CCP, csr));
    }

    /**
     * 更新证书
     *
     * @param vin      车架号
     * @param clientId 客户端ID
     * @param csr      证书签名请求
     * @return 证书
     */
    @Override
    @PostMapping("/renew")
    public Response<CertificateResponse> renew(@RequestHeader String vin, @RequestHeader String clientId,
                                               @RequestBody @Valid CertificateSigningRequest csr) {
        logger.info("车辆[{}]中央计算平台[{}]更新证书", vin, clientId);
        return new Response<>(certAppService.renew(vin, clientId, ClientType.CCP));
    }

}
