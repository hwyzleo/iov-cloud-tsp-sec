package net.hwyz.iov.cloud.tsp.sec.service.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.common.enums.ClientType;
import net.hwyz.iov.cloud.tsp.sec.api.contract.request.CertificateSigningRequest;
import net.hwyz.iov.cloud.tsp.sec.api.contract.response.CertificateResponse;
import net.hwyz.iov.cloud.tsp.vmd.api.feign.service.ExVehicleLifecycleService;
import org.springframework.stereotype.Service;

/**
 * 证书应用服务类
 *
 * @author hwyz_leo
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CertAppService {

    private final PartAppService partAppService;
    private final ExVehicleLifecycleService exVehicleLifecycleService;

    /**
     * 申请证书
     *
     * @param vin        车架号
     * @param deviceId   客户端ID
     * @param clientType 客户端类型
     * @param csr        证书签名请求
     * @return 证书
     */
    public CertificateResponse apply(String vin, String deviceId, ClientType clientType, CertificateSigningRequest csr) {
        partAppService.checkPartExist(clientType, deviceId);
        // TODO 调用 PKI 生成证书
        CertificateResponse response = new CertificateResponse();
        switch (clientType) {
            case TBOX -> exVehicleLifecycleService.recordFirstApplyTboxCertNode(vin);
            case CCP -> exVehicleLifecycleService.recordFirstApplyCcpCertNode(vin);
            case IDCM -> exVehicleLifecycleService.recordFirstApplyIdcmCertNode(vin);
            case ADCM -> exVehicleLifecycleService.recordFirstApplyAdcmCertNode(vin);
        }
        return response;
    }

    /**
     * 更新证书
     *
     * @param vin        车架号
     * @param deviceId   客户端ID
     * @param clientType 客户端类型
     * @return 证书
     */
    public CertificateResponse renew(String vin, String deviceId, ClientType clientType) {
        // TODO 调用 PKI 更新证书
        return null;
    }


}
