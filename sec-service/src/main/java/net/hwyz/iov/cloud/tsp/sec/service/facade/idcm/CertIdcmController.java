package net.hwyz.iov.cloud.tsp.sec.service.facade.idcm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.enums.ClientType;
import net.hwyz.iov.cloud.tsp.sec.api.contract.request.CertificateSigningRequest;
import net.hwyz.iov.cloud.tsp.sec.api.contract.response.CertificateResponse;
import net.hwyz.iov.cloud.tsp.sec.api.feign.idcm.CertIdcmApi;
import net.hwyz.iov.cloud.tsp.sec.service.application.service.CertAppService;
import org.springframework.web.bind.annotation.*;

/**
 * 证书相关信息娱乐模块接口实现类
 *
 * @author hwyz_leo
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/idcm/cert")
public class CertIdcmController implements CertIdcmApi {

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
    public CertificateResponse apply(@RequestHeader String vin, @RequestHeader String clientId,
                                     @RequestBody @Valid CertificateSigningRequest csr) {
        logger.info("车辆[{}]信息娱乐模块[{}]申请证书", vin, clientId);
        return certAppService.apply(vin, clientId, ClientType.IDCM, csr);
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
    public CertificateResponse renew(@RequestHeader String vin, @RequestHeader String clientId,
                                     @RequestBody @Valid CertificateSigningRequest csr) {
        logger.info("车辆[{}]信息娱乐模块[{}]更新证书", vin, clientId);
        return certAppService.renew(vin, clientId, ClientType.IDCM);
    }

}
